import { Component, OnChanges, SimpleChanges } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PistaService } from "src/app/services/pista.service";
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
import { GenericTableService, LoaderSpinnerService, LoginService } from "src/app/services";
import { InvokeFunctionExpr } from "@angular/compiler";
import { Router } from "@angular/router";
import { InserisciPistaComponent } from "../inserisci-pista/inserisci-pista.component";
import { Input } from '@angular/core';
import { InserisciPrenotazioneComponent } from "src/app/modules/prenotazione/inserisci-prenotazione/inserisci-prenotazione.component";

@Component({
  selector: "app-piste",
  standalone: true,
  imports: [CommonModule, AngularMaterialModule, GenericTableComponent],
  templateUrl: "./piste-getall.component.html",
  styleUrls: ["./piste-getall.component.scss"],
})
export default class PisteComponent implements OnChanges {
  dataSource!: any;
  listaPiste: any;
  @Input() pisteInput!: any;
  @Input() userType: any;
  resultConstant = RESULT_CONSTANT;
  size = INPUT_CONSTANT.pageSize;
  pageIndex = INPUT_CONSTANT.pageNumber;
  totalElements!: number;
  displayedColumns = [
    "select",
    "nome",
    "difficolta",
    "prezzo",
    "prenotazioniMax",
    "action",
  ];

  cellHeadTypes = {
    select: "checkbox",
    Nome: "sort",
    Prezzo: "sort",
    Difficoltà: "sort",
    PrenotazioniMax: "sort",
  };

  sortedItems = {
    nome: false,
    Difficoltà: false,
    Prezzo: false,
    PrenotazioniMax: false,
  };

  constructor(
    private genericTableService: GenericTableService,
    private loaderSpinnerService: LoaderSpinnerService,
    private pistaService: PistaService,
    private activatedRoute: ActivatedRoute,
    private dialog: MatDialog,
    private router: Router,
    private loginService:LoginService
  ) {}

  ngOnInit() {
    console.log("che utente sei ",this.userType);
    if(this.userType !== "USER"){
      this.getDataFromResolver();
    }
  }

  ngOnChanges(changes: SimpleChanges) {

    if (changes['pisteInput'] && !changes['pisteInput'].isFirstChange()) {

      if (this.userType === 'USER' && this.pisteInput) {
        this.totalElements = this.pisteInput.totalElements;
        this.pageIndex = this.pisteInput.pageIndex;
        this.listaPiste = this.pisteInput.content;
        console.log(this.listaPiste);
        if (this.listaPiste) {
          this.dataSource = new MatTableDataSource<any>(
            this.getMappedDataSourceUtente(this.listaPiste)
          );
        }

      } else {
        console.log("Attenzione: pisteInput è ancora indefinito o userType non è USER.");
      }
    }
  }

  getDataFromResolver() {
    this.totalElements =
      this.activatedRoute.snapshot.data["listaPiste"].totalElements;
    this.pageIndex = this.activatedRoute.snapshot.data["listaPiste"].pageIndex;
    this.listaPiste = this.activatedRoute.snapshot.data["listaPiste"].content;
    if (this.listaPiste) {
      this.dataSource = new MatTableDataSource<any>(
        this.getMappedDataSource(this.listaPiste)
      );
    }
  }

  changePage(event: any) {
    // Quando facciamo partire una qualsiasi chiamata facciamo apparire il loaderSpinner per dare un feedback visivo all'utente
    this.loaderSpinnerService.show();
    this.pistaService
      .getListaPiste(INPUT_CONSTANT.pageSize, event.number)
      .subscribe({
        // Quando ci sottoscriviamo ad una qualsiasi chiamata, bisogna utilizzare le casistiche next ed error per gestire correttamente le funzionalità
        next: (res) => {
          this.totalElements = res.totalElements;
          this.pageIndex = event.number;
          if (res.content) {
            this.listaPiste = res.content;
            this.dataSource = new MatTableDataSource<any>(
              this.getMappedDataSource(this.listaPiste)
            );
          }
          // Ricordarsi di aggiungere questa riga di codice ogni volta che si crea una funzione di cambio pagina della tabella, altrimenti si spacca
          this.genericTableService.emitFilteringStatus(false);
          // Nascondere il loader spinner dopo aver effettuato le nostre logiche
          this.loaderSpinnerService.hide();
        },
        // Nascondere sempre il loader spinner nella casistica di errore della chiamata
        error: () => this.loaderSpinnerService.hide(),
      });
  }

  getMappedDataSource(toMap: any[]) {
    // Mappiamo il nostro array di oggetti ricevuto dal backend
    return toMap.map((r) => {
      // Creiamo un'array di azioni che l'utente puo effettuare sulla tabella
      const action = [
        {
          title: LABEL_CONSTANT.modifica,
          icon: ICON_CONSTANT.edit,
          type: "icon",
          callback: () => this.modificaPista(r.idPista),
        },
        {
          title: LABEL_CONSTANT.elimina,
          icon: ICON_CONSTANT.delete,
          type: "icon",
          callback: () => this.eliminaPista(r.idPista),
        },
      ];
      // Ritorniamo quindi per ogni elemento all'interno dell'array un nuovo oggetto che avrà come nomi delle variabili i nomi delle colonne
      return {
        id: r.idPista,
        nomeselect: false,
        nome: r.nome,
        difficolta: r.difficolta,
        prezzo: r.prezzo,
        prenotazioniMax: r.prenotazioniMax,

        // Inserire nome Impianto in base all

        // dataCreazione: this.datePipe.trasform(r.dataCreazione, 'dd/MM/yyyy'),
        action: action,
      };
    });
  }

  modificaPista(id: number) {
    this.dialog.open(InserisciPistaComponent, {
      width: "660px",
      height: "550px",
      disableClose: true,
    }).componentInstance.setModifica(id);
  }

  /**
   * Funzione per l'eliminazione della pista, apre la modale di conferma eliminazione
   * @param {number} id L'id dell'utente da eliminare
   */
  eliminaPista(id: number) {
    this.pistaService.deletePista(id).subscribe({
      next: () => {
        window.location.reload();
      },
      error: (error) => {
        console.error("Errore durante l'eliminazione della pista", error);
      },
    });
  }




  //------------------USER------------------
  getMappedDataSourceUtente(toMap: any[]) {
    // Mappiamo il nostro array di oggetti ricevuto dal backend
    return toMap.map((r) => {
      // Creiamo un'array di azioni che l'utente puo effettuare sulla tabella
      const action = [
        {
          title: LABEL_CONSTANT.scegli,
          icon: ICON_CONSTANT.arrow,
          type: "icon",
          callback: () => this.aperturaDialog(r.idPista),
        },
      ];
      // Ritorniamo quindi per ogni elemento all'interno dell'array un nuovo oggetto che avrà come nomi delle variabili i nomi delle colonne
      return {
        id: r.idPista,
        nomeselect: false,
        nome: r.nome,
        difficolta: r.difficolta,
        prezzo: r.prezzo,
        prenotazioniMax: r.prenotazioniMax,
        action: action,
      };
    });
  }

  aperturaDialog(idPista:number){
    this.dialog.open(InserisciPrenotazioneComponent, {
      width: "660px",
      height: "550px",
      disableClose: true,
    }).componentInstance.setPista(idPista);
  }
}
