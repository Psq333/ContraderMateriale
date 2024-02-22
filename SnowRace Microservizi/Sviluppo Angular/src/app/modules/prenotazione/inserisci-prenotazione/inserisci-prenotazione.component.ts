import { Component, Input } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AngularMaterialModule } from "src/app/utils";
import { CommonModule } from "@angular/common";
import { MatDialogRef } from "@angular/material/dialog";
import { PrenotazioniService } from "src/app/services/prenotazioni.service";
import { ActivatedRoute } from "@angular/router";
import { inject } from "@angular/core";
import { UtentiService } from "src/app/services/utenti.service";
import { PistaService } from "src/app/services/pista.service";
import { ReactiveFormsModule } from "@angular/forms";
import { UtentiComponent } from "src/app/modules/utenti/components/utenti/utenti.component";
import { ElementRef } from "@angular/core";
import { LoginService } from "src/app/services";

@Component({
  selector: "app-inserisci-prenotazione",
  standalone: true,
  imports: [CommonModule, AngularMaterialModule, ReactiveFormsModule],
  templateUrl: "./inserisci-prenotazione.component.html",
  styleUrls: ["./inserisci-prenotazione.component.scss"],
})
export class InserisciPrenotazioneComponent {
  inserimentoPrenotazione: FormGroup;
  ut: any;
  pist: any;
  isModifica: boolean = false;
  listaUtenti: any[] = [];
  listaPiste: any[] = [];
  constructor(
    private dialog: MatDialogRef<InserisciPrenotazioneComponent>,
    private prenotazioniService: PrenotazioniService,
    private activatedRoute: ActivatedRoute,
    private service: UtentiService,
    private Pistaservice: PistaService,
    private fb: FormBuilder,
    private el: ElementRef,
    private loginService: LoginService
  ) {
    this.inserimentoPrenotazione = this.fb.group({
      id: [""], //stesso nome id nell'html e anche stesso nome degli attributi nel back DTO 
      idUser: [""],
      dataInizio: [""],
      dataFine: [""],
      idPista: ["", Validators.required],
    });
    this.setUtente();
  }

  ngOnInit() {
    this.service.getTuttiUtenti().subscribe({
      next: (res: any) => {
        this.listaUtenti = res;
        console.log(res);
      },
    });
    this.Pistaservice.getTuttePiste().subscribe({
      next: (res: any) => {
        this.listaPiste = res;
        console.log(res);
      },
    });
    console.log(this.listaPiste);
  }

  async submitForm() {
    const formValue = this.inserimentoPrenotazione.value;
    let k = await this.getUsersAndPiste(
      formValue.idUser,
      formValue.idPista
    ).then(() => {
      formValue.idUser = this.ut;
      formValue.idPista = this.pist;
      console.log(formValue);
      var observable;
      if (this.isModifica === true) {
        console.log(this.isModifica);
        this.isModifica = false;

        observable = this.prenotazioniService.updatePrenotazione(formValue);
        console.log("quedtoqqqqqqqqqqqqqqqqqqqqqqq")
        console.log(formValue);
      } else {
        console.log(this.isModifica);
        observable = this.prenotazioniService.inserisciPrenotazione(formValue);
      }
      observable.subscribe({
        next: () => {
          window.location.reload();
        },
        error: (error) => {
          console.log("errore durante l'inserimento della prenotazione", error);
        },
      });
    });
  }

  closeDialog() {
    console.log("closeDialog in InserisciPrenotazioneComponent");
    this.dialog.close(false);
  }

  async getUsersAndPiste(id: number, idPista: number): Promise<any> {
    try {
      var response = await this.service.getUtente(id).toPromise();
      this.ut = response;
      response = await this.Pistaservice.readPista(idPista).toPromise();
      this.pist = response;
      return response;
    } catch (error) {
      console.log("erroreeeee");
    }
  }

  isModificaSet(idPrenotazione: number) {
    this.isModifica = true;

    this.prenotazioniService.readPrenotazione(idPrenotazione).subscribe({
      next: (res: any) => {
        this.inserimentoPrenotazione = this.fb.group({
          id: res.id, //il primo è quello di form value a riga 40, il secondo è quello che ho nel dto del back
          dataInizio: res.dataInizio,
          dataFine: res.dataFine,
          idPista: res.idPista ? res.idPista.id : 0,
          idUser: res.idUser ? res.idUser.id : 0,
        });
      },
    });
  }

  disabilitaBottone() {
    const bottone = this.el.nativeElement.querySelector("#submit");
    this.inserimentoPrenotazione.get("amministratore")?.disable();
    if (bottone) {
      bottone.disabled = true;
    }
  }

  setPista(idPista: number) {
    this.inserimentoPrenotazione.patchValue({
      idPista: idPista,
    });
  }

  //----------------------------UTENTE----------------------------
  isUtente: boolean = false;
  userType: any;
  setUtente() {
    var utente = this.loginService.getUtenteSessione();
    this.userType = utente.usertype;
    if (this.userType === "user") {
      this.isUtente = true;
      this.service.getUtenteByUsername(utente.username).subscribe({
        next: (res: any) => {
          this.inserimentoPrenotazione.patchValue({
            idUser: res.id,
          });
          console.log("weee ", this.inserimentoPrenotazione.value);
        },
      });
    }
  }
}
