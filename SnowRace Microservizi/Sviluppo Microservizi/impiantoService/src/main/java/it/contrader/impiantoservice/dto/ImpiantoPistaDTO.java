package it.contrader.impiantoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImpiantoPistaDTO {

    private Long id;

    private String nome;

    private String descrizione;

    private String luogo;

    private List<PistaDTO> piste;
}
