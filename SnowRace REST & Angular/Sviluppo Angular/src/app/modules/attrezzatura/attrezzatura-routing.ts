import { Routes } from "@angular/router";
import { inject } from "@angular/core";
import { AttrezzaturaService } from "src/app/services/attrezzatura.service";

export const routes: Routes = [
  {
    path: "",
    title: "Attrezzatura",
    loadComponent: () =>
      import("./components/attrezzatura/attrezzatura.component").then(
        (m) => m.AttrezzaturaComponent
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
        loadComponent: () => import("./components/attrezzatura-getall/attrezzatura-getall.component").then((m) => m.AttrezzaturaGetallComponent),
        resolve: {
          listaAttrezzatura: () => inject(AttrezzaturaService).resolveListaAttrezzatura(),
        },
      },
      // {
      //   path: "",
      //   title: "",
      //   loadComponent: () =>
      //     import("./components/attrezzatura-getall/attrezzatura-getall.component"),
      // },
    ],
  },
];