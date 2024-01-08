package it.contrader.service;

import java.util.List;


import it.contrader.converter.UserConverter;
import it.contrader.dao.UserDAO;
import it.contrader.dto.UserDTO;

/**
 * Service class for User operations.
 * Uses a UserDAO for database operations
 * and a UserConverter for conversion between DTO and Entity objects.
 */
public class UserService {

	private final UserDAO userDAO;
	private final UserConverter userConverter;

	/**
	 * Constructor to initialize the specific DAO and Converter.
	 */
	public UserService() {
		this.userDAO = new UserDAO();
		this.userConverter = new UserConverter();
	}

	/**
	 * Retrieves all users from the database, converts them to DTOs and returns.
	 *
	 * @return List of UserDTO objects.
	 */
	public List<UserDTO> getAll() {
		return userConverter.toDTOList(userDAO.getAll());
	}

	/**
	 * Retrieves a user from the database by its ID, converts it to DTO and returns.
	 *
	 * @param id User ID.
	 * @return UserDTO object.
	 */
	public UserDTO read(int id) {
		return userConverter.toDTO(userDAO.read(id));
	}

	/**
	 * Inserts a new user into the database.
	 * Converts the input DTO to an entity before passing to DAO.
	 *
	 * @param dto UserDTO object to insert.
	 * @return boolean representing the operation result.
	 */
	public boolean insert(UserDTO dto) {
		return userDAO.insert(userConverter.toEntity(dto));
	}

	/**
	 * Updates a user in the database.
	 * Converts the input DTO to an entity before passing to DAO.
	 *
	 * @param dto UserDTO object to update.
	 * @return boolean representing the operation result.
	 */
	public boolean update(UserDTO dto) {
		return userDAO.update(userConverter.toEntity(dto));
	}

	/**
	 * Deletes a user from the database by its ID.
	 *
	 * @param id User ID.
	 * @return boolean representing the operation result.
	 */
	public boolean delete(int id) {
		return userDAO.delete(id);
	}

}

