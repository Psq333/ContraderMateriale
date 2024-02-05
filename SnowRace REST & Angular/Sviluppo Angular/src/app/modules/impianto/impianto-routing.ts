import { inject } from "@angular/core";
import { Routes } from "@angular/router";
import { ImpiantiService } from "src/app/services/impianti.service";
import { UtentiService } from "src/app/services/utenti.service";

export const routes: Routes = [
  {
    path: "",
    title: "Impianto",
    loadComponent: () => import("./components/impianto/impianto.component"),
    children: [
      {
        path: "",
        redirectTo: "getall",
        pathMatch: "full",
      },
      {
        path: "getAllPersonalizzata",
        title: "I Tuoi Impianti",
        loadComponent: () =>
          import("./components/impianto-getall/impianto-getall.component").then(
            (m) => m.ImpiantoGetallComponent
          ),
        resolve: {
          listaImpianti: () =>
            inject(ImpiantiService).resolveListaImpiantiAdmin(),
        },
      },
      {
        path: "getall",
        title: "Tutti gli impianti",
        loadComponent: () =>
          import("./components/impianto-getall/impianto-getall.component").then(
            (m) => m.ImpiantoGetallComponent
          ),
        resolve: {
          listaImpianti: () => inject(ImpiantiService).resolveListaImpianti(),
        },
      },
      {
        path: "insert",
        loadComponent: () =>
          import(
            "./components/inserisci-impianto/inserisci-impianto.component"
          ).then((m) => m.InserisciImpiantoComponent),
        resolve: {
          listaImpianti: () => inject(ImpiantiService).resolveListaImpianti(),
        },
      },
    ],
  },
];
