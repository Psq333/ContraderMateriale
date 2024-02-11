package it.contrader.view.prenotazioni;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class PrenotazioniDeleteView extends AbstractView {


    private Request request;

    private int id;
    private final String mode = "DELETE";

    public PrenotazioniDeleteView() {
    }

    public void showResults(Response response) {
        if (response!=null) {
            System.out.println("Cancellazione andata a buon fine.\n");
            Request request = new Request("Prenotazioni", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    public void showOptions() {
        System.out.println("Inserisci l'id della prenotazione:");
        id = Integer.parseInt(getInput());

    }

    public void submit() {
        request = new Request();
        request.getBody().put("id", id);
        request.setController("Prenotazioni");
        request.setMethod("delete");
        MainDispatcher.getInstance().callAction(request);
    }



}