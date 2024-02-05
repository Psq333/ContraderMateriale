import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AngularMaterialModule } from 'src/app/utils';
import {
  DASHBOARD_HEADER,
} from 'src/app/constants';
import { SetTextByUrlPipe } from 'src/app/pipes';
import { GenericButtonComponent } from 'src/app/shared/button/generic-button/generic-button.component';
import { MatDialog } from '@angular/material/dialog';
import { InserisciPrenotazioneComponent } from '../inserisci-prenotazione/inserisci-prenotazione.component';
import { UtentiService } from 'src/app/services/utenti.service';

//component generico con il bottone per l'inserimento della prenotazione e il titolo

@Component({
  selector: 'app-prenotazione',
  standalone: true,
  imports: [CommonModule,
     RouterModule,
     SetTextByUrlPipe,
     GenericButtonComponent,
     AngularMaterialModule],
  templateUrl: './prenotazione.component.html',
  styleUrls: ['./prenotazione.component.scss']
})
export class PrenotazioneComponent {
  title: string = ''; //inizializzo una stringa vuota per il titolo
  dashboardConstant: any = DASHBOARD_HEADER; //istanza dell'header della mia dashboard (tutte le prenotazioni)
  constructor(
    private dialog: MatDialog,
    private service: UtentiService
    ) {}

  onButtonClick(){
    this.dialog.open(InserisciPrenotazioneComponent, {
      width: '660px', 
      height: '600px',
      disableClose: true,
    });
  }

}
