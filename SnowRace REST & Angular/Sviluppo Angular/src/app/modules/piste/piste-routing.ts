import { Routes } from "@angular/router";
import { inject } from "@angular/core";
import { PistaService } from "src/app/services/pista.service";

export const routes: Routes = [
  {
    path: "",
    title: "Piste",
    loadComponent: () =>
      import("./components/piste/piste.component").then(
        (m) => m.PistaComponent
      ),

    children: [
      {
        path: "",
        redirectTo: "getall",
        pathMatch: "full",
      },
      {
        path: "getall",
        title: "getall", // Il title viene utilizzato per mostrare su broswer la pagina in cui siamo,
        // Se guardiamo la sezione delle pagine ( Sopra la barra di ricerca dell'url ) vedremo che ci sarà scritto "Formazione - Login"
        loadComponent: () =>
          import("./components/piste-getall/piste-getall.component"),
        resolve: {
          listaPiste: () => inject(PistaService).resolveListaPiste(),
        },
      },
      {
        path: "getAllPersonalizzata",
        title: "Le tue piste",
        loadComponent: () =>
          import("./components/piste-getall/piste-getall.component"),
        resolve: {
          listaPiste: () =>
            inject(PistaService).resolveListaPisteAdmin(),
        },
      },
      {
        path: "",
        title: "",
        loadComponent: () =>
          import("./components/piste-getall/piste-getall.component"),
      },
    ],
  },
];
