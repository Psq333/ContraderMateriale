package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Prenotazioni;
import it.contrader.utilities.userSpecs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuPrenotazioniAdminView extends AbstractView{
    private String choice;
    private Map<Integer, PrenotazioniDTO> mappa;

    public void showResults(Response response) {
        if(response != null){
            mappa = new HashMap<Integer, PrenotazioniDTO>();
            List<PrenotazioniDTO> prenotazioni = (List<PrenotazioniDTO>) response.getBody().get("prenotazioni");
            System.out.println("LE PRENOTAZIONI");
            System.out.println("idprenotazione\tidpista\tusername\tdata_inizio\tdata_fine");
            for (PrenotazioniDTO prenotazione : prenotazioni){
                mappa.put(prenotazione.getIdprenotazione(),prenotazione);
                System.out.println(prenotazione);
                System.out.println();
            }
        }
    }

    @Override
    public void showOptions() {
    }

    @Override
    public void submit() {
        if(userSpecs.getInstance().getUsertype().equals("USER")){
            MainDispatcher.getInstance().callView("user.HomeUser",null);
        }
        if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE") || userSpecs.getInstance().getUsertype().equals("ADMIN")) {
            Request request = new Request();
            request.setController("Prenotazioni");
            request.setMethod("prenotazioni.PrenotazioniCRUD");
            request.getBody().put("prenotazioniDTO", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }
}
