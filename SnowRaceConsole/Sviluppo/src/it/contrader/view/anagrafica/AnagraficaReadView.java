package it.contrader.view.anagrafica;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.AnagraficaDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;

public class AnagraficaReadView extends AbstractView {

    String username;
    @Override
    public void showResults(Response response) {
        if (response != null) {
            AnagraficaDTO anagrafica = (AnagraficaDTO) response.getBody().get("anagrafica");
            System.out.println(anagrafica);
            //Request request;
            switch (userSpecs.getInstance().getUsertype().toUpperCase()){
                case "AMMINISTRATORE":
                    MainDispatcher.getInstance().callView("anagrafica.AnagraficaCRUD",null); //Todo nome view homesuperadmin
                    break;
                case"ADMIN":
                case "USER":
                    MainDispatcher.getInstance().callView("user.UserAccount",null);
                    break;
            }
            //request = new Request("", "", null);
            //MainDispatcher.getInstance().callView("",null);
        }
    }

    @Override
    public void showOptions() {
        if (userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")) {
            System.out.println("Inserisci l'username dell'utente:");
            username = getInput();
        } else
            username = userSpecs.getInstance().getUsername();
    }

    @Override
    public void submit() {
        Request request = new Request();
            request.getBody().put("username", username);
        request.setController("Anagrafica");
        request.setMethod("read");
        MainDispatcher.getInstance().callAction(request);
    }
}
