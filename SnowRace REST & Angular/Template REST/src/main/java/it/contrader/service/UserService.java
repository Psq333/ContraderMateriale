package it.contrader.service;

import it.contrader.exceptions.InvalidCredentialsException;
import org.springframework.stereotype.Service;

import it.contrader.dao.UserRepository;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;

@Service
public class UserService extends AbstractService<User,UserDTO> {

	public UserDTO findByUsernameAndPassword(String username, String password) {
		return converter.toDTO(((UserRepository)repository).findByUsernameAndPassword(username, password)
				.orElseThrow(() -> new InvalidCredentialsException("Credenziali Errate")));
	}

}
