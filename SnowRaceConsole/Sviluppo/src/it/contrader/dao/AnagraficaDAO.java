package it.contrader.dao;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Anagrafica;
import it.contrader.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnagraficaDAO {

    private final String QUERY_ALL = "SELECT * FROM anagrafica";

    private final String QUERY_CREATE = "INSERT INTO anagrafica (nome, cognome, indirizzo, luogo_nascita, data_nascita, username) VALUES (?,?,?,?,?,?)";

    private final String QUERY_READ = "SELECT * FROM anagrafica WHERE username=?";
    private final String QUERY_READ_ID = "SELECT * FROM anagrafica WHERE id=?";

    private final String QUERY_UPDATE = "UPDATE anagrafica SET nome=?, cognome=?, indirizzo=?, luogo_nascita=?, data_nascita=? WHERE username=?";

    private final String QUERY_DELETE = "DELETE FROM anagrafica WHERE username=?";

    public AnagraficaDAO() {
    }

    public List<Anagrafica> getAll() {
        List<Anagrafica> anagraficaList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            Anagrafica anagrafica;
            while (resultSet.next()) {
                int id = resultSet.getInt("idutenti");
                String nome = resultSet.getString("nome");
                String cognome = resultSet.getString("cognome");
                String indirizzo = resultSet.getString("indirizzo");
                String luogoNascita = resultSet.getString("luogo_nascita");
                Date dataNascita = resultSet.getDate("data_nascita");
                String username = resultSet.getString("username");


                anagrafica = new Anagrafica(nome, cognome, indirizzo, dataNascita, luogoNascita, username);
                anagrafica.setIdutenti(id);
                anagraficaList.add(anagrafica);
            }


        } catch(SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return anagraficaList;
    }

    public boolean insert(Anagrafica anagraficaToInsert, String username) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setString(1, anagraficaToInsert.getNome());
            preparedStatement.setString(2, anagraficaToInsert.getCognome());
            preparedStatement.setString(3, anagraficaToInsert.getIndirizzo());
            preparedStatement.setString(4, anagraficaToInsert.getLuogo_nascita());
            preparedStatement.setDate(5, (java.sql.Date) anagraficaToInsert.getData_nascita());//todo cast non necessario?
            preparedStatement.setString(6, username);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }
    }
    public Anagrafica read(int id) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String nome, cognome, indirizzo, luogoNascita, username;
            Date dataNascita;
            nome = resultSet.getString("nome");
            cognome = resultSet.getString("cognome");
            indirizzo = resultSet.getString("indirizzo");
            luogoNascita = resultSet.getString("luogo_nascita");
            dataNascita = resultSet.getDate("data_nascita");
            username = resultSet.getString("username");

            Anagrafica anagrafica = new Anagrafica(nome, cognome, indirizzo, dataNascita, luogoNascita, username);
            anagrafica.setIdutenti(resultSet.getInt("idutenti"));

            return anagrafica;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }
    }
    public Anagrafica read(String username) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setString(1, username);
            ResultSet rSet = preparedStatement.executeQuery();
            rSet.next();
            String nome, cognome, indirizzo, luogoNascita;
            Date dataNascita;

            nome = rSet.getString("nome");
            cognome = rSet.getString("cognome");
            indirizzo = rSet.getString("indirizzo");
            luogoNascita = rSet.getString("luogo_nascita");
            dataNascita = rSet.getDate("data_nascita");

            Anagrafica anagrafica = new Anagrafica(nome, cognome, indirizzo, dataNascita, luogoNascita, username);
            anagrafica.setIdutenti(rSet.getInt("idutenti"));

            return anagrafica;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }
    }



    public boolean update(Anagrafica anagraficaToUpdate, String username) {
        Connection connection = ConnectionSingleton.getInstance();

        // Check if id is present
        if (anagraficaToUpdate.getUsername() == null || anagraficaToUpdate.getUsername().equals(""))
            return false;
        System.out.println(anagraficaToUpdate);
        Anagrafica anagraficaRead = read(username);
        if (!anagraficaRead.equals(anagraficaToUpdate)) {
            try {

                if (anagraficaToUpdate.getNome() == null || anagraficaToUpdate.getNome().equals("")) {
                    anagraficaToUpdate.setNome(anagraficaRead.getNome());
                }

                if (anagraficaToUpdate.getCognome() == null || anagraficaToUpdate.getCognome().equals("")) {
                    anagraficaToUpdate.setCognome(anagraficaRead.getCognome());
                }

                if (anagraficaToUpdate.getIndirizzo() == null || anagraficaToUpdate.getIndirizzo().equals("")) {
                    anagraficaToUpdate.setIndirizzo(anagraficaRead.getIndirizzo());
                }

                if (anagraficaToUpdate.getLuogo_nascita() == null || anagraficaToUpdate.getLuogo_nascita().equals("")) {
                    anagraficaToUpdate.setLuogo_nascita(anagraficaRead.getLuogo_nascita());
                }

                if (anagraficaToUpdate.getData_nascita() == null || anagraficaToUpdate.getData_nascita().equals("")) {
                    anagraficaToUpdate.setData_nascita(anagraficaRead.getData_nascita());
                }

                if (anagraficaToUpdate.getUsername() == null || anagraficaToUpdate.getUsername().equals("")) {
                    anagraficaToUpdate.setUsername(anagraficaRead.getUsername());
                }

                // Update the user
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setString(1, anagraficaToUpdate.getNome());
                preparedStatement.setString(2, anagraficaToUpdate.getCognome());
                preparedStatement.setString(3, anagraficaToUpdate.getIndirizzo());
                preparedStatement.setString(4, anagraficaToUpdate.getLuogo_nascita());
                preparedStatement.setDate(5, (java.sql.Date)anagraficaToUpdate.getData_nascita());
                preparedStatement.setString(6, username);
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

    public boolean delete(String username) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setString(1, username);
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


