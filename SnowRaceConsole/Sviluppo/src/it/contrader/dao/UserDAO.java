package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.User;
import it.contrader.utilities.userSpecs;

/**
 * UserDAO is a Data Access Object (DAO) class providing CRUD operations for the User model.
 * It interacts with the underlying database and encapsulates SQL query creation and execution.
 */
public class UserDAO {

	private final String QUERY_ALL = "SELECT * FROM user";
	private final String QUERY_CREATE = "INSERT INTO user (username, password, usertype) VALUES (?,?,?)";
	private final String QUERY_READ = "SELECT * FROM user WHERE iduser=?";
	private final String QUERY_READ_USERNAME = "SELECT * FROM user WHERE username=?";
	//Homecontroller[REGISTER] insert via username
	private final String QUERY_UPDATE = "UPDATE user SET username=?, password=?, usertype=? WHERE iduser=?";
	private final String QUERY_DELETE = "DELETE FROM user WHERE iduser=?";
	private final String QUERY_DELETE_USERNAME = "DELETE FROM user WHERE username=?";


	/**
	 * Default constructor for UserDAO.
	 */
	public UserDAO() {

	}

	/**
	 * Retrieves all User entries from the database.
	 *
	 * @return a List of User objects representing all users in the database.
	 */
	public List<User> getAll() {
		List<User> usersList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			User user;
			while (resultSet.next()) {
				int id = resultSet.getInt("iduser");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String usertype = resultSet.getString("usertype");
				user = new User(username, password, usertype);
				user.setId(id);
				usersList.add(user);
			}
		} catch (SQLException e) {
			System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
			e.printStackTrace();
			return null;
		}
		return usersList;
	}



	/**
	 * Inserts a new User entry into the database.
	 *
	 * @param userToInsert a User object representing the user to be inserted.
	 * @return a boolean indicating the success or failure of the operation.
	 */
	public boolean insert(User userToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, userToInsert.getUsername());
			preparedStatement.setString(2, userToInsert.getPassword());
			preparedStatement.setString(3, userToInsert.getUsertype());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Reads a User entry from the database using its ID.
	 *
	 * @param userId the ID of the User entry to read.
	 * @return a User object representing the read user or null if not found.
	 */
	public User read(int userId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String username, password, usertype;

			username = resultSet.getString("username");
			password = resultSet.getString("password");
			usertype = resultSet.getString("usertype");
			User user = new User(username, password, usertype);
			user.setId(resultSet.getInt("iduser"));

			return user;
		} catch (SQLException e) {
			System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
			e.printStackTrace();
			return null;
		}
	}

	public User read(String username) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ_USERNAME);
			//if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE"))
				preparedStatement.setString(1, username);
			//else
			//	preparedStatement.setString(1, userSpecs.getInstance().getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String password, usertype;

			password = resultSet.getString("password");
			usertype = resultSet.getString("usertype");
			User user = new User(username, password, usertype);
			user.setId(resultSet.getInt("iduser"));

			return user;
		} catch (SQLException e) {
			System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * Updates a User entry in the database.
	 *
	 * @param userToUpdate a User object containing the updated data.
	 * @return a boolean indicating the success or failure of the operation.
	 */
	public boolean update(User userToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();
		User userRead;
		// Check if id is present
		if (userToUpdate.getId() == 0)
			if (userToUpdate.getUsername()== null || userToUpdate.getUsername().equals("")){
				return false;
			}
			else {
				if (userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE"))
					userRead = read(userToUpdate.getUsername());
				else
					userRead = read(userSpecs.getInstance().getUsername());
			}
		else
			userRead = read(userToUpdate.getId());


		if (!userRead.equals(userToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (userToUpdate.getUsername() == null || userToUpdate.getUsername().equals("")) {
					userToUpdate.setUsername(userRead.getUsername());
				}

				if (userToUpdate.getPassword() == null || userToUpdate.getPassword().equals("")) {
					userToUpdate.setPassword(userRead.getPassword());
				}

				if (userToUpdate.getUsertype() == null || userToUpdate.getUsertype().equals("")) {
					userToUpdate.setUsertype(userRead.getUsertype());
				}

				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, userToUpdate.getUsername());
				preparedStatement.setString(2, userToUpdate.getPassword());
				preparedStatement.setString(3, userToUpdate.getUsertype());
				if (userToUpdate.getId() > 0)
					preparedStatement.setInt(4, userToUpdate.getId());
				else
					preparedStatement.setInt(4, userRead.getId());
				int a = preparedStatement.executeUpdate();
				return a > 0;

			} catch (SQLException e) {
				System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_UPDATE);
				e.printStackTrace();
				return false;
			}
		}

		return false;

	}

	/**
	 * Deletes a User entry from the database using its ID.
	 *
	 * @param id the ID of the User entry to delete.
	 * @return a boolean indicating the success or failure of the operation.
	 */
	public boolean delete(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, id);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
			System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_DELETE);
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public boolean delete(String username) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_USERNAME);
			preparedStatement.setString(1, username);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
			System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_DELETE_USERNAME);
			e.printStackTrace();
			return false;
		}
		return false;
	}
}