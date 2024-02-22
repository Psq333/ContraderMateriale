import { Routes } from "@angular/router";
import AppLayoutComponent from "./components/app-layout/app-layout.component";

export const routes: Routes = [
  {
    path: "",
    component: AppLayoutComponent,
    children: [
      {
        path: "noleggio",
        loadChildren: () =>
          import("../noleggio/noleggio-routing").then((m) => m.routes),
      },
      {
        path: "nuovaPrenotazione",
        loadChildren: () =>
          import("./../impianto/impianto-routing").then((m) => m.routes),
      },
      {
        path: "storico",
        loadComponent: () =>
          import(
            "../prenotazione/filtro-prenotazione/filtro-prenotazione.component"
          ).then((m) => m.FiltroPrenotazioneComponent),
      },
      {
        path: "prenotazioni",
        loadChildren: () =>
          import("./../prenotazione/prenotazione-routing").then(
            (m) => m.routes
          ),
      },
      {
        path: "",
        redirectTo: "",
        pathMatch: "full",
      },
      {
        path: "profilo",
        loadChildren: () =>
          import("../profilo/profilo-routing").then((m) => m.routes),
      },
      { path: "**", redirectTo: "utenti", pathMatch: "full" },
    ],
  },
];
