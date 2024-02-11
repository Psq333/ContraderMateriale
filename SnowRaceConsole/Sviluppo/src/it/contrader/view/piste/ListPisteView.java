package it.contrader.view.piste;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.PisteDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;

import java.util.List;

public class ListPisteView extends AbstractView {
    @Override
    public void showResults(Response response) {
        if (response != null) {
            System.out.println("\n------------------- Visualizzazione piste ----------------\n");
            System.out.println("ID Piste\tID Impianto\tDifficoltà\tPrezzo\t Prenotazioni Massime");
            System.out.println("----------------------------------------------------\n");

            List<PisteDTO> pistes = (List<PisteDTO>) response.getBody().get("pistes");
            for (PisteDTO u: pistes)
                System.out.println(u);
            System.out.println();
        }
    }

    @Override
    public void showOptions() {

    }

    @Override
    public void submit() {
        if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE") || userSpecs.getInstance().getUsertype().equals("ADMIN")) {
            Request request = new Request();
            request.setController("Piste");
            request.setMethod("piste.PisteCRUD");
            request.getBody().put("pisteDTO", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }
}
