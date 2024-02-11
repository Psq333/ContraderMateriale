package it.contrader.dao;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Prenotazioni;
import it.contrader.model.User;
import it.contrader.utilities.userSpecs;
import it.contrader.service.UserService;
import it.contrader.utilities.userSpecs;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class PrenotazioniDAO {
    private final String QUERY_ALL = "SELECT * FROM prenotazioni";
    private final String QUERY_CREATE = "INSERT INTO prenotazioni (idpista, username, data_inizio, data_fine) VALUES (?,?,?,?)";
    private final String QUERY_READ = "SELECT * FROM prenotazioni WHERE idprenotazione=?";
    private final String QUERY_UPDATE = "UPDATE prenotazioni SET idpista=?, username=?, data_inizio=?, data_fine=? WHERE idprenotazione=?";
    private final String QUERY_DELETE = "DELETE FROM prenotazioni WHERE idprenotazione=?";
    private final String QUERY_STORICO = "SELECT * FROM prenotazioni WHERE username=?";

    private final String QUERY_DATE_SEARCH = "SELECT * FROM prenotazioni WHERE data_inizio>? AND username=?";
    private final String QUERY_PISTE_SEARCH = "SELECT * FROM prenotazioni WHERE idpiste=? AND username=?";
    public PrenotazioniDAO() {

    }

    public List<Prenotazioni> getAll() {
        List<Prenotazioni> prenotazioniList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            Prenotazioni prenotazioni;
            while (resultSet.next()) {
                int id = resultSet.getInt("idprenotazione");
                int idpista = resultSet.getInt("idpista");
                String username = resultSet.getString("username");
                Date data_inizio = resultSet.getDate("data_inizio");
                Date data_fine = resultSet.getDate("data_fine");
                prenotazioni = new Prenotazioni(idpista, username, data_inizio, data_fine);
                prenotazioni.setIdprenotazione(id);
                prenotazioniList.add(prenotazioni);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return prenotazioniList;
    }

    public List<Prenotazioni> search(Map<String, String> quest){
        List<Prenotazioni> search = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();

        try {
            PreparedStatement preparedStatement;
            if (quest.get("key").equals("data_inizio")) {
                preparedStatement = connection.prepareStatement(QUERY_DATE_SEARCH);
                //ResultSet resultSet = preparedStatement.executeQuery();
            } else {
                preparedStatement = connection.prepareStatement(QUERY_PISTE_SEARCH);
            }
            //if (quest.get("key").equals("idimpianto"))
            preparedStatement.setString(1, quest.get("what"));
            preparedStatement.setString(2, userSpecs.getInstance().getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            Prenotazioni prenotazioni;
            while (resultSet.next()) {
                int id = resultSet.getInt("idprenotazione");
                int idpista = resultSet.getInt("idpista");
                String username = resultSet.getString("username");
                Date data_inizio = resultSet.getDate("data_inizio");
                Date data_fine = resultSet.getDate("data_fine");
                prenotazioni = new Prenotazioni(idpista,username, data_inizio, data_fine);
                prenotazioni.setIdprenotazione(id);
                search.add(prenotazioni);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return search;
    }

    public boolean insert(Prenotazioni prenotazioniToInsert) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setInt(1, prenotazioniToInsert.getIdpista());
            preparedStatement.setString(2, prenotazioniToInsert.getUsername());
            preparedStatement.setDate(3, prenotazioniToInsert.getDataIn());
            preparedStatement.setDate(4, prenotazioniToInsert.getDataFin());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }

    }

    public Prenotazioni read(int prenotazioneId) {
        Connection connection = ConnectionSingleton.getInstance();
        try {


            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, prenotazioneId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idpista;
            String username;
            Date data_inizio, data_fine;


            idpista = resultSet.getInt("idpista");
            username = resultSet.getString("username");
            data_inizio = resultSet.getDate("data_inizio");
            data_fine = resultSet.getDate("data_inizio");
            Prenotazioni prenotazioni = new Prenotazioni(idpista, username, data_inizio, data_fine);
            prenotazioni.setIdprenotazione(resultSet.getInt("idprenotazione"));

            return prenotazioni;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }

    }

    public boolean update(Prenotazioni prenotazioniToUpdate) {
        Connection connection = ConnectionSingleton.getInstance();

        // Check if id is present
        if (prenotazioniToUpdate.getIdprenotazione() == 0)
            return false;

        Prenotazioni prenotazioniRead = read(prenotazioniToUpdate.getIdprenotazione());
        if (!prenotazioniRead.equals(prenotazioniToUpdate)) {
            try {
                // Fill the prenotazioniToUpdate object
                if (prenotazioniToUpdate.getUsername() == null || prenotazioniToUpdate.getUsername().isEmpty()) {
                    prenotazioniToUpdate.setUsername(prenotazioniRead.getUsername());
                }

                if (prenotazioniToUpdate.getIdpista() == 0) {
                    prenotazioniToUpdate.setIdpista(prenotazioniRead.getIdpista());
                }

                if (prenotazioniToUpdate.getDataIn() == null) {
                    prenotazioniToUpdate.setDataIn(prenotazioniRead.getDataIn());
                }
                if (prenotazioniToUpdate.getDataFin() == null) {
                    prenotazioniToUpdate.setDataFin(prenotazioniRead.getDataFin());
                }
                // Update prenotazione
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setInt(1, prenotazioniToUpdate.getIdpista());
                preparedStatement.setString(2, prenotazioniToUpdate.getUsername());
                preparedStatement.setDate(3, (java.sql.Date) prenotazioniToUpdate.getDataIn());
                preparedStatement.setDate(4, (java.sql.Date) prenotazioniToUpdate.getDataFin());
                preparedStatement.setInt(5, prenotazioniToUpdate.getIdprenotazione());
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

    public List<Prenotazioni> getStorico(String usernameDaCercare) {
        List<Prenotazioni> prenotazioniList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_STORICO);
            preparedStatement.setString(1,usernameDaCercare);
            ResultSet resultSet = preparedStatement.executeQuery();
            Prenotazioni prenotazioni;
            while (resultSet.next()) {
                int id = resultSet.getInt("idprenotazione");
                int idpista = resultSet.getInt("idpista");
                String username = resultSet.getString("username");
                Date data_inizio = resultSet.getDate("data_inizio");
                Date data_fine = resultSet.getDate("data_fine");
                prenotazioni = new Prenotazioni(idpista, username, data_inizio, data_fine);
                prenotazioni.setIdprenotazione(id);
                prenotazioniList.add(prenotazioni);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_STORICO);
            e.printStackTrace();
            return null;
        }
        return prenotazioniList;
    }


}
