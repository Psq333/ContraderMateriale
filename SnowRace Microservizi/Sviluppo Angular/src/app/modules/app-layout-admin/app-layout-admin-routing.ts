import { Routes } from "@angular/router";
import AppLayoutComponent from "../app-layout-admin/components/app-layout-admin/app-layout-admin.component";

export const routes: Routes = [
  {
    path: "",
    component: AppLayoutComponent,
    children: [
      {
        path: "",
        redirectTo: "impianti",
        pathMatch: "full",
      },
      {
        path: "impianti",
        loadChildren: () =>
          import("../impianto/impianto-routing").then((m) => m.routes),
      },
      {
        path: "piste",
        loadChildren: () =>
          import("../piste/piste-routing").then((m) => m.routes),
      },
      {
        path:"profilo",
        loadChildren: () =>
          import("../profilo/profilo-routing").then((m) => m.routes),
      },
      { path: "**", redirectTo: "impianti", pathMatch: "full" },
    ],
  },
];
