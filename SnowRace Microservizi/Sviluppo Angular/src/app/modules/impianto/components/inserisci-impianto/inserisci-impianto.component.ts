import { Component, Input } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AngularMaterialModule } from "src/app/utils";
import { CommonModule } from "@angular/common";
import { MatDialogRef } from "@angular/material/dialog";
import { ImpiantiService } from "src/app/services/impianti.service";
import { ActivatedRoute } from "@angular/router";
import { inject } from "@angular/core";
import { UtentiService } from "src/app/services/utenti.service";
import { ReactiveFormsModule } from "@angular/forms";
import { UtentiComponent } from "src/app/modules/utenti/components/utenti/utenti.component";
import { ElementRef } from "@angular/core";
import { LoginService } from "src/app/services";
import { Observable } from "rxjs";

@Component({
  selector: "app-inserisci-impianto",
  standalone: true,
  imports: [CommonModule, AngularMaterialModule, ReactiveFormsModule],
  templateUrl: "./inserisci-impianto.component.html",
  styleUrls: ["./inserisci-impianto.component.scss"],
})
export class InserisciImpiantoComponent {
  isUserAdmin: boolean = false;
  listaAdmin: any[] = [];
  inserimentoImpianto: FormGroup;
  amm: any;
  isModifica: boolean = false;
  submitButtonDisabled: boolean = true;

  checkInputs() {
    this.submitButtonDisabled = this.inserimentoImpianto.invalid;
  }

  constructor(
    private dialog: MatDialogRef<InserisciImpiantoComponent>,
    private impiantiService: ImpiantiService,
    private activatedRoute: ActivatedRoute,
    private service: UtentiService,
    private fb: FormBuilder,
    private el: ElementRef,
    private loginService: LoginService
  ) {
    this.inserimentoImpianto = this.fb.group({
      id: [""],
      nome: ["", Validators.required],
      descrizione: ["", Validators.required],
      luogo: ["", Validators.required],
      amministratore: ["", Validators.required],
    });
    const amministratoreControl = this.inserimentoImpianto.get('amministratore');
    if (amministratoreControl) {
      amministratoreControl.clearValidators();
      amministratoreControl.updateValueAndValidity();
    }
  } 

  ngOnInit() {
    this.inserimentoImpianto.valueChanges.subscribe(() => {
      this.checkInputs();
    });

    let list = this.service.getAdmin().subscribe({
      next: (res: any) => {
        this.listaAdmin = res;
        if (this.loginService.isAdmin()) {
          this.isUserAdmin = false;
        } else {
          this.isUserAdmin = true;
        }
        if (this.listaAdmin?.length ?? 0 > 0) {
          this.inserimentoImpianto.patchValue({
            amministratore: this.listaAdmin[0].id,
          });
        } else {
          this.disabilitaBottone();
          this.listaAdmin = [
            {
              id: 0,
              username: "Mancano degli Admin, inserimento non consentito",
            },
          ];
          this.inserimentoImpianto.patchValue({
            amministratore: this.listaAdmin[0].id,
          });
        }
      },
    });
  }

  async submitForm() {
    if (this.loginService.isAdmin()) {
      this.inserimentoLatoAdmin();
    } else {
      this.inserimentoModificaAmministratore();
    }
  }

  closeDialog() {
    this.dialog.close(false);
  }

  async getAdmin(id: number): Promise<any> {
    try {
      const response = await this.service.getUtente(id).toPromise();
      this.amm = response;
      return response;
    } catch (error) {
      console.log("erroreeeee");
    }
  }

  isModificaSet(idImpianto: number) {
    this.isModifica = true;

    this.impiantiService.readImpianto(idImpianto).subscribe({
      next: (res: any) => {
        this.inserimentoImpianto = this.fb.group({
          id: res.id,
          nome: res.nome,
          descrizione: res.descrizione,
          luogo: res.luogo,
          amministratore: res.amministratore ? res.amministratore.id: '-',
        });
      },
    });
  }

  disabilitaBottone() {
    const bottone = this.el.nativeElement.querySelector("#submit");
    this.inserimentoImpianto.get("amministratore")?.disable();
    if (bottone) {
      bottone.disabled = true;
    }
  }

  inserimentoLatoAdmin() {
    var observable;
    let utente = this.service
    .getUtenteByUsername(this.loginService.getUtenteSessione().username)
    .subscribe({
    next: (res) => {
      //INSERISCO IL VALORE DELL'ADMIN ATTUALE DENTRO IL JSON CHE POI PASSERO'
      console.log(res.id);
    this.inserimentoImpianto.patchValue({
      amministratore: res.id,
    });
    console.log("LATO ADMIN");
    console.log(this.inserimentoImpianto.value);

          observable = this.impiantiService.inserisciImpianto(
            this.inserimentoImpianto.value
          );
          observable.subscribe({
            next: () => {
              window.location.reload();
            },
            error: (error: any) => {
              console.log("errore durante l'inserimento dell'impianto", error);
            },
          });
        },
      });
    return observable;
  }

  inserimentoModificaAmministratore() {
    var observable;
    console.log("Inserimento Impianto: ",this.inserimentoImpianto.value);
    if(this.isModifica === true){
      this.isModifica = false;
      observable = this.impiantiService.updateImpianto(
        this.inserimentoImpianto.value
      );
    } else {
      observable = this.impiantiService.inserisciImpianto(
        this.inserimentoImpianto.value
      );
    }
    observable.subscribe({
      next: () => {
        window.location.reload();
      },
      error: (error) => {
        console.log("errore durante l'inserimento dell'impianto", error);
      },
    });
  }
}
