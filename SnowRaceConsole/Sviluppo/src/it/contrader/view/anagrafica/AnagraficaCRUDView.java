package it.contrader.view.anagrafica;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;

public class AnagraficaCRUDView extends AbstractView {
    private String choice;
    private boolean printed=false;
    public AnagraficaCRUDView(){
    }
    @Override
    public void showResults(Response response) {
        if (response != null){
            if (!printed)
                printed = true;
        }
    }

    @Override
    public void showOptions() {
        if (!printed){
            Request request = new Request("Anagrafica", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
        System.out.println("          Scegli l'operazione da effettuare:");
        System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");

        this.choice = getInput();

    }

    @Override
    public void submit() {
        switch (choice.toUpperCase()){
            case "L":
                MainDispatcher.getInstance().callView("anagrafica.AnagraficaRead",null);
                break;
            case "I":
                MainDispatcher.getInstance().callView("anagrafica.AnagraficaInsert",null);
                break;
            case "M":
                MainDispatcher.getInstance().callView("anagrafica.AnagraficaUpdate",null);
                break;
            case "C":
                MainDispatcher.getInstance().callView("anagrafica.AnagraficaDelete",null);
                break;
            case "B":
                MainDispatcher.getInstance().callView("superAdmin.MenuSuperAdmin",null);
                break;
            case "E":
                MainDispatcher.getInstance().callView("Home",null);
                userSpecs.getInstance().esci();
                break;
            default:
                System.out.println("\nNessuna scelta valida");
                MainDispatcher.getInstance().callView("anagraficaCRUD", null);
                break;
        }
    }
}
