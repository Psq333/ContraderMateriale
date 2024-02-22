import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { AngularMaterialModule } from "src/app/utils";
import { CommonModule } from "@angular/common";
import { MatDialogRef } from "@angular/material/dialog";
import { NoleggioService } from "src/app/services/noleggio.service";
import { AttrezzaturaService } from "src/app/services/attrezzatura.service";
import { UtentiService } from "src/app/services/utenti.service";
import { FormBuilder, FormGroup, ReactiveFormsModule } from "@angular/forms";
import { LoginService } from "src/app/services";

@Component({
  selector: "app-insert-noleggio",
  templateUrl: "./noleggio-insert.component.html",
  styleUrls: ["./noleggio-insert.component.scss"],
  standalone: true,
  imports: [
    CommonModule,
    AngularMaterialModule,
    ReactiveFormsModule,
    FormsModule,
  ],
})
export class NoleggioInsertComponent {
  isModifica: boolean = false;
  inserimentoNoleggio: FormGroup;
  usertype: String = "";
  listAttrezzature: any[] = [];
  listaUtenti: any[] = [];
  constructor(
    private dialog: MatDialogRef<NoleggioInsertComponent>,
    private noleggioService: NoleggioService,
    private attrezzaturaService: AttrezzaturaService,
    private fb: FormBuilder,
    private utentiService: UtentiService,
    private loginService: LoginService
  ) {
    this.inserimentoNoleggio = this.fb.group({
      idnoleggio: "",
      username: [""],
      idattrezzatura: [""],
      startDate: [""],
      endDate: [""],
    });
  }

  ngOnInit() {
    this.usertype = this.loginService.getUtenteSessione().usertype;
    this.attrezzaturaService.getAttrezzatura().subscribe({
      next: (res: any) => {
        this.listAttrezzature = res;
        console.log(res);
      },
    });

    this.utentiService.resolveListaUtenti().subscribe({
      next: (res: any) => {
        if (res && res.content && Array.isArray(res.content)) {
          this.listaUtenti = res.content.map((utente: any) =>
            utente.username ? utente : { ...utente, username: "-" }
          );
          console.log(this.listaUtenti);
        } else {
          console.error(
            "La struttura dell'oggetto restituito non è quella prevista:",
            res
          );
        }
      },
    });

    if (this.usertype === "amministratore") {
      console.log("amministratore");
    } else {
      let username = this.loginService.getUtenteSessione().username;
      this.utentiService.getUtenteByUsername(username).subscribe({
        next: (res: any) => {
          console.log("Questo è il dto dell admin");
          console.log(res);
          this.inserimentoNoleggio.patchValue({
            username: res.username.username,
          });
        },
      });
    }
  }

  submitForm() {
    const formValue = this.inserimentoNoleggio.value;
    var inv = formValue;
    if (this.isModifica) {
      this.noleggioService.modificaNoleggio(formValue).subscribe({
        next: () => {
          window.location.reload();
        },
        error: (error: any) => {
          console.error("Errore durante l'inserimento del noleggio", error);
        },
      });
    } else {
      console.log("inserimento", formValue.idattrezzatura);
      console.log("inserimento", formValue);
      this.noleggioService.insertNoleggio(formValue).subscribe({
        next: () => {
          console.log("POST Inserimento", formValue);
          window.location.reload();
        },
        error: (error: any) => {
          console.error(
            "Errore durante l'inserimento del noleggioooooo",
            error
          );
        },
      });
    }
  }

  setInserimento() {
    this.isModifica = false;
  }

  setModifica(id: number) {
    this.isModifica = true;

    this.noleggioService.readNoleggio(id).subscribe({
      next: (res: any) => {
        console.log("Response from noleggioService:", res);

        //const defaultUsername = res.username || "-";
        //console.log("Default Username:", defaultUsername);

        this.inserimentoNoleggio.patchValue({
          idnoleggio: res.idnoleggio,
          username: res.username ? res.username : "-",
          idattrezzatura: res.idattrezzatura ? res.idattrezzatura.id : "-",
          startDate: res.startDate,
          endDate: res.endDate,
        });
      },
      error: (error: any) => {
        console.error("Error fetching data:", error);
      },
    });
  }

  closeDialog() {
    this.dialog.close(false);
  }
}
