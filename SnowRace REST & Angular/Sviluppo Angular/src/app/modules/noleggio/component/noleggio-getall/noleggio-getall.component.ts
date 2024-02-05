import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Router } from "@angular/router";

import {
  ICON_CONSTANT,
  INPUT_CONSTANT,
  LABEL_CONSTANT,
  RESULT_CONSTANT,
  TABLE_COLUMNS,
} from "src/app/constants";
import { AngularMaterialModule } from "src/app/utils";
import { GenericTableComponent } from "src/app/shared/generic";
import { ActivatedRoute } from "@angular/router";
import { MatTableDataSource } from "@angular/material/table";
import { MatDialog } from "@angular/material/dialog";
import { WorkInProgressComponent } from "src/app/shared";
import { GenericTableService, LoaderSpinnerService } from "src/app/services";
import { NoleggioService } from "src/app/services/noleggio.service";
import { AttrezzaturaService } from "src/app/services/attrezzatura.service";
import { NoleggioInsertComponent } from "../noleggio-insert/noleggio-insert.component";

@Component({
  selector: "app-noleggio-getall",
  standalone: true,
  imports: [CommonModule, AngularMaterialModule, GenericTableComponent],
  templateUrl: "./noleggio-getall.component.html",
  styleUrls: ["./noleggio-getall.component.scss"],
})
export class NoleggioGetallComponent {
  resultConstant = RESULT_CONSTANT;
  size = INPUT_CONSTANT.pageSize;
  pageIndex = INPUT_CONSTANT.pageNumber;
  totalElements!: number;
  dataSource!: any;
  displayedColumns = TABLE_COLUMNS.noleggio;

  cellHeadTypes = {
    select: "checkout",
    idnoleggio: "sort",
    username: "sort",
    idattrezzatura: "sort",
    data_inizio: "sort",
    data_fine: "sort",
  };

  sortedItems = {
    username: false,
    idattrezzatura: false,
    data_inizio: false,
    data_fine: false,
  };

  listaNoleggi: any[] = [];
  a: any;
  constructor(
    private genericTableService: GenericTableService,
    private loaderSpinnerService: LoaderSpinnerService,
    private noleggioService: NoleggioService,
    private attrezzatureService: AttrezzaturaService,
    private activatedRoute: ActivatedRoute,
    private dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit() {
    this.getDataFromResolver();
  }

  getDataFromResolver() {
    this.totalElements =
      this.activatedRoute.snapshot.data["listaNoleggi"].totalElements;
    this.pageIndex =
      this.activatedRoute.snapshot.data["listaNoleggi"].pageIndex;
    this.listaNoleggi =
      this.activatedRoute.snapshot.data["listaNoleggi"].content;
    console.log(this.listaNoleggi);
    if (this.listaNoleggi) {
      this.dataSource = new MatTableDataSource<any>(
        this.getMappedDataSource(this.listaNoleggi)
      );
    }
  }

  changePage(event: any) {
    this.loaderSpinnerService.show();
    this.noleggioService
      .getListaNoleggi(INPUT_CONSTANT.pageSize, event.number)
      .subscribe({
        next: (res) => {
          this.totalElements = res.totalElements;
          this.pageIndex = event.number;
          if (res.content) {
            this.listaNoleggi = res.content;
            this.dataSource = new MatTableDataSource<any>(
              this.getMappedDataSource(this.listaNoleggi)
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
      // this.attrezzatureService.readAttrezzatura(idAttrezzatura)
      console.log("-------------" + r.idattrezzatura);

      // this.attrezzatureService.readAttrezzatura(r.idattrezzatura).subscribe({
      //   next: (res: any) => {
      //     console.log(res);
      //     this.a = res;

      //   },
      // });

      const action = [
        {
          title: LABEL_CONSTANT.modifica,
          icon: ICON_CONSTANT.edit,
          type: "icon",
          callback: () => this.modificaNoleggio(r.idnoleggio),
        },
        {
          title: LABEL_CONSTANT.elimina,
          icon: ICON_CONSTANT.delete,
          type: "icon",
          callback: () => this.eliminaNoleggio(r.idnoleggio),
        },
      ];
      return {
        idnoleggio: r.idnoleggio,
        select: false,
        username: r.username ? r.username : '-',
        idattrezzatura: r.idattrezzatura ? r.idattrezzatura.nome : '-',
        data_inizio: r.startDate,
        data_fine: r.endDate,
        action: action,
      };
    });
  }

  modificaNoleggio(id: number) {
    this.dialog
      .open(NoleggioInsertComponent, {
        width: "660px",
        height: "500px",
        disableClose: true,
      })
      .componentInstance.setModifica(id);
  }

  eliminaNoleggio(id: number) {
    console.log("elimina noleggio" + id);
    this.noleggioService.deleteNoleggio(id).subscribe({
      next: () => {
        //this.router.navigate(['gestionale/noleggio/getall']);
        this.listaNoleggi = this.listaNoleggi.filter(
          (noleggio) => noleggio.id !== id
        );
        window.location.reload();
      },
      error: (error) => {
        console.log("errore durante l'eliminazione del noleggio", error);
      },
    });
  }
}
