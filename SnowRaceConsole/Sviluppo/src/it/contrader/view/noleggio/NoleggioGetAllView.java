package it.contrader.view.noleggio;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class NoleggioGetAllView extends AbstractView {

    public NoleggioGetAllView(){

    }

    @Override
    public void showResults(Response response) {
        Request request = new Request("noleggi", "getAll", null);
        MainDispatcher.getInstance().callAction(request);

    }

    @Override
    public void showOptions() {

    }

    @Override
    public void submit() {

    }
}
