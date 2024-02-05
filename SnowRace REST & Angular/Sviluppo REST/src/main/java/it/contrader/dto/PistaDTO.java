package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PistaDTO {

    private Long idPista;
    private String nome;
    private String difficolta;
    private double prezzo;
    private int prenotazioniMax;
    private Long impianto;
}