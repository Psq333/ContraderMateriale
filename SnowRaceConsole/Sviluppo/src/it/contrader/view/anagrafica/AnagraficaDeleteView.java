package it.contrader.view.anagrafica;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
public class AnagraficaDeleteView extends AbstractView {
    private Request request;
    private String username;

    public AnagraficaDeleteView(){}
    @Override
    public void showResults(Response response) {
        if (response!=null) {
            System.out.println("Cancellazione andata a buon fine.\n");
            MainDispatcher.getInstance().callView("superAdmin.MenuSuperAdmin", null);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("Inserisci username dell'utente:");
        username = getInput();
    }

    @Override
    public void submit() {
        request = new Request();
        request.getBody().put("username", username);
        request.setController("Anagrafica");
        request.setMethod("delete");
        MainDispatcher.getInstance().callAction(request);
    }
}
