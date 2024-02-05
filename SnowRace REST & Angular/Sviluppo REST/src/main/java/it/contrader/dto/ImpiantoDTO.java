package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImpiantoDTO {

    private Long idImpianto;

    @NotEmpty(message = "Il nome impianto non può essere vuoto")
    private String nome;

    @NotEmpty(message = "La descrizione impianto non può essere vuoto")
    private String descrizione;

    @NotEmpty(message = "Il luogo impianto non può essere vuoto")
    private String luogo;

    @NotNull(message = "L'amministratore impianto non può essere vuoto")
    private UserDTO amministratore;

    private List<PistaDTO> piste;
}