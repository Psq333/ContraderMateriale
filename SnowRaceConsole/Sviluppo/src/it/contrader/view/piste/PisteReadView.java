package it.contrader.view.piste;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PisteDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;


public class PisteReadView extends AbstractView {

    private int idpista;

    @Override
    public void showResults(Response response) {
        if(response != null){
            PisteDTO piste = (PisteDTO) response.getBody().get("piste");
            System.out.println(piste);
            Request request = new Request("Piste", "getAll",null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("Inserimento l'ID della pista");
        idpista = Integer.parseInt(getInput());
    }

    @Override
    public void submit() {
        Request request = new Request();
        request.setController("Piste");
        request.setMethod("read");
        request.getBody().put("idpista",idpista);
        MainDispatcher.getInstance().callAction(request);
    }
}
