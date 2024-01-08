package it.contrader.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.contrader.enums.Usertype;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.User;

/**
 * 
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class LoginDAO {

	private final String QUERY_LOGIN = "SELECT * FROM user WHERE username = ? AND password = ?";

	
	public User login (String username, String password) {

		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN);
			
			statement.setString(1, username);
			statement.setString(2, password);

			
			ResultSet resultSet;
			resultSet = statement.executeQuery();
			if(resultSet.next()) {
				int id = resultSet.getInt("iduser");
				Usertype usertype = Usertype.values()[resultSet.getInt("usertype")];
				//Usertype usertype = Usertype.valueOf(resultSet.getString("usertype"));


				return new User(id, username, password, usertype);
			}

			return null;
		}
		
		catch (SQLException e) {
			
			return null;
		}
	}
}
