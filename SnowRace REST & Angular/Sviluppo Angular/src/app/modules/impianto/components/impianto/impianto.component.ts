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
import { InserisciImpiantoComponent } from '../inserisci-impianto/inserisci-impianto.component';
import { UtentiService } from 'src/app/services/utenti.service';
import {
  LoginService,
} from "src/app/services";


@Component({
  selector: 'app-impianto',
  standalone: true,
  imports: [CommonModule,
     RouterModule,
     SetTextByUrlPipe,
     GenericButtonComponent,
     AngularMaterialModule],
  templateUrl: './impianto.component.html',
  styleUrls: ['./impianto.component.scss']
})
export default class ImpiantoComponent {
  userType !: 'USER'|'ADMIN' | 'AMMINISTRATORE';
  isUtente : boolean = true;
  title: string = '';
  dashboardConstant: any = DASHBOARD_HEADER;
  constructor(
    private dialog: MatDialog,
    private service: UtentiService,
    private loginService:LoginService
    ) {}

  ngOnInit() {
    this.setUtente();
  }

  onButtonClick(){
    this.dialog.open(InserisciImpiantoComponent, {
      width: '660px', 
      height: '600px',
      disableClose: true,
    });
  }

  setUtente(){
    var utente = this.loginService.getUtenteSessione();
    this.userType = utente.usertype;
    if(this.userType==="USER")
      this.isUtente = false;
    console.log(this.isUtente);
  }
}
