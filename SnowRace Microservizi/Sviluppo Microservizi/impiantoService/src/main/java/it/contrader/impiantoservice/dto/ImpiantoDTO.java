package it.contrader.impiantoservice.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ImpiantoDTO {

    private Long id;

    @NotBlank(message = "Il nome non può essere vuota")
    private String nome;

    @NotBlank(message = "La descrizione non può essere vuota")
    private String descrizione;

    @NotBlank(message = "Il luogo non può essere vuota")
    private String luogo;

    @NotBlank(message = "L'ID dell'admin non può essere vuota")
    private Long amministratore;


}
