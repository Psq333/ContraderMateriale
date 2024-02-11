package it.contrader.view;

import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.scannerUtils;


public class HomeView extends AbstractView {

    private String userSelection;


    public void showResults(Response response) {
    }
    public void showOptions() {

        System.out.println("----- .:HOME:. ----\n\n[L]ogin\n[R]egistrazione\nVisualizza [I]mpianti");
        this.userSelection = getInput();

    }

    public void submit() {
        switch(this.userSelection.toUpperCase()) {
            case "L":
                MainDispatcher.getInstance().callView("Login", null);
                break;
            case "R":
                MainDispatcher.getInstance().callView("Signin", null);
                break;
            case "I":
                Response responseNoUser = new Response();
                responseNoUser.getBody().put("noLog", "");
                MainDispatcher.getInstance().callView("Impianto.ListImpianti", responseNoUser);//todo piste che richiama impianto. adesso richiama solo impianto
                break;
            default:
                System.out.println("Input non valido.\nInserisci una delle lettere tra parentesi quadra per selezionare una funzione");
                MainDispatcher.getInstance().callView("Home", null);
                break;
        }
    }

}
