export interface NoleggioModelRequest {
  idnoleggio: number;
  username: string;
  idattrezzatura: bigint;
  data_inizio: Date;
  data_fine: Date;
}
