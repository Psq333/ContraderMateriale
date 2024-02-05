package it.contrader.dto;

import it.contrader.model.Attrezzatura;
import it.contrader.model.Impianto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttrezzaturaDTO {
    private Long idAttrezzatura;
    private String nome;
    private String descrizione;
    private Double prezzo;
    private ImpiantoDTO idImpianto;

}
