package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.UserDTO;
import it.contrader.model.User;

/**
 * The UserConverter class provides utility methods to convert between User and UserDTO objects.
 * This conversion is useful when the application needs to convert domain-specific objects (User)
 * to data transfer objects (UserDTO) and vice versa.
 */
public class UserConverter {

	/**
	 * Converts a User object to a UserDTO object.
	 *
	 * @param user the User object to be converted.
	 * @return a UserDTO object that represents the given User object. Returns null if the input User object is null.
	 */
	public UserDTO toDTO(User user) {
		return user != null ? new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getUsertype()) : null;
	}

	/**
	 * Converts a UserDTO object to a User object.
	 *
	 * @param userDTO the UserDTO object to be converted.
	 * @return a User object that represents the given UserDTO object. Returns null if the input UserDTO object is null.
	 */
	public User toEntity(UserDTO userDTO) {
		return userDTO != null ? new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getUsertype()) : null;
	}

	/**
	 * Converts a list of User objects to a list of UserDTO objects.
	 *
	 * @param userList the list of User objects to be converted.
	 * @return a list of UserDTO objects that represents the given list of User objects. Returns null if the input list is null.
	 */
	public List<UserDTO> toDTOList(List<User> userList) {
		List<UserDTO> userDTOList = new ArrayList<>();
		if(userList != null) {
			for (User user : userList) {
				userDTOList.add(toDTO(user));
			}
			return userDTOList;
		} else return null;
	}
}

