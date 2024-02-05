import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { AngularMaterialModule } from "src/app/utils";
import { SetTextByUrlPipe } from "src/app/pipes";
import { MatDialog } from "@angular/material/dialog";
import { DASHBOARD_HEADER } from "src/app/constants";
import { InserisciPistaComponent } from "../inserisci-pista/inserisci-pista.component";
import { GenericButtonComponent } from "src/app/shared/button/generic-button/generic-button.component";

@Component({
  selector: "app-pista",
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    SetTextByUrlPipe,
    GenericButtonComponent,
    AngularMaterialModule,
  ],
  templateUrl: "./piste.component.html",
  styleUrls: ["./piste.component.scss"],
})
export class PistaComponent {

  isModifica: boolean = false;
  title: string = "";
  dashboardConstant: any = DASHBOARD_HEADER;
  constructor(private dialog: MatDialog) {}

  onButtonClicks() {
    console.log("Inserisci pista");
    this.dialog.open(InserisciPistaComponent, {
      width: "660px",
      height: "550px",
      disableClose: true,
    }).componentInstance.setInserimento();
  }
}
