import { Routes } from "@angular/router";
import AppLayoutComponent from "./components/app-layout/app-layout.component";

export const routes: Routes = [
  {
    path: "",
    component: AppLayoutComponent, //definizione della rotta principale
    children: [
      {
        path: "impianti",
        loadChildren: () =>
          import("../impianto/impianto-routing").then((m) => m.routes),
      },
      {
        path: 'attrezzatura',
        loadChildren: () => import('../attrezzatura/attrezzatura-routing').then(m => m.routes),
      },
      {
         path: 'prenotazioni',
         loadChildren: () => import('../prenotazione/prenotazione-routing').then(m => m.routes),
       },
      {
        path: "",
        redirectTo: "utenti",
        pathMatch: "full",
      },
      {
        path: "utenti",
        loadChildren: () =>
          import("../utenti/utenti-routing").then((m) => m.routes),
      },
      {
        path: "piste",
        loadChildren: () =>
          import("../piste/piste-routing").then((m) => m.routes),
      },
      {
        path: "noleggi",
        loadChildren: () =>
          import("../noleggio/noleggio-routing").then((m) => m.routes),
      },
      {
        path: "anagrafica",
        loadChildren: () => import('../anagrafica/anagrafica-routing').then(m => m.routes),
      },
      {
        path:"profilo",
        loadChildren: () =>
          import("../profilo/profilo-routing").then((m) => m.routes),
      },
      { path: '**', redirectTo: 'utenti', pathMatch: 'full' },
    ],
  },
];
