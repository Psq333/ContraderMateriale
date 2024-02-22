package it.contrader.prenotazioneservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrenotazioneDTO {

    private Long id;

    private Date dataInizio;

    private Date dataFine;

    private PistaDTO idPista;

    private UserDTO idUser;
}
