export interface PrenotazioneModelRequest {
  idPrenotazione: number;
  user: string;
    dataInizio: string;
    dataFine: string;
    amministratore: string, //da modificare
  }

 /*  export interface PrenotazioneModelRequest {
    id: number;

    dataInizio: Date;

    dataFine: Date;

   idPista: any;

    idUser: any;
  } */

  export interface Prenotazione{
    
    id: number;

    dataInizio: Date;

    dataFine: Date;

   idPista: any;

    idUser: any;
  }