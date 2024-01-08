package it.contrader.dao;

import it.contrader.model.Piste;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.Impianto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImpiantoDAO implements DAO<Impianto>{
    private final String QUERY_ALL = "SELECT * FROM impianti";

    private final String QUERY_CREATE = "INSERT INTO impianti (nome, descrizione, luogo, amministratore) VALUES (?,?,?,?)";

    private final String QUERY_READ = "SELECT * FROM impianti WHERE idimpianti=?";

    private final String QUERY_UPDATE = "UPDATE impianti SET nome=?, descrizione=?, luogo=?, amministratore=? WHERE idimpianti=?";

    private final String QUERY_DELETE = "DELETE FROM impianti WHERE idimpianti=?";
    private final String QUERY_GETIMPIANTIAMMINISTRATORE = "SELECT * FROM impianti WHERE amministratore=?";
    private final String QUERY_GET_PISTE_IMPIANTO = "SELECT idimpianto, idpista, difficolta, prezzo, prenmax FROM impianti INNER JOIN piste ON impianti.idimpianti = piste.idimpianto";


    public ImpiantoDAO(){

    }

    public List<Impianto> getAll(){
        List<Impianto> impiantoList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            Impianto impianto;
            while(resultSet.next()){
                int idimpianto = resultSet.getInt("idimpianti");
                String nome = resultSet.getString("nome");
                String descrizione = resultSet.getString("descrizione");
                String luogo  = resultSet.getString("luogo");
                String amministratore = resultSet.getString("amministratore");
                impianto = new Impianto(nome, descrizione, luogo, amministratore);
                impianto.setIdImpianto(idimpianto);
                impiantoList.add(impianto);
            }
        } catch (SQLException e) {
            System.out.println("Errore  in ImpiantiDAO getAll()");
            e.printStackTrace();
            return null;
        }
        return impiantoList;
    }

    public boolean insert(Impianto impiantoDaInserire){
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setString(1, impiantoDaInserire.getNome());
            preparedStatement.setString(2, impiantoDaInserire.getDescrizione());
            preparedStatement.setString(3, impiantoDaInserire.getLuogo());
            preparedStatement.setString(4, impiantoDaInserire.getAmministratore());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Errore  in ImpiantiDAO insert()");
            e.printStackTrace();
            return false;
        }
    }

    public Impianto read(int idImpianto) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            System.out.println(idImpianto);
            preparedStatement.setInt(1, idImpianto);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String nome, descrizione, luogo, amministratore;
            nome = resultSet.getString("nome");
            descrizione  = resultSet.getString("descrizione");
            luogo  = resultSet.getString("luogo");
            amministratore  = resultSet.getString("amministratore");
            Impianto impianto = new Impianto(nome, descrizione, luogo, amministratore);
            impianto.setIdImpianto(resultSet.getInt("idimpianti"));

            return impianto;
        } catch (SQLException e) {
            System.out.println("Errore  in ImpiantiDAO read()");
            e.printStackTrace();
            return null;
        }
    }


    public boolean update(Impianto impiantoDaModificare) {
        Connection connection = ConnectionSingleton.getInstance();

        // Check if id is present
        if (impiantoDaModificare.getIdImpianto() == 0)
            return false;

        Impianto impiantoLetto = read(impiantoDaModificare.getIdImpianto());
        if (!impiantoLetto.equals(impiantoDaModificare)) {
            try {
                // Facciamo gli if per riempire le variabili dell'oggetto che sono vuote
                if (impiantoDaModificare.getNome() == null || impiantoDaModificare.getNome().equals("")) {
                    impiantoDaModificare.setNome(impiantoLetto.getNome());
                }

                if (impiantoDaModificare.getDescrizione() == null || impiantoDaModificare.getDescrizione().equals("")) {
                    impiantoDaModificare.setDescrizione(impiantoLetto.getDescrizione());
                }

                if (impiantoDaModificare.getLuogo() == null || impiantoDaModificare.getLuogo().equals("")) {
                    impiantoDaModificare.setLuogo(impiantoLetto.getLuogo());
                }

                if (impiantoDaModificare.getAmministratore() == null || impiantoDaModificare.getAmministratore().equals("")) {
                    impiantoDaModificare.setAmministratore(impiantoLetto.getAmministratore());
                }

                // Update the user
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setInt(5, impiantoDaModificare.getIdImpianto());
                preparedStatement.setString(1, impiantoDaModificare.getNome());
                preparedStatement.setString(2, impiantoDaModificare.getDescrizione());
                preparedStatement.setString(3, impiantoDaModificare.getLuogo());
                preparedStatement.setString(4, impiantoDaModificare.getAmministratore());

                int a = preparedStatement.executeUpdate();
                return a > 0;

            } catch (SQLException e) {
                System.out.println("Errore  in ImpiantiDAO update()");
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean delete(int idImpianto) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, idImpianto);
            int n = preparedStatement.executeUpdate();
            if (n != 0)
                return true;

        } catch (SQLException e) {
            System.out.println("Errore  in ImpiantiDAO delete()");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public List<Impianto> getImpiantiAmministratore(String username){
        List<Impianto> impiantoList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_GETIMPIANTIAMMINISTRATORE);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            Impianto impianto;
            while(resultSet.next()){
                int idimpianto = resultSet.getInt("idimpianti");
                String nome = resultSet.getString("nome");
                String descrizione = resultSet.getString("descrizione");
                String luogo  = resultSet.getString("luogo");
                String amministratore = resultSet.getString("amministratore");
                impianto = new Impianto(nome, descrizione, luogo, amministratore);
                impianto.setIdImpianto(idimpianto);
                impiantoList.add(impianto);
            }
        } catch (SQLException e) {
            System.out.println("Errore  in ImpiantiDAO getAll()");
            e.printStackTrace();
            return null;
        }
        return impiantoList;
    }


    public Map<Integer, List<Piste>> getPisteImpianto(){
        Map<Integer, List<Piste>> mappa = new HashMap<Integer, List<Piste>>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(QUERY_GET_PISTE_IMPIANTO);

            Piste pista;
            while(resultSet.next()){
                int idimpianto = resultSet.getInt("idimpianto");
                int idpista = resultSet.getInt("idpista");
                String difficolta = resultSet.getString("difficolta");
                Double prezzo  = resultSet.getDouble("prezzo");
                int prenmax = resultSet.getInt("prenmax");
                pista = new Piste(idpista,idimpianto,difficolta,prezzo,prenmax);
                if(!mappa.containsKey(idimpianto))
                    mappa.put(idimpianto,new ArrayList<Piste>());
                mappa.get(idimpianto).add(pista);
            }
        } catch (SQLException e) {
            System.out.println("Errore  in ImpiantiDAO getPisteImpianto()");
            e.printStackTrace();
            return null;
        }
        return mappa;

    }
}
