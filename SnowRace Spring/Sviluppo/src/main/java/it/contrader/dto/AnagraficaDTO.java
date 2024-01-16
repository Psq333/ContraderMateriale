package it.contrader.dto;

import it.contrader.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnagraficaDTO{
    private Long id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private Date dataNascita;
    private String luogoNascita;
    private UserDTO user;
}