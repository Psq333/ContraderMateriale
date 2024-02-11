package it.contrader.view.superAdmin;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import it.contrader.utilities.userSpecs;

public class MenuSuperAdminView extends AbstractView {

    private String choice;

    //Inserimento in una Mappa senza l'utilizzo di tante put
    Map map = (Map) Stream.of(new String[][]{{"C", "Credenziali"}, {"A", "Anagrafica"}, {"I", "Impianto"}, {"P", "Piste"}, {"AT", "Attrezzatura"}, {"N", "Noleggi"},  {"PR", "Prenotazioni"}, {"E", "Esci"} }).collect(Collectors.toMap(p -> p[0], p -> p[1]));;

    @Override
    public void showResults(Response response) {
        System.out.println("----------MENU SUPER ADMIN----------");
    }

    @Override
    public void showOptions() {
        System.out.println("          Scegli l'operazione CRUD da effettuare:");
        System.out.println("[C]redenziali [A]nagrafica [I]mpianto [P]iste [At]trezzatura [N]oleggi [Pr]enotazioni [E]sci");

        this.choice = getInput().toUpperCase();
    }

    @Override
    public void submit() {
        if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
            Request request = new Request();
            request.setController("SuperAdmin"); //SuperAdminController
            request.setMethod((String) map.get(this.choice));
            request.setBody(null);
            MainDispatcher.getInstance().callAction(request);
        }

    }
}
