package it.contrader.view.piste;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class PisteCRUDView extends AbstractView {

    private String choice;

    @Override
    public void showResults(Response response) {
        // richieste impianti di pascal
    }

    @Override
    public void showOptions() {
        System.out.println("---|| Scegli l'operazione da effettuare sulle piste ||---");
        System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");
        this.choice = getInput();
    }

    @Override
    public void submit() {
        switch (choice.toUpperCase()) {

            case "L":
                MainDispatcher.getInstance().callView("piste.PisteRead", null);
                break;
            case "I":
                MainDispatcher.getInstance().callView("piste.PisteInsert", null);
                break;
            case "M":
                MainDispatcher.getInstance().callView("piste.PisteUpdate", null);
                break;
            case "C":
                MainDispatcher.getInstance().callView("piste.PisteDelete", null);
                break;
            case "B":
                MainDispatcher.getInstance().callView("HomeAdmin", null);
                break;
            case "E":
                MainDispatcher.getInstance().callView("Login", null);
                break;
            default:
                System.out.println("\nNessuna scelta valida");
                Request request = new Request("User", "getAll", null);
                MainDispatcher.getInstance().callAction(request);
        }
    }
}
