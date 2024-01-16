package it.contrader.dto;

import it.contrader.model.User.Usertype;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

	private Long id;

	private String username;

	private String password;

	private Usertype usertype;

}
