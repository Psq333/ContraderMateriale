package it.contrader.anagraficaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AnagraficaDTO {

    private Long id;

    private String nome;

    private String cognome;

    private String indirizzo;

    private LocalDate dataNascita;

    private String luogoNascita;

    private UserDTO user;
}
