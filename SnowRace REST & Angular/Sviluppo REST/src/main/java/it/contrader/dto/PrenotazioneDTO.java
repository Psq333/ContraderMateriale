package it.contrader.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrenotazioneDTO {
    private Long idPrenotazione;

    private UserDTO user;

    private Date dataInizio;

    private Date dataFine;

    private PistaDTO pista;

}
