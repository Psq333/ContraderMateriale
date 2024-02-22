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
import { PrenotazioniService } from "src/app/services/prenotazioni.service";
import { InserisciPrenotazioneComponent } from "../inserisci-prenotazione/inserisci-prenotazione.component";
import { Prenotazione } from "src/app/models/prenotazione.model";

@Component({
  selector: "app-getall-prenotazione",
  standalone: true,
  imports: [CommonModule, AngularMaterialModule, GenericTableComponent],
  templateUrl: "./getall-prenotazione.component.html",
  styleUrls: ["./getall-prenotazione.component.scss"],
})
export class GetallPrenotazioneComponent {
  resultConstant = RESULT_CONSTANT; //Costante per la stringa da far visualizzare in caso di tabella vuota
  size = INPUT_CONSTANT.pageSize; // Costante con i testi da usare negli input
  pageIndex = INPUT_CONSTANT.pageNumber;
  totalElements!: number; // "!" = variabile che non sarà mai nulla o undefined anche se potrebbe all'inizio in fase di compilazione
  dataSource!: any;
  displayedColumns: string[] = TABLE_COLUMNS.prenotazioni;
  /* [ 
    "select",
    "user",
    "dataInizio",
    "dataFine",
    "piste",
    "action",
  ]; */ //costante che specifica i campi della tabella pren. specificati dentro input.constants

  cellHeadTypes = {
    //oggetto per definire lo stile delle intestazioni di una tabella, ad esempio il css oppure per l'ordinamento
  };

  sortedItems = {};

  listaPrenotazioni: Prenotazione[] = []; // Inizializzazione di un array vuoto da riempire con dati di qualsiasi tipo (any)

