import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { AttrezzaturaService } from "src/app/services/attrezzatura.service";
import {
  ICON_CONSTANT,
  INPUT_CONSTANT,
  LABEL_CONSTANT,
  RESULT_CONSTANT,
  TABLE_COLUMNS,
} from "src/app/constants";
import { AngularMaterialModule } from "src/app/utils";
import { GenericTableComponent } from "src/app/shared/generic";
import { ActivatedRoute, Router } from "@angular/router";
import { MatTableDataSource } from "@angular/material/table";
import { MatDialog } from "@angular/material/dialog";
import { WorkInProgressComponent } from "src/app/shared";
import { GenericTableService, LoaderSpinnerService } from "src/app/services";
import { AttrezzaturaInsertComponents } from "../attrezzatura-insert/attrezzatura-insert.component";
import { ImpiantiService } from "src/app/services/impianti.service";
import { Observable } from "rxjs";
@Component({
  selector: "app-attrezzatura-getall",
  standalone: true,
  imports: [CommonModule, AngularMaterialModule, GenericTableComponent],
  templateUrl: "./attrezzatura-getall.component.html",
  styleUrls: ["./attrezzatura-getall.component.scss"],
})
export class AttrezzaturaGetallComponent {
  resultConstant = RESULT_CONSTANT;
  size = INPUT_CONSTANT.pageSize;
  pageIndex = INPUT_CONSTANT.pageNumber;
  totalElements!: number;
  dataSource!: any;
  displayedColumns = TABLE_COLUMNS.attrezzatura;
  isReadImpiantiNome: boolean = false;

  cellHeadTypes = {
    select: "checkout",
    //idAttrezzatura: "sort",
    nome: "sort",
    descrizione: "sort",
    prezzo: "sort",
    idImpianto: "sort",
  };

  sortedItems = {
    nome: false,
    prezzo: false,
    idImpianto: false,
  };

  listaImpianti: any[] = [];
  listaAttrezzatura: any[] = [];

  constructor(
    private genericTableService: GenericTableService,
    private loaderSpinnerService: LoaderSpinnerService,
    private attrezzaturaService: AttrezzaturaService,
    private activatedRoute: ActivatedRoute,
    private dialog: MatDialog,
    private router: Router,
    private impiantiService: ImpiantiService
  ) {}

  ngOnInit() {
    let list = this.attrezzaturaService.getImpianti().subscribe({
      next: (res: any) => {
        this.listaImpianti = res;
        this.getDataFromResolver();
      },
    });
  }

  getDataFromResolver() {
    console.log(this.activatedRoute.snapshot.data);
    console.log(this.activatedRoute.snapshot.data["listaAttrezzatura"]);
    this.totalElements =
      this.activatedRoute.snapshot.data["listaAttrezzatura"].totalElements;
    this.pageIndex =
      this.activatedRoute.snapshot.data["listaAttrezzatura"].pageIndex;
    this.listaAttrezzatura =
      this.activatedRoute.snapshot.data["listaAttrezzatura"].content;
    if (this.listaAttrezzatura) {
      this.dataSource = new MatTableDataSource<any>(
        this.getMappedDataSource(this.listaAttrezzatura)
      );
    }
  }

  changePage(event: any) {
    this.loaderSpinnerService.show();
    this.attrezzaturaService
      .getListaAttrezzatura(INPUT_CONSTANT.pageSize, event.number)
      .subscribe({
        next: (res) => {
          this.totalElements = res.totalElements;
          this.pageIndex = event.number;
          if (res.content) {
            this.listaAttrezzatura = res.content;
            this.dataSource = new MatTableDataSource<any>(
              this.getMappedDataSource(this.listaAttrezzatura)
            );
          }
          this.genericTableService.emitFilteringStatus(false);
          this.loaderSpinnerService.hide();
        },
        error: () => this.loaderSpinnerService.hide(),
      });
  }

  getMappedDataSource(toMap: any[]) {
    return toMap.map((r) => {
      console.log("stampo quello che arriva", r);
      const action = [
        {
          title: LABEL_CONSTANT.modifica,
          icon: ICON_CONSTANT.edit,
          type: "icon",
          callback: () => this.updateAttrezzatura(r.idAttrezzatura),
        },
        {
          title: LABEL_CONSTANT.elimina,
          icon: ICON_CONSTANT.delete,
          type: "icon",
          callback: () => this.deleteAttrezzatura(r.idAttrezzatura),
        },
      ];
      return {
        //idAttrezzatura: r.idAttrezzatura,
        select: false,
        nome: r.nome,
        descrizione: r.descrizione,
        prezzo: r.prezzo,
        idImpianto: r.idImpianto ? r.idImpianto.nome : '-',
        action: action,
      };
    });
  }
  updateAttrezzatura(idAttrezzatura: number) {
    this.dialog
      .open(AttrezzaturaInsertComponents, {
        width: "660px",
        height: "550px",
        disableClose: true,
      })
      .componentInstance.setModifica(idAttrezzatura);
  }

  deleteAttrezzatura(idAttrezzatura: number) {
    console.log("delete attrezzatura" + idAttrezzatura);
    this.attrezzaturaService.deleteAttrezzatura(idAttrezzatura).subscribe({
      next: () => {
        this.listaAttrezzatura = this.listaAttrezzatura.filter(
          (attrezzatura) => attrezzatura.id !== idAttrezzatura
        );
        window.location.reload();
      },
      error: (error) => {
        console.log("errore durante l'eliminazione dell'attrezzatura", error);
      },
    });
  }
}
