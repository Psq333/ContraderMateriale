package it.contrader.view.Impianto;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

import it.contrader.utilities.userSpecs;

public class ImpiantoDeleteView extends AbstractView {

    private Request request;
    private int idimpianto;
    private final String mode = "DELETE";

    public ImpiantoDeleteView(){

    }

    @Override
    public void showResults(Response response) {
        if(response != null){
            if((userSpecs.getInstance().getUsertype().equals("ADMIN") && (boolean)response.getBody().get("boolean"))) {
                System.out.println("Cancellazione Impianto andato a buon fine. \n");
            } else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                System.out.println("Cancellazione Impianto andato a buon fine. \n");
            }
            request = new Request("Impianto", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("Inserisci id dell'impianto da eliminare.");
        idimpianto = Integer.parseInt(getInput());
    }

    @Override
    public void submit() {
        request = new Request();
        request.getBody().put("idImpianto",idimpianto);
        request.setController("Impianto");
        request.setMethod("delete");
        MainDispatcher.getInstance().callAction(request);
    }
}
