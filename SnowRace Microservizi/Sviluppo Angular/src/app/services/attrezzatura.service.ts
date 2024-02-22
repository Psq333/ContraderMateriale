import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, of } from "rxjs";
import { environment } from "src/environments/environment";
import { LoginService } from "./login.service";

@Injectable({ providedIn: "root" })
export class AttrezzaturaService {
  baseUrl = environment.apiUrl;
  constructor(private http: HttpClient, private loginService: LoginService) {}

  resolveListaAttrezzatura(): Observable<any> | undefined {
    return this.getListaAttrezzatura(25, 0).pipe(
      catchError((error) => {
        return of("No data");
      })
    );
  }
  resolveListaPisteAdmin(): Observable<any> | undefined {
    let amm = this.loginService.getUtenteSessione().username;
    return this.getListaPisteAdmin(25, 0, amm).pipe(
      catchError((error) => {
        return of("No data");
      })
    );
  }
  getListaAttrezzatura(
    pageSize: number,
    pageNumber: number,
    ...args: any
  ): Observable<any> {
    let url = `${this.baseUrl}attrezzatura/getAllPaginata?pageSize=${pageSize}&pageNumber=${pageNumber}`;
    // Si esegue un forEach degli argomenti ricevuti e ottenendo la chiave valore dell'argomento ricevuto viene inserito all'interno dell'url
    args.forEach((x: any) => {
      if (x) {
        for (const [key, value] of Object.entries(x)) {
          url = url + `&${key}=${value}`;
        }
      }
    });
    return this.http.get<any>(url);
  }
  getListaPisteAdmin(
    pageSize: number,
    pageNumber: number,
    amm: string | undefined,
    ...args: any
  ): Observable<any> {
    let url = `${this.baseUrl}pista/getAllPaginataAdmin?pageSize=${pageSize}&pageNumber=${pageNumber}&amministratore=${amm}`;
    // Si esegue un forEach degli argomenti ricevuti e ottenendo la chiave valore dell'argomento ricevuto viene inserito all'interno dell'url
    args.forEach((x: any) => {
      if (x) {
        for (const [key, value] of Object.entries(x)) {
          url = url + `&${key}=${value}`;
        }
      }
    });
    return this.http.get<any>(url);
  }
  getImpianti(): Observable<any> {
    let url = `${this.baseUrl}impianto/listaimpianti`;
    return this.http.get<any>(url);
  }
  insertAttrezzatura(body: any): Observable<any> {
    let url = `${this.baseUrl}attrezzatura/insert`;
    return this.http.post(url, body);
  }
  deleteAttrezzatura(idAttrezzatura: number): Observable<any> {
    let url = `${this.baseUrl}attrezzatura/delete?id=${idAttrezzatura}`;
    return this.http.delete<any>(url);
  }
  updateAttrezzatura(payload: any): Observable<any> {
    let url = `${this.baseUrl}attrezzatura/update`;
    return this.http.put(url, payload);
  }
  readAttrezzatura(id: number): Observable<any> {
    console.log("********", id);
    let url = `${this.baseUrl}attrezzatura/read?id=${id}`;
    return this.http.get(url);
  }
  getAttrezzatura(): Observable<any>{
    let url = `${this.baseUrl}attrezzatura/getall`;
    return this.http.get<any> (url);
  }
}
