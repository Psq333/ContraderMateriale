import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, of } from "rxjs";
import { environment } from "src/environments/environment";
import { PrenotazioneModelRequest } from "../models/prenotazione.model";

@Injectable({
  providedIn: "root",
})
export class PrenotazioniService {
  baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  resolveListaPrenotazioni(): Observable<any> | undefined {
    return this.getListaPrenotazioni(25, 0).pipe(
      catchError((error) => {
        return of("No data");
      })
    );
  }

  getListaPrenotazioni(
    pageSize: number,
    pageNumber: number,
    ...args: any
  ): Observable<any> {
    let url = `${this.baseUrl}prenotazione/getAllPaginata?pageSize=${pageSize}&pageNumber=${pageNumber}`;
    args.forEach((x: any) => {
      if (x) {
        for (const [key, value] of Object.entries(x)) {
          url = url + `&${key}=${value}`;
        }
      }
    });
    return this.http.get<any>(url);
  }

  deletePrenotazione(id: number): Observable<any> {
    let url = `${this.baseUrl}prenotazione/delete?id=${id}`;
    return this.http.delete(url);
  }

  inserisciPrenotazione(
    payload: PrenotazioneModelRequest
  ): Observable<PrenotazioneModelRequest> {
    return this.http.post<PrenotazioneModelRequest>(
      this.baseUrl + "prenotazione/insert",
      payload
    );
  }

  updatePrenotazione(
    payload: PrenotazioneModelRequest
  ): Observable<PrenotazioneModelRequest> {
    return this.http.post<PrenotazioneModelRequest>(
      this.baseUrl + "prenotazione/insert",
      payload
    );
  }

  readPrenotazione(id: number): Observable<any> {
    let url = `${this.baseUrl}prenotazione/read?id=${id}`;
    return this.http.get(url);
  }

  filteredDates(
    pageSize: number,
    pageNumber: number,
    data: Date
  ): Observable<any> {
    let url = `${this.baseUrl}prenotazione/findByDataInizioIsGreaterThanEqual?pageSize=${pageSize}&pageNumber=${pageNumber}&data=${data}`;
    return this.http.get<any>(url);
  }

  filteredPiste(
    pageSize: number,
    pageNumber: number,
    idPista: bigint
  ): Observable<any> {
    let url = `${this.baseUrl}prenotazione/findByPistaStorico?pageSize=${pageSize}&pageNumber=${pageNumber}&idPista=${idPista}`;
    return this.http.get<any>(url);
  }
}
