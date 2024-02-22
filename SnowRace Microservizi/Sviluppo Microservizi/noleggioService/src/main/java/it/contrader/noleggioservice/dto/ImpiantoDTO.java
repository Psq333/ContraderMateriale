package it.contrader.noleggioservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
