package it.contrader.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {

    private String username;

    private String password;

    //private Set<String> roles;

    private String usertype;

    //private AnagraficaDTO anagrafica;
}
