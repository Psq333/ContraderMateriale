import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { AngularMaterialModule } from "src/app/utils";
import {
  DASHBOARD_HEADER,
  ICON_CONSTANT,
  INPUT_CONSTANT,
  LABEL_CONSTANT,
  TABLE_COLUMNS,
} from "src/app/constants";
import { SetTextByUrlPipe } from "src/app/pipes";
import { GenericButtonComponent } from "src/app/shared/button/generic-button/generic-button.component";
import { LoginService } from "src/app/services";
import { PistaService } from "src/app/services/pista.service";
import { PrenotazioniService } from "src/app/services/prenotazioni.service";
import { FormGroup, FormBuilder } from "@angular/forms";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { GenericTableComponent } from "src/app/shared/generic";
import { MatTableDataSource } from "@angular/material/table";

@Component({
  selector: "app-prenotazione",
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    SetTextByUrlPipe,
    GenericButtonComponent,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    GenericTableComponent,
  ],
  templateUrl: "./filtro-prenotazione.component.html",
  styleUrls: ["./filtro-prenotazione.component.scss"],
})
export class FiltroPrenotazioneComponent {
  usertype: string = "";
  listaPista: any[] = [];
  listaFiltrata: any[] = [];
  title: string = "";
  size = INPUT_CONSTANT.pageSize;
  dashboardConstant: any = DASHBOARD_HEADER;

  cellHeadTypes = {
    select: "checkout",
    id: "sort",
  };

  totalElements!: number; // "!" = variabile che non sarà mai nulla o undefined anche se potrebbe all'inizio in fase di compilazione
  dataSource!: any;
  displayedColumns: string[] = TABLE_COLUMNS.prenotazioni;
  pageIndex = INPUT_CONSTANT.pageNumber;
  filter: boolean;
  myForm: FormGroup;

  constructor(
    private loginService: LoginService,
    private pistaService: PistaService,
    private prenService: PrenotazioniService,
    private fb: FormBuilder
  ) {
    this.filter = false;
    this.myForm = this.fb.group({
      dataInizio: "",
      idPista: "",
    });
  }

  setFilter(value: boolean) {
    this.filter = value;
  }

  ngOnInit() {
    this.usertype = this.loginService.getUtenteSessione().usertype;
    this.pistaService.getTuttePiste().subscribe({
      next: (res: any) => {
        this.listaPista = res;
      },
    });
  }

  submitForm() {
    //inerire la logica del filtro "se filter è true mandi la data altrimenti la pista "
    console.log("quaaaaaaaaaaaaaaaaaaa");
    var valori = this.myForm.value;
    console.log(valori.dataInizio);
    if (this.filter === true) {
      this.prenService.filteredDates(25, 0, valori.dataInizio).subscribe({
        next: (res: any) => {
          
          this.totalElements = res.totalElements;
          this.pageIndex = res.pageIndex;

          if (res.content) {
            //se la lista esiste crea una nuova istanza di MatTableDataSource con i dati che ritornano dalla funzione sotto getMappedDataSource
            this.dataSource = new MatTableDataSource<any>( //l'if serve a dirgli "se non è vuoto allora procedi con il codice qui dentro {}", se invece è nmull this.dataSource rimane invariato a quello che già era
              this.getMappedDataSource(res.content) // getMappedDataSource è una funzione di MatTableDataSource e tornerà un array di oggetti (lista prenot) che passera al costruttore di MatTableDataSource
            );
            console.log(this.dataSource);
          }
        },
      });
    } else {
      this.prenService
        .filteredPiste(25, 0, this.myForm.value.idPista)
        .subscribe({
          next: (res: any) => {
            this.totalElements = res.totalElements;
            this.pageIndex = res.pageIndex;
            if (res.content) {
              //se la lista esiste crea una nuova istanza di MatTableDataSource con i dati che ritornano dalla funzione sotto getMappedDataSource
              this.dataSource = new MatTableDataSource<any>( //l'if serve a dirgli "se non è vuoto allora procedi con il codice qui dentro {}", se invece è nmull this.dataSource rimane invariato a quello che già era
                this.getMappedDataSource(res.content) // getMappedDataSource è una funzione di MatTableDataSource e tornerà un array di oggetti (lista prenot) che passera al costruttore di MatTableDataSource
              );
              console.log(this.dataSource);
            }
          },
        });
    }
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
          //callback: () => this.modificaPrenotazione(r.idPrenotazione), //una funzione di callback che viene eseguita quando l'azione viene attivata. In questo caso, la funzione this.modificaPrenotazione
        },
        {
          title: LABEL_CONSTANT.elimina,
          icon: ICON_CONSTANT.delete,
          type: "icon",
          //callback: () => this.eliminaPrenotazione(r.idPrenotazione),
        },
      ];
      if (this.loginService.getUtenteSessione().usertype === "AMMINISTRATORE"){
      return {
        idPrenotazione: r.idPrenotazione, //primo valore, nome = quello che ho nel front, secondo valore quello che ho nel json (nel nostro caso il json viene generato in automatico prendendo i dati dal back quindi i nomi sono gli stessi del DTO)
        select: false,
        DataInizio: r.dataInizio,
        DataFine: r.dataFine,
        nomePista: r.pista.nome,
        nomeUtente: r.user.username,
        action: action,
      };
    }       else {
      if (!(r.user && this.loginService.getUtenteSessione().username === r.user.username)){
        return undefined;
      }
      return {
        idPrenotazione: r.idPrenotazione, //primo valore, nome = quello che ho nel front, secondo valore quello che ho nel json (nel nostro caso il json viene generato in automatico prendendo i dati dal back quindi i nomi sono gli stessi del DTO)
        select: false,
        DataInizio: r.dataInizio,
        DataFine: r.dataFine,
        nomePista: r.pista.nome,
        nomeUtente: r.user.username,
        action: action,
      };
    }
    }).filter(item => item !== undefined);
    
  }
}
