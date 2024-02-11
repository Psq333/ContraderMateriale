package it.contrader.view.noleggio;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.NoleggioDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class NoleggioReadView extends AbstractView {
    private int idnoleggio;

    public NoleggioReadView(){

    }

    @Override
    public void showResults(Response response) {
        if(response != null){
            NoleggioDTO noleggio = (NoleggioDTO) response.getBody().get("noleggio");
            System.out.println(noleggio);
            Request request = new Request("noleggio", "getAll", null);
            MainDispatcher.getInstance().callAction(request);

        }

    }

    @Override
    public void showOptions() {
        System.out.println("Inserisci l'id del noleggio");

    }

    @Override
    public void submit() {

    }
}