  constructor(
    private genericTableService: GenericTableService, //il costruttore riceve un insieme di servizi e oggetti che saranno utilizzati all'interno della classe per implementare funzionalità specifiche, come la gestione della tabella, lo spinner di caricamento, l'interazione con il servizio per le prenotaz, la gestione dei dialoghi, la navigazione tra le viste dell'applicazione. La dichiarazione private indica che queste variabili sono accessibili solo all'interno della classe stessa.
    private loaderSpinnerService: LoaderSpinnerService,
    private prenotazioniService: PrenotazioniService,
    private activatedRoute: ActivatedRoute,
    private dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit() {
    this.getDataFromResolver(); //"risoluzione della route"= dati risolti e caricati prima che una route venga attivata e il componente associato venga visualizzato. (utile quando hai bisogno di caricare dati asincroni o effettuare operazioni di inizializzazione prima di mostrare una determinata vista o componente.)
  }

  getDataFromResolver() {
    //funzione che prende i dati da un route resolver che è una feature di Angular che ti permett di prendere i dati prima di navigare su una nuova rotta. Quando si vogliono avere i dati disponibili prima che l'utente navighi una nuova view
    this.totalElements = //assegna i dati di total elements, page index e lista impianti
      this.activatedRoute.snapshot.data["listaPrenotazioni"].totalElements;
    this.pageIndex =
      this.activatedRoute.snapshot.data["listaPrenotazioni"].pageIndex; //"listaPrenotazioni" è il collegamento con il resolve nel routing, stesso nome
    this.listaPrenotazioni =
      this.activatedRoute.snapshot.data["listaPrenotazioni"].content;
    if (this.listaPrenotazioni) {
      //se la lista esiste crea una nuova istanza di MatTableDataSource con i dati che ritornano dalla funzione sotto getMappedDataSource
      this.dataSource = new MatTableDataSource<any>( //l'if serve a dirgli "se non è vuoto allora procedi con il codice qui dentro {}", se invece è nmull this.dataSource rimane invariato a quello che già era
        this.getMappedDataSource(this.listaPrenotazioni) // getMappedDataSource è una funzione di MatTableDataSource e tornerà un array di oggetti (lista prenot) che passera al costruttore di MatTableDataSource
      );
      console.log(this.dataSource);
    }
  } //activatedRoute.snapshot.data ritorna i dati che sono nella route resolve in questo caso i dati dentro l'oggetto ListaPrenotazioni

  changePage(event: any) {
    //gestisce l'evento di cambio pagina e viene attivato da un evento (ad esempio clic su avanti e indietro pagina). effettua richieste asincrione al service tramite il blocco next, altrimenti error
    this.loaderSpinnerService.show();
    this.prenotazioniService //chiamata del metodo getListaPrenotazioni nel service che prende dal back una lista con un numero di elementi per pagina e il numero di pagina corernte come paramentri
      .getListaPrenotazioni(INPUT_CONSTANT.pageSize, event.number) //è un observable
      .subscribe({
        //metodo degli observable
        next: (res) => {
          //res è la risposta del servizio
          this.totalElements = res.totalElements;
          this.pageIndex = event.number; //numero della pagina
          if (res.content) {
            //se ci sono dati nella risposta
            this.listaPrenotazioni = res;

            //aggiorna la lista con i dati della risposta
            this.dataSource = new MatTableDataSource<any>(
              this.getMappedDataSource(this.listaPrenotazioni)
            );
          }
          this.genericTableService.emitFilteringStatus(false);
          this.loaderSpinnerService.hide();
        },
        error: () => this.loaderSpinnerService.hide(),
      });
  }

  getMappedDataSource(toMap: any[]) {
    //riceve un array di oggetti e restituisce un array di oggetti. Ogni oggetto del nuovo array è una trasformazione di quello vecchio. L'obiettivo principale del metodo: la creazione di una rappresentazione mappata degli oggetti in input, con l'aggiunta di nuove proprietà e la definizione di azioni specifiche (come modifica ed eliminazione).
    return toMap.map((r) => {
      //il metodo map itera su ogni elemento dell'array eseguendo la logica fornita nella funzione di call back
      const action = [
        //array con due oggetti rappresentanti due azioni: modifica e elimina. Ogni azione è rappresentata da un oggetto e le sue proprietà
        {
          title: LABEL_CONSTANT.modifica,
          icon: ICON_CONSTANT.edit,
          type: "icon",
          callback: () => this.modificaPrenotazione(r.id), //una funzione di callback che viene eseguita quando l'azione viene attivata. In questo caso, la funzione this.modificaPrenotazione
        },
        {
          title: LABEL_CONSTANT.elimina,
          icon: ICON_CONSTANT.delete,
          type: "icon",
          callback: () => this.eliminaPrenotazione(r.id), //id uguale al back
        },
      ];
      console.log(r.idPista);
      console.log(r.idUser);

      return {
        idPrenotazione: r.idPrenotazione, //primo valore, nome = quello che ho nel front, secondo valore quello che ho nel json (nel nostro caso il json viene generato in automatico prendendo i dati dal back quindi i nomi sono gli stessi del DTO)
        select: false,
        DataInizio: r.dataInizio,
        DataFine: r.dataFine,
        nomePista: r.idPista ? r.idPista.nome : "-",
        nomeUtente: r.idUser ? r.idUser.username : "-",
        action: action,
      };
    });
  }

  modificaPrenotazione(id: number) {
    this.dialog
      .open(InserisciPrenotazioneComponent, {
        width: "660px",
        height: "600px",
        disableClose: true, //la finestra di pop up non può chiudersi se clicchi fuori
      })
      .componentInstance.isModificaSet(id);
  }

  eliminaPrenotazione(id: number) {
    //Viene chiamato il metodo deletePrenotazione(id) del Service. Questo metodo fa una richiesta di eliminazione al backend per rimuovere la prenotazione con l'ID specificato.
    console.log("elimina prenotazione" + id);
    this.prenotazioniService.deletePrenotazione(id).subscribe({
      // La chiamata è asincrona e utilizza il metodo subscribe per gestire gli eventi successivi.
      next: () => {
        this.listaPrenotazioni = this.listaPrenotazioni.filter(
          //Aggiorna la lista locale delle prenotazioni rimuovendo la prenotazione con l'ID specificato
          (prenotazione) => prenotazione.id !== id
        );
        window.location.reload();
      },
      error: (error) => {
        console.log("errore durante l'eliminazione della prenotazione", error);
      },
    });
  }
}
