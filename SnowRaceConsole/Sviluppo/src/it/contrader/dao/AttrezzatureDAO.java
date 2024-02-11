package it.contrader.dao;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Attrezzature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// todo controllare la stampa della read

public class AttrezzatureDAO {

    private final String QUERY_ALL = "SELECT * FROM attrezzature";

    private final String QUERY_CREATE = "INSERT INTO attrezzature (prezzo,descrizione,nome,idimpianto) VALUES (?,?,?,?)";

    private final String QUERY_READ = "SELECT * FROM attrezzature WHERE idattrezzatura=?";

    private final String QUERY_UPDATE = "UPDATE attrezzature SET prezzo=?, descrizione=?, nome=?, idimpianto =? WHERE idattrezzatura=?";

    private final String QUERY_DELETE = "DELETE FROM attrezzature WHERE idattrezzatura=?";

    public AttrezzatureDAO(){

    }

    public List<Attrezzature> getAll() {
        List<Attrezzature> attrezzatureList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            Attrezzature attrezzature;
            while (resultSet.next()) {
                int id = resultSet.getInt("idattrezzatura");
                Double prezzo = resultSet.getDouble("prezzo");
                String descrizione = resultSet.getString("descrizione");
                String nome = resultSet.getString("nome");
                int idimpianto = resultSet.getInt("idimpianto");
                attrezzature = new Attrezzature(id,prezzo,descrizione,nome,idimpianto);
                attrezzature.setIdattrezzatura(id);
                attrezzatureList.add(attrezzature);
            }

        } catch(SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return attrezzatureList;
    }


    public boolean insert(Attrezzature attrezzatureToInsert) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setDouble(1, attrezzatureToInsert.getPrezzo());
            preparedStatement.setString(2, attrezzatureToInsert.getDescrizione());
            preparedStatement.setString(3, attrezzatureToInsert.getNome());
            preparedStatement.setInt(4,attrezzatureToInsert.getIdimpianto());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }
    }

    public Attrezzature read(int idAttrezzature) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            System.out.println(idAttrezzature);
            preparedStatement.setInt(1, idAttrezzature);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            double prezzo;
            String descrizione, nome;
            prezzo = resultSet.getDouble("prezzo");
            descrizione = resultSet.getString("descrizione");
            nome = resultSet.getString("nome");
            Attrezzature attrezzature = new Attrezzature(prezzo, descrizione, nome);
            attrezzature.setIdattrezzatura(resultSet.getInt("idattrezzatura"));
            return attrezzature;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }
    }


    public boolean update(Attrezzature attrezzatureToUpdate) {
        Connection connection = ConnectionSingleton.getInstance();

        // Check if id is present
        if (attrezzatureToUpdate.getIdattrezzatura() == 0)
            return false;

        Attrezzature attrezzatureRead = read(attrezzatureToUpdate.getIdattrezzatura());
        if (!attrezzatureRead.equals(attrezzatureToUpdate)) {
            try {

                if (attrezzatureToUpdate.getPrezzo() == 0)
                    return false;


                if (attrezzatureToUpdate.getNome() == null || attrezzatureToUpdate.getNome().equals("")) {
                    attrezzatureToUpdate.setNome(attrezzatureRead.getNome());
                }

                if (attrezzatureToUpdate.getDescrizione() == null || attrezzatureToUpdate.getDescrizione().equals("")) {
                    attrezzatureToUpdate.setDescrizione(attrezzatureRead.getDescrizione());
                }

                // Update the user
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);

                preparedStatement.setDouble(1, attrezzatureToUpdate.getPrezzo());
                preparedStatement.setString(2, attrezzatureToUpdate.getDescrizione());
                preparedStatement.setString(3, attrezzatureToUpdate.getNome());
                preparedStatement.setInt(4, attrezzatureToUpdate.getIdimpianto());
                preparedStatement.setInt(5,attrezzatureToUpdate.getIdattrezzatura());
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

    public boolean delete(int idAttrezzature) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, idAttrezzature);
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
