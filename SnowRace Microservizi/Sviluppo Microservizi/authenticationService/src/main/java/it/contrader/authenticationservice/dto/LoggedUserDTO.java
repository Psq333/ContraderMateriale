package it.contrader.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoggedUserDTO {

    private Long id;

    private String username;

    private String jwt;

    //private List<String> roles;

    private String usertype;
}
