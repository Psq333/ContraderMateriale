package it.contrader.pistaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImpiantoDTO {

    private Long id;

    private String nome;

    private String descrizione;

    private String luogo;

    private Long amministratore;


}
