import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, of } from "rxjs";
import { environment } from "src/environments/environment";
import { LoginService } from "./login.service";

@Injectable({
  providedIn: "root",
})
export class NoleggioService {
  baseUrl = environment.apiUrl;

  constructor(private http: HttpClient, private loginService: LoginService) {}

  resolveListaNoleggi(): Observable<any> | undefined {
    return this.getListaNoleggi(25, 0).pipe(
      catchError((error) => {
        return of("No data");
      })
    );
  }
  resolveListaNoleggiUser(): Observable<any> | undefined {
    let username = this.loginService.getUtenteSessione().username;
    return this.getListaNoleggiUser(25, 0, username).pipe(
      catchError((error) => {
        return of("No data");
      })
    );
  }

  getListaNoleggi(
    pageSize: number,
    pageNumber: number,
    ...args: any
  ): Observable<any> {
    let url = `${this.baseUrl}noleggio/getAllPaginata?pageSize=${pageSize}&pageNumber=${pageNumber}`;
    args.forEach((x: any) => {
      if (x) {
        for (const [key, value] of Object.entries(x)) {
          url = url + `&${key}=${value}`;
        }
      }
    });
    return this.http.get<any>(url);
  }
  getListaNoleggiUser(
    pageSize: number,
    pageNumber: number,
    username : String,
    ...args: any
  ): Observable<any> {
    let url = `${this.baseUrl}noleggio/getAllPaginataUser?pageSize=${pageSize}&pageNumber=${pageNumber}&username=${username}`;
    args.forEach((x: any) => {
      if (x) {
        for (const [key, value] of Object.entries(x)) {
          url = url + `&${key}=${value}`;
        }
      }
    });
    return this.http.get<any>(url);
  }

  getAttrezzature(): Observable<any> {
    let url = `${this.baseUrl}attrezzatura/getall`;
    return this.http.get<any>(url);
  }

  getAttrezzatureUser(username: String): Observable<any> {
    let url = `${this.baseUrl}attrezzature/getalluser?username=${username}`;
    return this.http.get<any>(url);
  
  }

  deleteNoleggio(id: number): Observable<any> {
    console.log("service noleggio" + id);
    let url = `${this.baseUrl}noleggio/delete?id=${id}`;
    console.log(url);
    return this.http.delete(url);
  }

  insertNoleggio(body: any): Observable<any> {
    let a = body.idattrezzatura;
    body.idattrezzatura = null;
    let url = `${this.baseUrl}noleggio/inserta?id=${a}`;
    return this.http.post(url, body);
  }

  modificaNoleggio(payload: any): Observable<any> {
    let a = payload.idattrezzatura;
    payload.idattrezzatura = null;
    let url = `${this.baseUrl}noleggio/updatee?id=${a}`;
    return this.http.put(url, payload);
  }

  readNoleggio(id: number): Observable<any>{
    let url = `${this.baseUrl}noleggio/read?id=${id}`;
    return this.http.get(url);
  }
}


