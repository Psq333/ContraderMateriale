package it.contrader.view.Impianto;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.utilities.userSpecs;

public class ImpiantoCRUDView extends AbstractView {

    private String choice;

    public ImpiantoCRUDView(){

    }

    @Override
    public void showResults(Response response){
        /*if(response != null) {
            ImpiantoDTO impiantoDTO = (ImpiantoDTO) response.getBody().get("impiantoDTO");
            System.out.println(impiantoDTO);
        }*/
    }

    @Override
    public void showOptions() {
        System.out.println("          Scegli l'operazione da effettuare:");
        System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");

        this.choice = getInput();
        //System.out.println(this.choice);
    }

    @Override
    public void submit() {
        switch (choice.toUpperCase()){
            case "L":
                MainDispatcher.getInstance().callView("Impianto.ImpiantoRead",null);
                break;
            case "I":
                MainDispatcher.getInstance().callView("Impianto.ImpiantoInsert",null);
                break;
            case "M":
                MainDispatcher.getInstance().callView("Impianto.ImpiantoUpdate",null);
                break;
            case "C":
                MainDispatcher.getInstance().callView("Impianto.ImpiantoDelete",null);
                break;
            case "B":
                if(userSpecs.getInstance().getUsertype().equals("ADMIN"))
                    MainDispatcher.getInstance().callView("user.HomeAdmin",null);
                else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE"))
                    MainDispatcher.getInstance().callView("superAdmin.MenuSuperAdmin",null);
                break;
            case "E":
                userSpecs.getInstance().esci();
                MainDispatcher.getInstance().callView("Home",null);
                break;
            default:
                System.out.println("\nNessuna scelta valida");
                Request request = new Request("Impianto", "getAll", null);
                MainDispatcher.getInstance().callAction(request);
                break;
        }
    }
}
