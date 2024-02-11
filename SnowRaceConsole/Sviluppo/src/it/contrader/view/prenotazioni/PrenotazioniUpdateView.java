package it.contrader.view.prenotazioni;
import it.contrader.utilities.scannerUtils;
import it.contrader.dto.PrenotazioniDTO;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

import java.sql.Date;

public class PrenotazioniUpdateView extends AbstractView {

    private int idprenotazione;
    private int idPista;
    private  String username;
    private Date dataInizio;
    private Date dataFine;

    public PrenotazioniUpdateView() {
    }

    @Override
    public void showResults(Response response) {
        if (response!=null) {
            System.out.println("Modifica andata a buon fine.\n");
            Request request = new Request("Prenotazioni", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }
    @Override
    public void showOptions() {
        try {
            /*System.out.println("Inserisci l'id dell'utente di cui vuoi modificare la prenotazione:");
            id = Integer.parseInt(getInput());*/
            System.out.println("Inserisci l'id della prenotazione:");
            idprenotazione = Integer.parseInt(getInput());
            System.out.println("Inserisci la nuova pista:");
            scannerUtils.getInstance().askInt();
            idPista = scannerUtils.getInstance().getNumIn();
            //System.out.println("Inserisci il tuo username:");
            //username = getInput();
            System.out.println("Inserisci la nuova data di inizio:");
            scannerUtils.getInstance().askDate();
            dataInizio = scannerUtils.getInstance().getDateIn();
            System.out.println("Inserisci la nuova data di fine:");
            scannerUtils.getInstance().askDate();
            dataFine = scannerUtils.getInstance().getDateIn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void submit() {
        Request request = new Request();
        PrenotazioniDTO prenotazioniToUpdate = new PrenotazioniDTO(idprenotazione, idPista, username, dataInizio, dataFine);
        request.getBody().put("prenotazioniToUpdate", prenotazioniToUpdate);
        request.setMethod("update");
        request.setController("Prenotazioni");
        MainDispatcher.getInstance().callAction(request);
    }


}
