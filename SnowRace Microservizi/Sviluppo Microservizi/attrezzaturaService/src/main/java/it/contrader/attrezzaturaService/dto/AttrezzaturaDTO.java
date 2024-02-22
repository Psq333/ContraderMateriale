package it.contrader.attrezzaturaService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AttrezzaturaDTO {

    private Long id;

    private String nome;

    private String descrizione;

    private Double prezzo;

    private ImpiantoDTO idImpianto;
}
