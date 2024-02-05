import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class AnagraficaService {
  baseUrl= environment.apiUrl;
  constructor(private http: HttpClient) {}

  resolveListaAnagrafica(): Observable<any> | undefined {
    return this.getListaAnagrafica(25, 0).pipe(
      catchError((error) => {
        return of('No data');
      })
    );
  }

  getListaAnagrafica(
    pageSize: number,
    pageNumber: number,
    ...args: any
  ): Observable<any> {
    let url = `${this.baseUrl}anagrafica/getAllPaginata?pageSize=${pageSize}&pageNumber=${pageNumber}`;
    args.forEach((x: any) => {
      if (x) {
        for (const [key, value] of Object.entries(x)) {
          url = url + `&${key}=${value}`;
        }
      }
    });
    return this.http.get<any>(url);
  }

  deleteAnagrafica(id: number): Observable<any>{
    let url = `${this.baseUrl}anagrafica/delete?id=${id}`;
    return this.http.delete<any>(url);
  }

  insertAnagrafica(body: any): Observable<any> {
    let userId = body.user;
    body.user = null;
    let url = `${this.baseUrl}anagrafica/inserta?id=${userId}`;
    return this.http.post(url, body);
  }

  getUserWoAnag(): Observable<any>{
    let url = `${this.baseUrl}anagrafica/getUserWoAnag`;
    return this.http.get<any>(url);
  }

  modificaAnagrafica(payload: any): Observable<any> {
    let url = `${this.baseUrl}anagrafica/update`;
    return this.http.put(url, payload);
  }

  readAnagrafica(id: number): Observable<any>{
    let url = `${this.baseUrl}anagrafica/read?id=${id}`;
    return this.http.get(url);
  }

  findByUserId(userId: number): Observable<any>{
    let url = `${this.baseUrl}anagrafica/findByUserId?id=${userId}`;
    return this.http.get(url);
  }
}