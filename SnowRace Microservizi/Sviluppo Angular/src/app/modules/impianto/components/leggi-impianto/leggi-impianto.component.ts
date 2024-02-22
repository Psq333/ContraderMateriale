import { Component } from '@angular/core';
import { AngularMaterialModule } from "src/app/utils";
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ImpiantiService } from 'src/app/services/impianti.service';
import { FormBuilder } from '@angular/forms';
import { Input } from '@angular/core';

@Component({
  selector: 'app-leggi-impianto',
  standalone: true,
  imports: [ AngularMaterialModule, ReactiveFormsModule],
  templateUrl: './leggi-impianto.component.html',
  styleUrls: ['./leggi-impianto.component.scss']
})
export class LeggiImpiantoComponent {
  inserimentoImpianto: FormGroup;
  @Input() id:any;

  constructor(
    private impiantiService:ImpiantiService,
    private fb: FormBuilder
  ){
    this.inserimentoImpianto = this.fb.group({
      id: [""],
      nome: [""],
      descrizione: [""],
      luogo: [""],
      amministratore: [""],
    });
  }

  ngOnInit(){
    this.impiantiService.readImpianto(this.id).subscribe({
      next: (res: any) => {
        this.inserimentoImpianto.patchValue({
          id: res.id,
          nome: res.nome,
          descrizione: res.descrizione,
          luogo: res.luogo,
          amministratore: res.amministratore.id,
        });
      },
    });
  }


  }
