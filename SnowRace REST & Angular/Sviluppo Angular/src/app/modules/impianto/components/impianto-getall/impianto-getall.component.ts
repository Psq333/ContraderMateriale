import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Router, RouterModule } from "@angular/router";
import { LeggiImpiantoComponent } from "../leggi-impianto/leggi-impianto.component";
import { GenericButtonComponent } from "src/app/shared/button";

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
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { WorkInProgressComponent } from "src/app/shared";
import {
  GenericTableService,
  LoaderSpinnerService,
  LoginService,
} from "src/app/services";
import { ImpiantiService } from "src/app/services/impianti.service";
import { InserisciImpiantoComponent } from "../inserisci-impianto/inserisci-impianto.component";
import PisteComponent from "src/app/modules/piste/components/piste-getall/piste-getall.component";
import { PistaService } from "src/app/services/pista.service";

@Component({
  selector: "app-impianto-getall",
  standalone: true,
  imports: [CommonModule, AngularMaterialModule, GenericTableComponent, RouterModule, PisteComponent, LeggiImpiantoComponent, GenericButtonComponent],
  templateUrl: "./impianto-getall.component.html",
  styleUrls: ["./impianto-getall.component.scss"],
})

export class ImpiantoGetallComponent {
  resultConstant = RESULT_CONSTANT;
  size = INPUT_CONSTANT.pageSize;
  pageIndex = INPUT_CONSTANT.pageNumber;
  totalElements!: number;
  dataSource!: any;
  displayedColumns = TABLE_COLUMNS.impianti;
  isUtente : boolean = false;
  listaPiste:any;
  idImpianto:number = 0;
  labelConstant: any = LABEL_CONSTANT;

  cellHeadTypes = {
    select: "checkout",
    id: "sort",
    };


  listaImpianti: any[] = [];
  utente: any[] = [];
  userType !: 'USER'|'ADMIN' | 'AMMINISTRATORE';
  userName: any;

  constructor(
    private genericTableService: GenericTableService,
    private loaderSpinnerService: LoaderSpinnerService,
    private impiantiService: ImpiantiService,
    private activatedRoute: ActivatedRoute,
    private dialog: MatDialog,
    private router: Router,
    private loginService:LoginService,
    private pistaService:PistaService,
  ) {}

  ngOnInit() {
    this.setUtente();
    this.getDataFromResolver();
  }

  getDataFromResolver() {
    this.totalElements =
      this.activatedRoute.snapshot.data["listaImpianti"].totalElements;
    this.pageIndex =
      this.activatedRoute.snapshot.data["listaImpianti"].pageIndex;
    this.listaImpianti =
      this.activatedRoute.snapshot.data["listaImpianti"].content;
    if (this.listaImpianti) {
      console.log("IMPIANTI ", this.listaImpianti);
      this.dataSource = new MatTableDataSource<any>(
        this.getMappedDataSource(this.listaImpianti)
      );
    }
  }

  changePage(event: any) {
    this.loaderSpinnerService.show();
    this.impiantiService
      .getListaImpianti(INPUT_CONSTANT.pageSize, event.number)
      .subscribe({
        next: (res) => {
          this.totalElements = res.totalElements;
          this.pageIndex = event.number;
          if (res.content) {
            this.listaImpianti = res.content;
            this.dataSource = new MatTableDataSource<any>(
              this.getMappedDataSource(this.listaImpianti)
            );
          }
          this.genericTableService.emitFilteringStatus(false);
          this.loaderSpinnerService.hide();
        },
        error: () => this.loaderSpinnerService.hide(),
      });
  }

  getMappedDataSource(toMap: any[]) {
  if(this.userType === "ADMIN" || this.userType === "AMMINISTRATORE"){
      return this.listaAdmin(toMap);
    }
    else{
      this.displayedColumns = TABLE_COLUMNS.impiantiUser;
      return this.listaUser(toMap);
    }
  }

  listaAdmin(toMap: any[]){
    return toMap.map((r) => {
      const action = [
        {
          title: LABEL_CONSTANT.modifica,
          icon: ICON_CONSTANT.edit,
          type: "icon",
          callback: () => this.modificaImpianto(r.idImpianto),
        },
        {
          title: LABEL_CONSTANT.elimina,
          icon: ICON_CONSTANT.delete,
          type: "icon",
          callback: () => this.eliminaImpianto(r.idImpianto),
        },
      ];
      if (this.userType === "AMMINISTRATORE"){
      return {
        idImpianto: r.idImpianto,
        select: false,
        nome: r.nome,
        descrizione: r.descrizione,
        luogo: r.luogo,
        amministratore: r.amministratore ? r.amministratore.username : '-',
        action: action,
      };} 
      else {
        if (!(r.amministratore && this.userName === r.amministratore.username)){
          return undefined;
        }
        return {
          idImpianto: r.idImpianto,
          select: false,
          nome: r.nome,
          descrizione: r.descrizione,
          luogo: r.luogo,
          amministratore: r.amministratore ? r.amministratore.username : '-',
          action: action,
        };
      }
    }).filter(item => item !== undefined);
  }

  listaUser(toMap: any[]){
    return toMap.map((r) => {
      const action = [
        {
          title: LABEL_CONSTANT.scegli,
          icon: ICON_CONSTANT.arrow,
          type: "icon",
          callback: () => this.selezionaPista(r.idImpianto),
        },    
      ];
      return {
        idImpianto: r.idImpianto,
        select: false,
        nome: r.nome,
        descrizione: r.descrizione,
        luogo: r.luogo,
        amministratore: r.amministratore ? r.amministratore.username : '-',
        action: action,
      };
    });
  }

  setUtente(){
    var utente = this.loginService.getUtenteSessione();
    this.userType = utente.usertype;
    this.userName = utente.username;
    if(this.userType==="USER"){
      this.isUtente = true;
    }
  }

  modificaImpianto(id: number) {
    this.dialog
      .open(InserisciImpiantoComponent, {
        width: "660px",
        height: "600px",
        disableClose: true,
      })
      .componentInstance.isModificaSet(id);
  }

  eliminaImpianto(id: number) {
    this.impiantiService.deleteImpianto(id).subscribe({
      next: () => {
        //this.router.navigate(['gestionale/impianti/getall']);
        this.listaImpianti = this.listaImpianti.filter(
          (impianto) => impianto.id !== id
        );
        window.location.reload();
      },
      error: (error) => {
        console.log("errore durante l'eliminazione dell'impianto", error);
      },
    });
  }

  selezionaPista(id:number){
    const args = [
      { idImpianto: id},
    ];
    this.pistaService.getListaPisteImpianto(25,0,args[0]).subscribe({
      next: (res:any) =>{
        this.listaPiste = res;
        this.toggleComponentSize();
        this.idImpianto = id;
      }
    });

  }

  public isComponentNarrow: boolean = false;
  toggleComponentSize() {
    this.isComponentNarrow = !this.isComponentNarrow;
  }

  indietro(){
    this.toggleComponentSize();
    this.pistaService.getListaPiste(25,1000).subscribe({
      next: (res:any) =>{
        this.listaPiste = res;
      }
    });
  }
}
