package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.AttrezzatureDTO;
import it.contrader.dto.NoleggioDTO;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuNoleggiAdmin extends AbstractView{
    private String choice;
    private Map<Integer, NoleggioDTO> mappa;

    public void showResults(Response response) {
        if(response != null){
            mappa = new HashMap<Integer, NoleggioDTO>();
            List<NoleggioDTO> noleggio = (List<NoleggioDTO>) response.getBody().get("noleggio");
            System.out.println("I NOLEGGI");
            System.out.println("idnoleggio\tusername\tidattrezzature\tdata_inizio\tdata_fine");
            for (NoleggioDTO noleggi : noleggio){
                mappa.put(noleggi.getIdnoleggio(),noleggi);
                System.out.println(noleggi);
                System.out.println();
            }
        }
    }

    @Override
    public void showOptions() {
    }

    @Override
    public void submit() {
        if(userSpecs.getInstance().getUsertype().equals("SUPERADMIN") || userSpecs.getInstance().getUsertype().equals("ADMIN")) {
            Request request = new Request();
            request.setController("Noleggio");
            request.setMethod("noleggio.NoleggioCRUDView");
            request.getBody().put("NoleggioDTO", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }
}

