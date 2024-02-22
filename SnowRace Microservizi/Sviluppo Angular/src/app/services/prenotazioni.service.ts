import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, of } from "rxjs";
import { environment } from "src/environments/environment";
import {
  Prenotazione,
  PrenotazioneModelRequest,
} from "../models/prenotazione.model";

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

  /* getListaPrenotazioni(
    pageSize: number,
    pageNumber: number,
    ...args: any
  ): Observable<any> {
    let url = `${this.baseUrl}pren/getAllPaginata?pageSize=${pageSize}&pageNumber=${pageNumber}`;
    args.forEach((x: any) => {
      if (x) {
        for (const [key, value] of Object.entries(x)) {
          url = url + `&${key}=${value}`;
        }
      }
    });
    return this.http.get<any>(url);
  }
  */

  getListaPrenotazioni(pageSize: number, pageNumber: number): Observable<any> {
    let url = `${this.baseUrl}pren/findAllPageable?pageSize=${pageSize}&pageNumber=${pageNumber}`;
    return this.http.get<any>(url);
  }

  deletePrenotazione(id: number): Observable<any> {
    let url = `${this.baseUrl}pren/deletePrenotazione?id=${id}`;
    return this.http.delete(url);
  }

  inserisciPrenotazione(
    payload: PrenotazioneModelRequest
  ): Observable<PrenotazioneModelRequest> {
    return this.http.post<PrenotazioneModelRequest>(
      this.baseUrl + "pren/insertPrenotazione",
      payload
    );
  }

  updatePrenotazione(payload: PrenotazioneModelRequest): Observable<any> {
    return this.http.put(this.baseUrl + "pren/updatePrenotazione", payload);
  }

  readPrenotazione(id: number): Observable<any> {
    let url = `${this.baseUrl}pren/readPrenotazione?id=${id}`;
    return this.http.get(url);
  }

  filteredDates(
    pageSize: number,
    pageNumber: number,
    data: Date,
    idUser: number
  ): Observable<any> {
    let url = `${this.baseUrl}pren/findAllStoricoD?pageSize=${pageSize}&pageNumber=${pageNumber}&idUser=${idUser}&dataInizio=${data}`;
    return this.http.get<any>(url);
  }

  filteredPiste(
    pageSize: number,
    pageNumber: number,
    idPista: bigint,
    idUser: number
  ): Observable<any> {
    let url = `${this.baseUrl}pren/findAllStoricoP?pageSize=${pageSize}&pageNumber=${pageNumber}&idUser=${idUser}&idPista=${idPista}`;
    return this.http.get<any>(url);
  }
}
