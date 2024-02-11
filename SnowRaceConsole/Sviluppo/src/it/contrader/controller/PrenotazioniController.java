package it.contrader.controller;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.PrenotazioniService;
import it.contrader.service.UserService;
import it.contrader.utilities.userSpecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrenotazioniController implements Controller{
    private static String sub_package = "prenotazioni.";

    private final PrenotazioniService prenotazioniService;

    public PrenotazioniController() {
        this.prenotazioniService = new PrenotazioniService();
    }

    @Override
    public void doControl(Request request) {
        Response response = new Response();

        switch (request.getMethod()) {
            case "getAll":
                List<PrenotazioniDTO> prenotazionesDTO = prenotazioniService.getAll();
                List<PrenotazioniDTO> prenotazioniUserDTO = new ArrayList<>();
                if(userSpecs.getInstance().getUsertype().equals("USER")) {
                    for (PrenotazioniDTO pre : prenotazionesDTO) {
                        if (pre.getUsername().equals(userSpecs.getInstance().getUsername())) {
                            prenotazioniUserDTO.add(pre);
                        }
                    }
                    response.put("prenotazioni", prenotazioniUserDTO);
                    MainDispatcher.getInstance().callView("MenuPrenotazioniAdmin", response);
                }
                response.put("prenotazioni", prenotazionesDTO);
                MainDispatcher.getInstance().callView("MenuPrenotazioniAdmin", response);
                break;
            case "read":
                PrenotazioniDTO prenotazioniDTO = prenotazioniService.read(Integer.parseInt(request.getBody().get("id").toString()));
                response.put("prenotazioni", prenotazioniDTO);
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniRead", response);
                break;
            case "insert":
                PrenotazioniDTO prenotazionitoinsert = (PrenotazioniDTO) request.getBody().get("prenotazioniToInsert");
                prenotazioniService.insert(prenotazionitoinsert);
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniInsert", response);
                break;
            case "delete":
                prenotazioniService.delete(Integer.parseInt(request.getBody().get("id").toString()));
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniDelete", response);
                break;
            case "update":
                PrenotazioniDTO prenotazioniToUpdate = (PrenotazioniDTO) request.getBody().get("prenotazioniToUpdate");
                prenotazioniService.update(prenotazioniToUpdate);
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniUpdate", response);
                break;
            case "prenotazioni.PrenotazioniCRUD":
                PrenotazioniDTO prenotazione = (PrenotazioniDTO)request.getBody().get("prenotazioniDTO");
                response.put("prenotazioniDTO", prenotazione);
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniCRUD", response);
                break;
            case "getStorico":
                List<PrenotazioniDTO> prenotazionistorico = prenotazioniService.getStorico(userSpecs.getInstance().getUsername());
                response.put("prenotazionistorico", prenotazionistorico);
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniList", response);
                break;
            case "search":
                Map<String, String> quest = new HashMap<>();
                quest.put("key", (String)request.getBody().get("key"));
                quest.put("what", (String)request.getBody().get("what"));
                quest.put("who", (String)request.getBody().get("who"));
                List<PrenotazioniDTO> searchedPrenotazioni = prenotazioniService.search(quest);
                response.put("prenotazioni", searchedPrenotazioni);
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniSearch", response);
                break;
            default:
                System.out.println("\n 404 pagina non trovata");
                MainDispatcher.getInstance().callView("prenotazioni.PrenotazioniCRUD", null);
        }
    }

}
