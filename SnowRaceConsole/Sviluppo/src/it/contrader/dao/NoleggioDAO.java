package it.contrader.dao;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Noleggi;
import it.contrader.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NoleggioDAO {
    private final String QUERY_ALL = "Select * FROM noleggio";
    private final String QUERY_CREATE = "INSERT INTO noleggio (idnoleggio, data_inizio, data_fine, idattrezzatura, username) VALUES (?,?,?,?,?)";

    private final String QUERY_READ = "SELECT * FROM noleggio WHERE id=?";
    private final String QUERY_UPDATE = "UPDATE noleggio SET username=?, data_inizio=?, data_fine=?, WHERE id=?";
    private final String QUERY_DELETE = "DELETE FROM noleggio WHERE id=?";

    public NoleggioDAO(){

    }
    public List<Noleggi> getAll() {
        List<Noleggi> noleggiList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            Noleggi noleggi;
            while (resultSet.next()) {
                int id = resultSet.getInt("idnoleggio");

                int idattrezzatura = resultSet.getInt("idattrezzatura");

                String username = resultSet.getString("username");

                Date data_inizio = resultSet.getDate("data_inizio");
                Date data_fine = resultSet.getDate("data_fine");

                noleggi = new Noleggi(idattrezzatura, username, data_inizio, data_fine);

                noleggi.setIdNoleggio(id);
                noleggiList.add(noleggi);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return noleggiList;
    }

    public boolean insert(Noleggi noleggiToInsert) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setString(1, noleggiToInsert.getUsername());
            preparedStatement.setDate(2, (java.sql.Date)noleggiToInsert.getDataInizio());
            preparedStatement.setDate(3, (java.sql.Date)noleggiToInsert.getDataFine());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }

    }

    public Noleggi read(int noleggioId) {
        Connection connection = ConnectionSingleton.getInstance();
        try {


            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, noleggioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String username;
            Date data_inizio, data_fine;
            int idAttrezzatura = resultSet.getInt("idAttrezzatura");
            username = resultSet.getString("username");
            data_inizio = resultSet.getDate("data_inizio");
            data_fine = resultSet.getDate("data_fine");
            Noleggi noleggi = new Noleggi(idAttrezzatura, username, data_inizio, data_fine);
            noleggi.setIdNoleggio(resultSet.getInt("id"));

            return noleggi;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }

    }


    public boolean update(Noleggi noleggiToUpdate) {
        Connection connection = ConnectionSingleton.getInstance();

        // Check if id is present
        if (noleggiToUpdate.getIdNoleggio() == 0)
            return false;

        Noleggi noleggiRead = read(noleggiToUpdate.getIdNoleggio());
        if (!noleggiRead.equals(noleggiToUpdate)) {
            try {
                // Fill the userToUpdate object
                if (noleggiToUpdate.getUsername() == null || noleggiToUpdate.getUsername().equals("")) {
                    noleggiToUpdate.setUsername(noleggiRead.getUsername());
                }

                if (noleggiToUpdate.getDataInizio() == null || noleggiToUpdate.getDataInizio().equals("")) {
                    noleggiToUpdate.setDataInizio(noleggiRead.getDataInizio());
                }

                if (noleggiToUpdate.getDataFine() == null || noleggiToUpdate.getDataFine().equals("")) {
                    noleggiToUpdate.setDataFine(noleggiRead.getDataFine());
                }

                // Update the user
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setString(1, noleggiToUpdate.getUsername());
                preparedStatement.setDate(2, (java.sql.Date)noleggiToUpdate.getDataInizio());
                preparedStatement.setDate(3, (java.sql.Date)noleggiToUpdate.getDataFine());
                preparedStatement.setInt(4, noleggiToUpdate.getIdNoleggio());
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
