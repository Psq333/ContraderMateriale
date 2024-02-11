package it.contrader.view.prenotazioni;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class PrenotazioniReadView extends AbstractView {
    private int id;

    public PrenotazioniReadView() {
    }

    @Override
    public void showResults(Response response) {
        if (response != null) {
            PrenotazioniDTO prenotazioni = (PrenotazioniDTO) response.getBody().get("prenotazioni");
            System.out.println(prenotazioni);
            Request request = new Request("Prenotazioni", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }
    @Override
    public void showOptions() {
        System.out.println("Inserisci l'ID dell'utente:");
        id = Integer.parseInt(getInput());
    }

    @Override
    public void submit() {
        Request request = new Request();
        request.getBody().put("id", id);
        request.setController("Prenotazioni");
        request.setMethod("read");
        MainDispatcher.getInstance().callAction(request);
    }


}
