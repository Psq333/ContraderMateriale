package it.contrader.view.user;


import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;

public class HomeAdminView extends AbstractView{

    String choice;

    Request request;

    public HomeAdminView(){
        request = new Request();
    }

    @Override
    public void showResults(Response response) {

    }

    @Override
    public void showOptions() {
        System.out.println("\n Benvenuto in SAMPLE PROJECT " + userSpecs.getInstance().getUsername() + "\n\n");
        System.out.println("-------------MENU ADMIN------------\n\n");
        System.out.println("Miei [I]mpianti\nMie [P]iste\n[A]ccount\n[E]sci");
        choice = getInput();
    }

    @Override
    public void submit() {
        switch(choice.toUpperCase()){
            case"I":
                MainDispatcher.getInstance().callAction(new Request("Impianto", "getAll",null));//todo tbL
                break;
            case "P":
                MainDispatcher.getInstance().callAction(new Request("Piste","getAll",null));
                break;
            case "A":
                MainDispatcher.getInstance().callView("user.UserAccount", null);
                break;
            case "E":
                MainDispatcher.getInstance().callView("Home", null);
                userSpecs.getInstance().esci();
                break;
            default:
                System.out.println("Inserisci una delle lettere tra parentesi quadre per selezionare la funzione");
                MainDispatcher.getInstance().callView("HomeAdmin", null);
                break;
        }
        //MainDispatcher.getInstance().callAction(this.request);
    }

}
