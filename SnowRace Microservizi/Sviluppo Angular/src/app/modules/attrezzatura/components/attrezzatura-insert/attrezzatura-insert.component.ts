import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { AngularMaterialModule } from "src/app/utils";
import { CommonModule } from "@angular/common";
import { MatDialogRef } from "@angular/material/dialog";
import { AttrezzaturaService } from "src/app/services/attrezzatura.service";
import { ImpiantiService } from "src/app/services/impianti.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ElementRef } from "@angular/core";
import { ReactiveFormsModule } from "@angular/forms";
import { ImpiantoModelRequest } from "src/app/models/impianto.model";
@Component({
  selector: "app-insert-attrezzatura",
  templateUrl: "./attrezzatura-insert.component.html",
  styleUrls: ["./attrezzatura-insert.component.scss"],
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    AngularMaterialModule,
    CommonModule,
    ReactiveFormsModule,
  ],
})
export class AttrezzaturaInsertComponents {
  isModifica: boolean = false;
  listaImpianti: any[] = [];
  attrezzaturaInsert: FormGroup;

  constructor(
    private dialog: MatDialogRef<AttrezzaturaInsertComponents>,
    private attrezzaturaService: AttrezzaturaService,
    private impiantiService: ImpiantiService,
    private fb: FormBuilder
  ) {
    this.attrezzaturaInsert = this.fb.group({
      id: "",
      nome: [""],
      descrizione: [""],
      prezzo: [""],
      idImpianto: [""],
    });
  }
  ngOnInit() {
    let list = this.attrezzaturaService.getImpianti().subscribe({
      next: (res: any) => {
        this.listaImpianti = res;
      },
    });
  }

  async submitForm() {
    try {
      const formValue = this.attrezzaturaInsert.value;

      // Leggi l'impianto asincronamente
      const res = await this.impiantiService
        .readImpianto(formValue.idImpianto)
        .toPromise();

      // Aggiorna il formValue con il risultato ottenuto
      formValue.idImpianto = res;

      console.log("aaaaaa", formValue);

      // Esegui la logica di update solo dopo aver ottenuto il risultato asincrono
      if (this.isModifica) {
        await this.attrezzaturaService
          .updateAttrezzatura(formValue)
          .toPromise();
        console.log(formValue);
        window.location.reload();
      } else {
        await this.attrezzaturaService
          .insertAttrezzatura(formValue)
          .toPromise();
        window.location.reload();
      }
    } catch (error) {
      console.error("Errore durante l'inserimento dell'attrezzatura", error);
    }
  }

  setInserimento() {
    this.isModifica = false;
  }

  setModifica(id: number) {
    this.isModifica = true;

    console.log("qua");

    this.attrezzaturaService.readAttrezzatura(id).subscribe({
      next: (res: any) => {
        this.attrezzaturaInsert = this.fb.group({
          id: res.id,
          nome: res.nome,
          descrizione: res.descrizione,
          prezzo: res.prezzo,
          idImpianto: res.idImpianto.id,
        });
      },
    });
  }
  closeDialog() {
    this.dialog.close(false);
  }
}
