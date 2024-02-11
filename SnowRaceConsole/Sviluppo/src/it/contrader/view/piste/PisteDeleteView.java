package it.contrader.view.piste;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.PisteDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;

public class PisteDeleteView extends AbstractView {

    private Request request;
    private int id;
    private final String mode = "DELETE";
    public PisteDeleteView(){}

    @Override
    public void showResults(Response response) {
        if (response!=null) {
            if((userSpecs.getInstance().getUsertype().equals("ADMIN") && (boolean)response.getBody().get("boolean"))) {
                System.out.println("Cancellazione Impianto andato a buon fine. \n");
            } else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                System.out.println("Cancellazione Impianto andato a buon fine. \n");
            }
            Request request = new Request("Piste", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("Inserisci id della pista:");
        id = Integer.parseInt(getInput());

    }

    @Override
    public void submit() {
        request = new Request();
        request.getBody().put("id", id);
        request.setController("Piste");
        request.setMethod("delete");
        MainDispatcher.getInstance().callAction(request);
    }
}
