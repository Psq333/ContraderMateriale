package it.contrader.dao;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Piste;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PisteDAO {

    private final String QUERY_ALL = "SELECT * FROM piste";
    private final String QUERY_CREATE = "INSERT INTO piste (idimpianto, difficolta, prezzo, prenmax) VALUES (?,?,?,?)";
    private final String QUERY_READ = "SELECT * FROM piste WHERE idpista=?";
    private final String QUERY_UPDATE = "UPDATE piste SET idimpianto=?, difficolta=?, prezzo=?, prenmax=? WHERE idpista=?";
    private final String QUERY_DELETE = "DELETE FROM piste WHERE idpista=?";
    private final String QUERY_ALL_USERNAME = "SELECT * " +
            "FROM piste INNER JOIN impianti " +
            "ON piste.idimpianto = impianti.idimpianti " +
            "WHERE impianti.amministratore=?";

    public PisteDAO(){}

    public List<Piste> getAll(){
        List<Piste> pisteListe = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            Piste piste;
            while (resultSet.next()) {
                int idPista = resultSet.getInt("idpista");
                int idImpianto = resultSet.getInt("idimpianto");
                String difficolta = resultSet.getString("difficolta");
                double prezzo = resultSet.getDouble("prezzo");
                int prenMax = resultSet.getInt("prenmax");
                piste = new Piste(idImpianto, difficolta, prezzo, prenMax);
                piste.setIdPista(idPista);
                pisteListe.add(piste);
            }
        }catch(SQLException e){
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return pisteListe;
    }

    public boolean insert(Piste pistaToInsert) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setInt(1, pistaToInsert.getIdImpianto());
            preparedStatement.setString(2, pistaToInsert.getDifficolta());
            preparedStatement.setDouble(3, pistaToInsert.getPrezzo());
            preparedStatement.setInt(4, pistaToInsert.getPrenMax());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }
    }

    public Piste read(int idPista) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, idPista);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String difficolta;
            int idImpianto,prenMax;
            double prezzo;
            idImpianto = resultSet.getInt("idimpianto");
            difficolta = resultSet.getString("difficolta");
            prezzo = resultSet.getDouble("prezzo");
            prenMax = resultSet.getInt("prenMax");
            Piste piste = new Piste(idImpianto, difficolta, prezzo, prenMax);
            piste.setIdPista(resultSet.getInt("idpista"));
            return piste;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(Piste pistaToUpdate) {     //userToUpdate
        Connection connection = ConnectionSingleton.getInstance();

        // Check if id is present
        if (pistaToUpdate.getIdPista() == 0)
            return false;

        Piste pistaRead = read(pistaToUpdate.getIdPista());
        if (!pistaRead.equals(pistaToUpdate)) {
            try {

                if (pistaToUpdate.getIdImpianto() == 0) {
                    pistaToUpdate.setIdImpianto(pistaRead.getIdImpianto());
                }

                if (pistaToUpdate.getDifficolta() == null || pistaToUpdate.getDifficolta().equals("")) {
                    pistaToUpdate.setDifficolta(pistaRead.getDifficolta());
                }

                if (pistaToUpdate.getPrezzo() == 0) {
                    pistaToUpdate.setPrezzo(pistaRead.getPrezzo());
                }

                if (pistaToUpdate.getPrenMax() == 0) {
                    pistaToUpdate.setPrenMax(pistaRead.getPrenMax());
                }

                // Update the user
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setInt(1, pistaToUpdate.getIdImpianto());
                preparedStatement.setString(2, pistaToUpdate.getDifficolta());
                preparedStatement.setDouble(3, pistaToUpdate.getPrezzo());
                preparedStatement.setInt(4, pistaToUpdate.getPrenMax());
                preparedStatement.setInt(5, pistaToUpdate.getIdPista());
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

    public boolean delete(int idPista) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, idPista);
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

    public List<Piste> getAll(String username){
        List<Piste> pisteListeUsername = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_ALL_USERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            Piste piste;
            while (resultSet.next()) {
                int idPista = resultSet.getInt("idpista");
                int idImpianto = resultSet.getInt("idimpianto");
                String difficolta = resultSet.getString("difficolta");
                double prezzo = resultSet.getDouble("prezzo");
                int prenMax = resultSet.getInt("prenmax");
                piste = new Piste(idImpianto, difficolta, prezzo, prenMax);
                piste.setIdPista(idPista);
                pisteListeUsername.add(piste);
            }
        }catch(SQLException e){
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return pisteListeUsername;
    }

}