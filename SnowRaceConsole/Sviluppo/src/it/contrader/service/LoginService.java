package it.contrader.service;

import it.contrader.converter.UserConverter;
import it.contrader.dao.LoginDAO;
import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
import it.contrader.utilities.userSpecs;

/**
 * Service class responsible for user login operations.
 * Uses LoginDAO for database operations and UserConverter for conversion between DTO and Entity objects.
 */
public class LoginService {

	private final LoginDAO loginDAO;
	private final UserConverter userConverter;
	private UserService userService;

	/**
	 * Constructor to initialize the specific DAO and Converter.
	 */
	public LoginService() {
		this.loginDAO = new LoginDAO();
		this.userConverter = new UserConverter();
		this.userService = new UserService();
	}

	/**
	 * Performs the login operation for a user.
	 * Retrieves the user from the database based on provided username and password,
	 * converts the retrieved User entity to a DTO and returns it.
	 *
	 * @param loginDTO LoginDTO object containing the user's username and password.
	 * @return UserDTO object representing the logged in user.
	 */
	public UserDTO login (LoginDTO loginDTO) {
		UserDTO userDTO = userConverter.toDTO(this.loginDAO.login(loginDTO.getUsername(), loginDTO.getPassword()));
		if (userDTO!=null){
			userSpecs.getInstance().setUsername(userDTO.getUsername());
			userSpecs.getInstance().setUsertype(userDTO.getUsertype());
		}
		return userDTO;
	}
}

