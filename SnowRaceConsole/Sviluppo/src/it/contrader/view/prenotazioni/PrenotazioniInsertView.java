package it.contrader.view.prenotazioni;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.scannerUtils;
import it.contrader.view.AbstractView;

import java.sql.Date;

public class PrenotazioniInsertView extends AbstractView {

    private int idpista;
    private String username;
    private Date data_inizio, data_fine;
    @Override
    public void showResults(Response response) {
        if (response!=null) {
            System.out.println("Inserimento andato a buon fine.\n");
            //TODO effettuare controllo admin user
            Request request = new Request("Prenotazioni", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("Inserisci l'id della pista:");
        scannerUtils.getInstance().askInt();
        idpista = scannerUtils.getInstance().getNumIn();
        System.out.println("Inserisci l'username: ");
        username = getInput();
        System.out.println("Inserisci data inizio prenotazione (dd-MM-yyyy)");
        scannerUtils.getInstance().askDate();
        data_inizio = scannerUtils.getInstance().getDateIn();
        System.out.println("Inserisci data fine prenotazione (dd-MM-yyyy)");
        scannerUtils.getInstance().askDate();
        data_fine = scannerUtils.getInstance().getDateIn();
    }

    @Override
    public void submit() {
        Request request = new Request();
        request.setController("Prenotazioni");
        request.setMethod("insert");
        PrenotazioniDTO prenotazioni = new PrenotazioniDTO(idpista, username, data_inizio, data_fine);
        request.getBody().put("prenotazioniToInsert", prenotazioni);
        MainDispatcher.getInstance().callAction(request);

    }
}

