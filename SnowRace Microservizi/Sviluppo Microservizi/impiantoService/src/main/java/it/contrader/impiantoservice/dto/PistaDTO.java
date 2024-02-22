package it.contrader.impiantoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PistaDTO {

    private Long id;

    private String nome;

    private String difficolta;

    private int prenotazioniMax;

    private int prezzo;

    private Long impianto;

}
