package it.contrader.dto;

import it.contrader.model.Pista;
import it.contrader.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImpiantoDTO {

    private Long idImpianto;

    private String nome;

    private String descrizione;

    private String luogo;

    private UserDTO amministratore;

    private List<PistaDTO> piste;
}
