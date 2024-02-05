import { inject } from "@angular/core";
import { Routes } from "@angular/router";
import { PrenotazioniService } from "src/app/services/prenotazioni.service";

export const routes: Routes = [
  {
    path: "", //percorso base della rotta prenotazione (/prenotazione)
    title: "Prenotazione",
    loadComponent: () =>
      import("./prenotazione/prenotazione.component").then(
        (m) => m.PrenotazioneComponent
      ), //caricherà quindi prenotazione.component
    children: [
      {
        path: "",
        redirectTo: "getall",
        pathMatch: "full",
      },
     
      {
        path: "getall",
        title: "Tutte le prenotazioni",
        loadComponent: () =>
          import("./getall-prenotazione/getall-prenotazione.component").then(
            (m) => m.GetallPrenotazioneComponent
          ),
        resolve: {
          //listaPrenotazioni, resolver che prende i dati dal metodo .resolveListaPrenotazioni() dentro il service
          listaPrenotazioni: () =>
            inject(PrenotazioniService).resolveListaPrenotazioni(),
        },
      }, //questo metodo ci dice: prima di navigare questa route esegui .resolveListaPrenotazioni(), il risultato mettilo in listaPrenotazioni. Arrow function
    ],
  },
];
