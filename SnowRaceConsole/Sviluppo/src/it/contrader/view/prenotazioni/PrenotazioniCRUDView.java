package it.contrader.view.prenotazioni;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class PrenotazioniCRUDView extends AbstractView {
    private String choice;
    public PrenotazioniCRUDView(){

    }

    public void showResults(Response response){
    }

    @Override
    public void showOptions() {
        System.out.println("          Scegli l'operazione da effettuare:");
        System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");

        this.choice = getInput();

    }

    @Override
    public void submit() {
        switch (choice.toUpperCase()){
            case "L":
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniRead",null);
                break;
            case "I":
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniInsert",null);
                break;
            case "M":
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniUpdate",null);
                break;
            case "C":
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniDelete",null);
                break;
            case "B":
                MainDispatcher.getInstance().callView("HomeAdmin",null);
                break;
            case "E":
                MainDispatcher.getInstance().callView("Login",null);
                break;
            default:
                System.out.println("\nNessuna scelta valida");
                Request request = new Request("Prenotazioni", "getAll", null);
                MainDispatcher.getInstance().callAction(request);
                break;
        }
    }
}
