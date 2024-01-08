package it.contrader.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import it.contrader.enums.Usertype;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.User;

/**
 * @author Vittorio
 * <p>
 * Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class UserDAO implements DAO<User> {

    private final String QUERY_ALL = "SELECT * FROM user";
    private final String QUERY_CREATE = "INSERT INTO user (username, password, usertype) VALUES (?,?,?)";
    private final String QUERY_READ = "SELECT * FROM user WHERE id=?";
    private final String QUERY_UPDATE = "UPDATE user SET username=?, password=?, usertype=? WHERE id=?";
    private final String QUERY_DELETE = "DELETE FROM user WHERE id=?";

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
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Usertype usertype = Usertype.values()[resultSet.getInt("usertype")];
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
            preparedStatement.setInt(3, userToInsert.getUsertype().ordinal());
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
            String username, password;
            Usertype usertype;

            username = resultSet.getString("username");
            password = resultSet.getString("password");
            usertype = Usertype.values()[resultSet.getInt("usertype")];
            User user = new User(username, password, usertype);
            user.setId(resultSet.getInt("id"));

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

        // Check if id is present
        if (userToUpdate.getId() == 0)
            return false;

        User userRead = read(userToUpdate.getId());
        if (!userRead.equals(userToUpdate)) {
            try {
                // Fill the userToUpdate object
                if (userToUpdate.getUsername() == null || userToUpdate.getUsername().equals("")) {
                    userToUpdate.setUsername(userRead.getUsername());
                }

                if (userToUpdate.getPassword() == null || userToUpdate.getPassword().equals("")) {
                    userToUpdate.setPassword(userRead.getPassword());
                }

                if (userToUpdate.getUsertype() == null) {
                    userToUpdate.setUsertype(userRead.getUsertype());
                }

                // Update the user
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setString(1, userToUpdate.getUsername());
                preparedStatement.setString(2, userToUpdate.getPassword());
                preparedStatement.setInt(3, userToUpdate.getUsertype().ordinal());
                preparedStatement.setInt(4, userToUpdate.getId());
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
}
