import { Component } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { AngularMaterialModule } from "src/app/utils";
import { DASHBOARD_HEADER } from "src/app/constants";
import { SetTextByUrlPipe } from "src/app/pipes";
import { GenericButtonComponent } from "src/app/shared/button/generic-button/generic-button.component";
import { MatDialog } from "@angular/material/dialog";
import { NoleggioInsertComponent } from "../noleggio-insert/noleggio-insert.component";

@Component({
  selector: "app-noleggio",
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    SetTextByUrlPipe,
    GenericButtonComponent,
    AngularMaterialModule,
  ],
  templateUrl: "./noleggio.component.html",
  styleUrls: ["./noleggio.component.scss"],
})
export default class NoleggioComponent {
  title: string = "";
  dashboardConstant: any = DASHBOARD_HEADER;
  constructor(private dialog: MatDialog) {}

  onButtonClick() {
    console.log("Aggiungi nuovo Noleggio.");
    this.dialog.open(NoleggioInsertComponent, {
      width: "660px",
      height: "600px",
      disableClose: true,
    });
  }
}
