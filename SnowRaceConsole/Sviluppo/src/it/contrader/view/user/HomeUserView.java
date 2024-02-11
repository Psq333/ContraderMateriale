package it.contrader.view.user;


import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;

public class HomeUserView extends AbstractView{

    String choice;

    Request request;

    public HomeUserView(){
        request = new Request();
    }

    @Override
    public void showResults(Response response) {
        if(response!=null){

        }
    }

    @Override
    public void showOptions() {
        System.out.println("\n Benvenuto in SAMPLE PROJECT " + userSpecs.getInstance().getUsername() + "\n\n");//todo TITOLO
        System.out.println("-------------MENU UTENTE------------\n\n");
        System.out.println("Nuova [P]renotazione\n[M]odifica prenotazione\n[S]torico prenotazioni\n[N]oleggio\n[A]ccount\n[R]icerca\n[E]sci");
        choice = getInput();
    }

    @Override
    public void submit() {
        switch (choice.toUpperCase()) {
            case "P":
                MainDispatcher.getInstance().callView("MenuPrenotazioniUser", null);
                break;
            case "M":
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniUpdate", null);
                break;
            case "S":
                MainDispatcher.getInstance().callAction(new Request("Prenotazioni", "getStorico",null));
                break;
            case "N":
                MainDispatcher.getInstance().callView("noleggio.NoleggioInsert", null);
                break;
            case "A":
                MainDispatcher.getInstance().callView("user.UserAccount", null);
                break;
            case "R":
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniSearch", null);//todo
                break;
            case "E":
                MainDispatcher.getInstance().callView("Home", null);
                userSpecs.getInstance().esci();
                break;
            default:
                System.out.println("Input non valido.\nInserisci una delle lettere tra parentesi quadra per selezionare una funzione");
                MainDispatcher.getInstance().callView("HomeUser", null);
                break;
        }
    }

}
