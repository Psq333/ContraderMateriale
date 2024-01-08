package it.contrader.dao;

import it.contrader.model.Impianto;
import it.contrader.model.Noleggio;
import it.contrader.utils.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class NoleggioDAO implements DAO<Noleggio>{
    private final String QUERY_ALL = "SELECT * FROM noleggio";

    private final String QUERY_CREATE = "INSERT INTO noleggio (username, idAttrezzatura, data_inizio, data_fine) VALUES (?,?,?,?)";

    private final String QUERY_READ = "SELECT * FROM noleggio WHERE idnoleggio=?";

    private final String QUERY_UPDATE = "UPDATE noleggio SET username=?, idAttrezzatura=?, data_inizio=?, data_fine=? WHERE idnoleggio=?";

    private final String QUERY_DELETE = "DELETE FROM noleggio WHERE idnoleggio=?";


    private final String QUERY_ALL_USERNAME = "SELECT * " +
            "FROM noleggio INNER JOIN attrezzature " +
            "ON noleggio.idattrezzature = attrezzature.idattrezzature " +
            "WHERE noleggio.username=?";




    public NoleggioDAO(){

    }

    public List<Noleggio> getAll(){
        List<Noleggio> noleggioList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            Noleggio noleggio;
            while(resultSet.next()){
                int idnoleggio = resultSet.getInt("idnoleggio");
                String username = resultSet.getString("username");
                int idattrezzatura = resultSet.getInt("idattrezzatura");
                Date data_inizio = resultSet.getDate("data_inizio");
                Date data_fine  = resultSet.getDate("data_fine");
                noleggio = new Noleggio(username, idattrezzatura, data_inizio, data_fine);
                noleggio.setIdNoleggio(idnoleggio);
                noleggioList.add(noleggio);
            }
        } catch (SQLException e) {
            System.out.println("Errore  in NoleggioDAO getAll()");
            e.printStackTrace();
            return null;
        }
        return noleggioList;
    }

    public boolean insert(Noleggio noleggioDaInserire){
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setString(1, noleggioDaInserire.getUsername());
            preparedStatement.setInt(2, noleggioDaInserire.getIdAttrezzatura());
            preparedStatement.setDate(3, noleggioDaInserire.getData_inizio());
            preparedStatement.setDate(4, noleggioDaInserire.getData_fine());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Errore  in NoleggioDAO insert()");
            e.printStackTrace();
            return false;
        }
    }

    public Noleggio read(int idNoleggio) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            System.out.println(idNoleggio);
            preparedStatement.setInt(1, idNoleggio);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String username;
            java.sql.Date data_inizio;
            java.sql.Date data_fine;
            username = resultSet.getString("username");
            int idattrezzatura = resultSet.getInt("idattrezzatura");
            data_inizio  = resultSet.getDate("data_inizio");
            data_fine  = resultSet.getDate("data_fine");
            Noleggio noleggio = new Noleggio(username, idattrezzatura, data_inizio, data_fine);
            noleggio.setIdNoleggio(resultSet.getInt("idnoleggio"));

            return noleggio;
        } catch (SQLException e) {
            System.out.println("Errore  in NoleggioDAO read()");
            e.printStackTrace();
            return null;
        }
    }


    public boolean update(Noleggio noleggioDaModificare) {
        Connection connection = ConnectionSingleton.getInstance();

        // Check if id is present
        if (noleggioDaModificare.getIdNoleggio() == 0)
            return false;

        Noleggio noleggioLetto = read(noleggioDaModificare.getIdNoleggio());
        if (!noleggioLetto.equals(noleggioDaModificare)) {
            try {
                // Facciamo gli if per riempire le variabili dell'oggetto che sono vuote
                if (noleggioDaModificare.getUsername() == null || noleggioDaModificare.getUsername().equals("")) {
                    noleggioDaModificare.setUsername(noleggioLetto.getUsername());
                }

                if (noleggioDaModificare.getIdAttrezzatura() == 0 ){
                    noleggioDaModificare.setIdAttrezzatura(noleggioLetto.getIdAttrezzatura());
                }

                if (noleggioDaModificare.getData_inizio() == null || noleggioDaModificare.getData_inizio().equals("")) {
                    noleggioDaModificare.setData_inizio(noleggioLetto.getData_inizio());
                }

                if (noleggioDaModificare.getData_fine() == null || noleggioDaModificare.getData_fine().equals("")) {
                    noleggioDaModificare.setData_fine(noleggioLetto.getData_fine());
                }

                // Update the user
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setInt(5, noleggioDaModificare.getIdNoleggio());
                preparedStatement.setString(1, noleggioDaModificare.getUsername());
                preparedStatement.setInt(2, noleggioDaModificare.getIdAttrezzatura());
                preparedStatement.setDate(3, noleggioDaModificare.getData_inizio());
                preparedStatement.setDate(4, noleggioDaModificare.getData_fine());

                int a = preparedStatement.executeUpdate();
                return a > 0;

            } catch (SQLException e) {
                System.out.println("Errore  in NoleggioDAO update()");
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public boolean delete(int idNoleggio) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, idNoleggio);
            int n = preparedStatement.executeUpdate();
            if (n != 0)
                return true;

        } catch (SQLException e) {
            System.out.println("Errore  in NoleggioDAO delete()");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public List<Noleggio> getAll(String username){
        List<Noleggio> noleggioList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL_USERNAME);
            Noleggio noleggio;
            while(resultSet.next()){
                int idnoleggio = resultSet.getInt("idnoleggio");
                int idattrezzatura = resultSet.getInt("idattrezzatura");
                Date data_inizio = resultSet.getDate("data_inizio");
                Date data_fine  = resultSet.getDate("data_fine");
                noleggio = new Noleggio(username, idattrezzatura, data_inizio, data_fine);
                noleggio.setIdNoleggio(idnoleggio);
                noleggioList.add(noleggio);
            }
        } catch (SQLException e) {
            System.out.println("Errore  in NoleggioDAO getAll()");
            e.printStackTrace();
            return null;
        }
        return noleggioList;
    }
}


