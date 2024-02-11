package it.contrader.view.prenotazioni;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrenotazioniSearchView extends AbstractView {

    private Map<Integer, PrenotazioniDTO> mappa;
    private String key, what, who;


    @Override
    public void showResults(Response response) {
        if(response != null){
            mappa = new HashMap<Integer,PrenotazioniDTO>();
            List<PrenotazioniDTO> prenot = (List<PrenotazioniDTO>) response.getBody().get("prenotazioni");
            System.out.println("-------------PRENOTAZIONI filtrate-------------");
            for (PrenotazioniDTO prenn : prenot){
                mappa.put(prenn.getIdprenotazione(),prenn);
                System.out.println(prenot);
                System.out.println();
            }
        }
    }

    @Override
    public void showOptions() {
        if (userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")) {
            System.out.println("Inserisci l'username dell'user di cui vuoi conoscere le prenotazioni:");
            who = getInput();
        }
        System.out.println("Scegli un filtro ([D]ata, [P]ista:");//, [I]mpianto):");
        boolean a = true;
        while (a) {
            key = getInput();
            switch (key.toUpperCase()) {
                case "D":
                    System.out.println("Data di partenza (dd/MM/yyyy): ");
                    key = "data_inizio";
                    a = false;
                    break;
                case "P":
                    System.out.println("id della Pista: ");
                    key = "idpista";
                    a = false;
                    break;
                //case "I":
               // key = "";
                //break;
                default:
                    System.out.println("Inserisci una scelta valida");
                    break;
            }
        }
        what = getInput();
    }


    @Override
    public void submit() {
        Request request = new Request();
        request.setController("Prenotazioni");
        request.setMethod("search");
        request.getBody().put("key", key);
        request.getBody().put("what", what);
        request.getBody().put("who", who);
        MainDispatcher.getInstance().callAction(request);
    }
}
