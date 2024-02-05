import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AngularMaterialModule } from 'src/app/utils';
import {
  DASHBOARD_HEADER,
} from 'src/app/constants';
import { SetTextByUrlPipe } from 'src/app/pipes';
import { GenericButtonComponent } from 'src/app/shared/button/generic-button/generic-button.component';
import { AttrezzaturaInsertComponents } from '../attrezzatura-insert/attrezzatura-insert.component';
import { MatDialog } from '@angular/material/dialog';
import { AttrezzaturaService } from 'src/app/services/attrezzatura.service';

@Component({
  selector: 'app-attrezzatura',
  standalone: true,
  imports: [CommonModule,
     RouterModule,
     SetTextByUrlPipe,
     GenericButtonComponent,
     AngularMaterialModule,],
  templateUrl: './attrezzatura.component.html',
  styleUrls: ['./attrezzatura.component.scss']
})
export class AttrezzaturaComponent {
  title: string = "";
  dashboardConstant: any = DASHBOARD_HEADER;
  constructor(private dialog: MatDialog) {}

  onButtonClicks() {
    console.log("Inserisci attrezzatura");
    this.dialog.open(AttrezzaturaInsertComponents, {
      width: "660px",
      height: "550px",
      disableClose: true,
    }).componentInstance.setInserimento();
  }
}
