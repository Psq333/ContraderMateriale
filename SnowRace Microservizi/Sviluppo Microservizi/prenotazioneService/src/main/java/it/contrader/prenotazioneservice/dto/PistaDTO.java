package it.contrader.prenotazioneservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PistaDTO {

    private long id;

    private String nome;

    private String difficolta;

    private int prenotazioniMax;

    private int prezzo;

    private Long impianto;

}
