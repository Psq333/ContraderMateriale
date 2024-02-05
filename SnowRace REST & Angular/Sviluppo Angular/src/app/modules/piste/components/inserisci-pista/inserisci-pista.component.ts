import { Component } from "@angular/core";
import { FormBuilder, FormGroup, ReactiveFormsModule } from "@angular/forms";
import { AngularMaterialModule } from "src/app/utils";
import { CommonModule } from "@angular/common";
import { MatDialogRef } from "@angular/material/dialog";
import { PistaService } from "src/app/services/pista.service";
import { FormsModule } from "@angular/forms";
import { LoginService } from "src/app/services";

@Component({
  selector: "app-inserisci-pista",
  templateUrl: "./inserisci-pista.component.html",
  styleUrls: ["./inserisci-pista.component.scss"],
  standalone: true,
  imports: [
    CommonModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    FormsModule,
  ],
})
export class InserisciPistaComponent {
  isModifica: boolean = false;
  listaImpianti: any[] = [];
  inserimentoPista: FormGroup;

  constructor(
    private dialog: MatDialogRef<InserisciPistaComponent>,
    private pistaService: PistaService,
    private fb: FormBuilder,
    private loginService: LoginService
  ) {
    this.inserimentoPista = this.fb.group({
      idPista: "",
      nome: [""],
      difficolta: [""],
      prezzo: [""],
      prenotazioniMax: [""],
      impianto: "",
    });
  }

  ngOnInit() {
    /*
    #   Ho fatto questo controllo in modo tale che se l'utente in sessione è un amministratore mi restituisce  #
    #   nel menu a tendina tutti gli impianti , mentre se è un admin mi restituisce solo i suoi impianti;      #
    */
    let username = this.loginService.getUtenteSessione().username;
    if (this.loginService.getUtenteSessione().usertype === "ADMIN") {
      let list = this.pistaService.getImpiantiAdmin(username).subscribe({
        next: (res: any) => {
          this.listaImpianti = res;
        },
      });
    } else {
      let list = this.pistaService.getImpianti().subscribe({
        next: (res: any) => {
          this.listaImpianti = res;
        },
      });
    }
  }

  submitForm() {
    const formValue = this.inserimentoPista.value;
    console.log(formValue);
    if (this.isModifica) {
      this.pistaService.modificaPista(formValue).subscribe({
        next: () => {
          console.log(formValue);
          window.location.reload();
        },
        error: (error: any) => {
          console.error("Errore durante l'inserimento della pista", error);
        },
      });
    } else {
      this.pistaService.insertPista(formValue).subscribe({
        next: () => {
          window.location.reload();
        },
        error: (error: any) => {
          console.error("Errore durante l'inserimento della pista", error);
        },
      });
    }
  }

  setInserimento() {
    this.isModifica = false;
  }

  setModifica(id: number) {
    this.isModifica = true;

    console.log("qua");
    this.pistaService.readPista(id).subscribe({
      next: (res: any) => {
        this.inserimentoPista = this.fb.group({
          idPista: res.idPista,
          nome: res.nome,
          difficolta: res.difficolta,
          prezzo: res.prezzo,
          prenotazioniMax: res.prenotazioniMax,
          impianto: res.impianto,
        });
      },
    });
  }

  closeDialog() {
    this.dialog.close(false);
  }
}
