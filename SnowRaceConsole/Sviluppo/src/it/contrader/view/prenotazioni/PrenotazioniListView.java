package it.contrader.view.prenotazioni;
import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

import java.util.List;

public class PrenotazioniListView extends AbstractView {
    @Override
    public void showResults(Response response) {
        if (response != null) {
            System.out.println("\n------------------- Visualizzazione prenotazioni ----------------\n");
            System.out.println("ID prenotazione\tID Pista\tUsername\tData Inizio\t Data Fine");
            System.out.println("-------------------------------------------------------------------\n");

            List<PrenotazioniDTO> prenotaz = (List<PrenotazioniDTO>) response.getBody().get("prenotazionistorico");
            for (PrenotazioniDTO u: prenotaz)
                System.out.println(u);
            System.out.println();
        }
    }

    @Override
    public void showOptions() {

    }

    @Override
    public void submit() {
        /*Request request = new Request();
        request.setController("Prenotazioni");
        request.setMethod("getStorico");
        request.getBody().put("prenotazioniDTO", null);*/
        MainDispatcher.getInstance().callView("user.HomeUser",null);
    }
}
