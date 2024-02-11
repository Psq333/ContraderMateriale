package it.contrader.view.noleggio;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class NoleggioDeleteView extends AbstractView {

    private Request request;

    private int idnoleggio;

    private final String mode = "DELETE";

    public NoleggioDeleteView(){

    }



    @Override
    public void showResults(Response response) {
        if(response!=null) {
            System.out.println("Cancellazione andata a buon fine.\n");
            Request request = new Request("noleggio", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    public void showOptions() {
        System.out.println("Inserisci id dell'utente:");
        idnoleggio = Integer.parseInt(getInput());

    }

    public void submit(){
        request = new Request();
        request.getBody().put("id", idnoleggio);
        request.setController("noleggio");
        request.setMethod("delete");
        MainDispatcher.getInstance().callAction(request);


    }
}
