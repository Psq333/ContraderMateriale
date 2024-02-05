import { Routes } from "@angular/router";
import { inject } from "@angular/core";
import { NoleggioService } from "src/app/services/noleggio.service";

export const routes: Routes = [
  {
    path: "",
    title: "Noleggi",
    loadComponent: () => import("./component/noleggio/noleggio.component"),

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
          import("./component/noleggio-getall/noleggio-getall.component").then(
            (m) => m.NoleggioGetallComponent
          ),
        resolve: {
          listaNoleggi: () => inject(NoleggioService).resolveListaNoleggi(),
        },
      },
      {
        path: "getallPersonalizzata",
        title: "getallPersonalizzata", // Il title viene utilizzato per mostrare su broswer la pagina in cui siamo,
        // Se guardiamo la sezione delle pagine ( Sopra la barra di ricerca dell'url ) vedremo che ci sarà scritto "Formazione - Login"
        loadComponent: () =>
          import("./component/noleggio-getall/noleggio-getall.component").then(
            (m) => m.NoleggioGetallComponent
          ),
        resolve: {
          listaNoleggi: () => inject(NoleggioService).resolveListaNoleggiUser(),
        },
      },
      {
        path: "",
        title: "",
        loadComponent: () =>
          import("./component/noleggio-getall/noleggio-getall.component").then(
            (m) => m.NoleggioGetallComponent
          ),
      },
    ],
  },
];
