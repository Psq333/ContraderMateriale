import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, of } from "rxjs";
import { environment } from "src/environments/environment";
import { ImpiantoModelRequest } from "../models/impianto.model";
import { LoginService } from "./login.service";

@Injectable({
  providedIn: "root",
})
export class ImpiantiService {
  baseUrl = environment.apiUrl;
  idamm: string | undefined;

  constructor(private http: HttpClient, private loginService: LoginService) {}

  resolveListaImpianti(): Observable<any> | undefined {
    return this.getListaImpianti(25, 0).pipe(
      catchError((error) => {
        return of("No data");
      })
    );
  }

  resolveListaImpiantiAdmin(): Observable<any> | undefined {
    let idamm = this.loginService.getUtenteSessione().username;
    return this.getListaImpiantiAdmin(25, 0, idamm).pipe(
      catchError((error) => {
        return of("No data");
      })
    );
  }

  getListaImpianti(
    pageSize: number,
    pageNumber: number,
    ...args: any
  ): Observable<any> {
    let url = `${this.baseUrl}impianto/getAllPaginata?pageSize=${pageSize}&pageNumber=${pageNumber}`;
    args.forEach((x: any) => {
      if (x) {
        for (const [key, value] of Object.entries(x)) {
          url = url + `&${key}=${value}`;
        }
      }
    });
    return this.http.get<any>(url);
  }

  getListaImpiantiAdmin(
    pageSize: number,
    pageNumber: number,
    idamm: string | undefined,
    ...args: any
  ): Observable<any> {
    let url = `${this.baseUrl}impianto/getAllPaginataCustom?pageSize=${pageSize}&pageNumber=${pageNumber}&idAmministratore=${idamm}`;
    args.forEach((x: any) => {
      if (x) {
        for (const [key, value] of Object.entries(x)) {
          url = url + `&${key}=${value}`;
        }
      }
    });
    return this.http.get<any>(url);
  }

  deleteImpianto(id: number): Observable<any> {
    let url = `${this.baseUrl}impianto/delete?id=${id}`;
    return this.http.delete(url);
  }

  inserisciImpianto(
    payload: ImpiantoModelRequest
  ): Observable<ImpiantoModelRequest> {
    return this.http.post<ImpiantoModelRequest>(
      this.baseUrl + "impianto/insert",
      payload
    );
  }

  updateImpianto(
    payload: ImpiantoModelRequest
  ): Observable<ImpiantoModelRequest> {
    return this.http.post<ImpiantoModelRequest>(
      this.baseUrl + "impianto/insert",
      payload
    );
  }

  readImpianto(id: number): Observable<any> {
    let url = `${this.baseUrl}impianto/read?id=${id}`;
    return this.http.get(url);
  }
}