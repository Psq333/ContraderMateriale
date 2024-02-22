import { HttpClient, HttpHandler } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, of } from "rxjs";
import { environment } from "src/environments/environment";
import { LoginService } from "./login.service";

@Injectable({ providedIn: "root" })
export class PistaService {
  baseUrl = environment.apiUrl;
  amm: string | undefined;
  constructor(private http: HttpClient, private loginService: LoginService) {}

  resolveListaPiste(): Observable<any> | undefined {
    return this.getListaPiste(25, 0).pipe(
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

  getListaPiste(
    pageSize: number,
    pageNumber: number,
    ...args: any
  ): Observable<any> {
    let url = `${this.baseUrl}pista/getAllPaginata?pageSize=${pageSize}&pageNumber=${pageNumber}`;
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
    let url = `${this.baseUrl}pista/getallimpiantiadmin?pageSize=${pageSize}&pageNumber=${pageNumber}&amministratore=${amm}`;
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

  getListaPisteImpianto(
    pageSize: number,
    pageNumber: number,
    ...args: any
  ): Observable<any> {
    let url = `${this.baseUrl}pista/getAllPisteImpianto?pageSize=${pageSize}&pageNumber=${pageNumber}`;
    // Si esegue un forEach degli argomenti ricevuti e ottenendo la chiave valore dell'argomento ricevuto viene inserito all'interno dell'url
    args.forEach((x: any) => {
      if (x) {
        for (const [key, value] of Object.entries(x)) {
          url = url + `&${key}=${value}`;
        }
        console.log(url);
      }
    });
    return this.http.get<any>(url);
  }

  getImpiantiAdmin(username: String): Observable<any> {
    let url = `${this.baseUrl}pista/getallimpiantiadminlist?username=${username}`;
    return this.http.get<any>(url);
  }
  getImpianti(): Observable<any> {
    let url = `${this.baseUrl}impianto/listaimpianti`;
    return this.http.get<any>(url);
  }

  deletePista(id: number): Observable<any> {
    let url = `${this.baseUrl}pista/delete?id=${id}`;
    return this.http.delete<any>(url);
  }

  insertPista(body: any): Observable<any> {
    let url = `${this.baseUrl}pista/insert`;
    return this.http.post(url, body);
  }

  getTuttePiste(): Observable<any> {
    let url = `${this.baseUrl}pista/getall`;
    return this.http.get<any>(url);
  }

  modificaPista(payload: any): Observable<any> {
    let url = `${this.baseUrl}pista/update`;
    return this.http.put(url, payload);
  }

  readPista(id: number): Observable<any> {
    let url = `${this.baseUrl}pista/read?id=${id}`;
    return this.http.get(url);
  }
}
