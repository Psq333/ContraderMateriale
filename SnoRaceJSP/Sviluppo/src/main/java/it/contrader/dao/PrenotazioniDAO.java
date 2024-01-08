package it.contrader.dao;

import it.contrader.enums.Usertype;

import it.contrader.model.Impianto;
import it.contrader.model.Prenotazioni;
import it.contrader.model.User;
import it.contrader.utils.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioniDAO implements DAO <Prenotazioni> {
    private final String QUERY_ALL = "SELECT * FROM prenotazioni INNER JOIN piste ON prenotazioni.idpista=piste.idpista";
    private final String QUERY_CREATE = "INSERT INTO prenotazioni (idpista, username, data_inizio, data_fine) VALUES (?,?,?,?)";
    private final String QUERY_READ = "SELECT * FROM prenotazioni INNER JOIN piste ON prenotazioni.idpista=piste.idpista WHERE idprenotazione=?";
    private final String QUERY_UPDATE = "UPDATE prenotazioni SET idpista=?, username=?, data_inizio=?, data_fine=? WHERE idprenotazione=?";
    private final String QUERY_DELETE = "DELETE FROM prenotazioni WHERE idprenotazione=?";
    private final String QUERY_STORICO = "SELECT * FROM prenotazioni WHERE username=?";
    private final String QUERY_SEARCH_PISTE = "SELECT * FROM prenotazioni WHERE idpista=? AND username=?";
    private final String QUERY_SEARCH_DATA = "SELECT * FROM prenotazioni WHERE data_inizio>=? AND username=?";

    private final String QUERY_PRENOTAZIONIUSER = "SELECT * FROM prenotazioni WHERE username=?";


    public PrenotazioniDAO() {

    }

    /**
     * Recupera le voci di Prenotazioni dal database.
     *
     * @return una List di oggetti di tipo Prenotazioni che rappresentano tutte le prenotazioni nel database.
     */
    public List<Prenotazioni> getAll() {
        List<Prenotazioni> prenotazioniList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            Prenotazioni prenotazione;
            while (resultSet.next()) {
                int idprenotazione = resultSet.getInt("idprenotazione");
                int idpista = resultSet.getInt("idpista");
                String username = resultSet.getString("username");
                String data_inizio = resultSet.getString("data_inizio");
                String data_fine = resultSet.getString("data_fine");
                prenotazione = new Prenotazioni(idpista, username, data_inizio,data_fine);
                prenotazione.setId_prenotazione(idprenotazione);
                prenotazioniList.add(prenotazione);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return prenotazioniList;
    }
    /**
     * Inserisce una nuova voce Prenotazioni nel database.
     *
     * @param prenotazioniToInsert un oggetto di tipo Prenotazioni che rappresenta una prenotazione da inserire.
     * @return un boolean che indica se l'operazione è andata a buon fine o meno
     */
    public boolean insert(Prenotazioni prenotazioniToInsert) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setInt(1, prenotazioniToInsert.getId_pista());
            preparedStatement.setString(2, prenotazioniToInsert.getUsername());
            preparedStatement.setString(3, prenotazioniToInsert.getData_inizio());
            preparedStatement.setString(4, prenotazioniToInsert.getData_fine());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }

    }

    /**
     * legge una voce di tipo Prenotazioni dal database usando il suo ID.
     *
     * @param prenotazioneId l'id della voce Prenotazioni da leggere
     * @return un oggetto di tipo Prenotazioni che rapprensenta la prenotazione letta o null se non la trova.
     */
    public Prenotazioni read(int prenotazioneId) {
        Connection connection = ConnectionSingleton.getInstance();
        try {


            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, prenotazioneId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id_pista, id_prenotazione;
            String username, data_inizio, data_fine;
            id_pista = resultSet.getInt("idpista");
            username = resultSet.getString("username");
            data_inizio = resultSet.getString("data_inizio");
            data_fine = resultSet.getString("data_fine");
            id_prenotazione = resultSet.getInt("idprenotazione");

            return new Prenotazioni(id_prenotazione, username, id_pista, data_inizio, data_fine);
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }

    }

    public boolean update(Prenotazioni prenotazioniToUpdate) {
        Connection connection = ConnectionSingleton.getInstance();

        // Check if id is present
        if (prenotazioniToUpdate.getId_prenotazione() == 0)
            return false;

        Prenotazioni prenotazioniRead = read(prenotazioniToUpdate.getId_prenotazione());
        if (!prenotazioniRead.equals(prenotazioniToUpdate)) {
            try {
                // Fill the prenotazioniToUpdate object
                if (prenotazioniToUpdate.getUsername() == null || prenotazioniToUpdate.getUsername().isEmpty()) {
                    prenotazioniToUpdate.setUsername(prenotazioniRead.getUsername());
                }

                if (prenotazioniToUpdate.getId_pista() == 0) {
                    prenotazioniToUpdate.setId_pista(prenotazioniRead.getId_pista());
                }

                if (prenotazioniToUpdate.getData_inizio() == null) {
                    prenotazioniToUpdate.setData_inizio(prenotazioniRead.getData_inizio());
                }
                if (prenotazioniToUpdate.getData_inizio() == null) {
                    prenotazioniToUpdate.setData_fine(prenotazioniRead.getData_fine());
                }
                // Update prenotazione
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setInt(1, prenotazioniToUpdate.getId_pista());
                preparedStatement.setString(2, prenotazioniToUpdate.getUsername());
                preparedStatement.setString(3, prenotazioniToUpdate.getData_inizio());
                preparedStatement.setString(4, prenotazioniToUpdate.getData_fine());
                preparedStatement.setInt(5, prenotazioniToUpdate.getId_prenotazione());
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

    public boolean delete(int prenotazioneId) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, prenotazioneId);
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
                String data_inizio = resultSet.getString("data_inizio");
                String data_fine = resultSet.getString("data_fine");
                prenotazioni = new Prenotazioni(idpista, username, data_inizio, data_fine);
                prenotazioni.setId_prenotazione(id);
                prenotazioniList.add(prenotazioni);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_STORICO);
            e.printStackTrace();
            return null;
        }
        return prenotazioniList;
    }


    public List<Prenotazioni> search(Date which, String who){
        List<Prenotazioni> searchRes = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_DATA);
            preparedStatement.setDate(1, which);
            preparedStatement.setString(2, who);
            ResultSet resultSet = preparedStatement.executeQuery();
            Prenotazioni prenotazioni;
            while (resultSet.next()) {
                int id = resultSet.getInt("idprenotazione");
                int idpista = resultSet.getInt("idpista");
                String username = resultSet.getString("username");
                String data_inizio = resultSet.getString("data_inizio");
                String data_fine = resultSet.getString("data_fine");
                prenotazioni = new Prenotazioni(idpista,username, data_inizio, data_fine);
                prenotazioni.setId_prenotazione(id);
                searchRes.add(prenotazioni);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_SEARCH_DATA);
            e.printStackTrace();
            return null;
        }
        return searchRes;
    }

    public List<Prenotazioni> search(String which, String who){
        List<Prenotazioni> searchRes = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SEARCH_PISTE);
            preparedStatement.setInt(1, Integer.parseInt(which));
            preparedStatement.setString(2, who);
            ResultSet resultSet = preparedStatement.executeQuery();
            Prenotazioni prenotazioni;
            while (resultSet.next()) {
                int id = resultSet.getInt("idprenotazione");
                int idpista = resultSet.getInt("idpista");
                String username = resultSet.getString("username");
                String data_inizio = resultSet.getString("data_inizio");
                String data_fine = resultSet.getString("data_fine");
                prenotazioni = new Prenotazioni(idpista,username, data_inizio, data_fine);
                prenotazioni.setId_prenotazione(id);
                searchRes.add(prenotazioni);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_SEARCH_PISTE);
            e.printStackTrace();
            return null;
        }
        return searchRes;
    }

    public List<Prenotazioni> getPrenotazioniUser(String username){
        List<Prenotazioni> prenotazioniList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_PRENOTAZIONIUSER);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            Prenotazioni prenotazioni;
            while(resultSet.next()){
                int id = resultSet.getInt("idprenotazione");
                int idpista = resultSet.getInt("idpista");
                //String username = resultSet.getString("username"); //ottenuto come paramentro
                String data_inizio = resultSet.getString("data_inizio");
                String data_fine = resultSet.getString("data_fine");
                prenotazioni = new Prenotazioni(idpista, username, data_inizio, data_fine);
                prenotazioni.setId_prenotazione(id);
                prenotazioniList.add(prenotazioni);
            }
        } catch (SQLException e) {
            System.out.println("Errore  in ImpiantiDAO getAll()");
            e.printStackTrace();
            return null;
        }
        return prenotazioniList;
    }

}
