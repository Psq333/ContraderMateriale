package it.contrader.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.contrader.dto.AnagraficaDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.AnagraficaService;
import it.contrader.utilities.userSpecs;

public class AnagraficaController implements Controller {

    private static String sub_package = "anagrafica.";

    private final AnagraficaService anagraficaService;
    private AnagraficaDTO anagraficaDTO = new AnagraficaDTO();

    public AnagraficaController() {
        this.anagraficaService = new AnagraficaService();
    }


    @Override
    public void doControl(Request request) {
        Response response = new Response();

        switch (request.getMethod()) {
            case "getAll":
                List<AnagraficaDTO> anagraficaDTOList = anagraficaService.getAll();
                System.out.println(anagraficaDTOList);
                response.put("anagrafica", anagraficaDTOList);
                MainDispatcher.getInstance().callView("anagrafica.AnagraficaCRUD", response);
                break;
            case "read":
                anagraficaDTO = anagraficaService.read(request.getBody().get("username").toString());
                response.put("anagrafica", anagraficaDTO);
                MainDispatcher.getInstance().callView(sub_package + "AnagraficaRead", response);
                break;
            case "insert":
                AnagraficaDTO anagraficatoinsert = (AnagraficaDTO) request.getBody().get("anagraficaToInsert");
                anagraficaDTO = anagraficaService.read(request.getBody().get("username").toString());
                anagraficaService.insert(anagraficatoinsert, anagraficaDTO.getUsername());
                response.getBody().put("anagrafica","done");
                MainDispatcher.getInstance().callView(sub_package + "AnagraficaInsert", response);
                break;
            case "delete":
                anagraficaDTO = anagraficaService.read(request.getBody().get("username").toString());
                anagraficaService.delete(request.getBody().get("username").toString());
                MainDispatcher.getInstance().callView(sub_package + "AnagraficaDelete", response);
                break;
            case "update":
                AnagraficaDTO anagraficaToUpdate = (AnagraficaDTO) request.getBody().get("anagraficaToUpdate");
                boolean a = anagraficaService.update(anagraficaToUpdate, anagraficaToUpdate.getUsername());
                if (a)
                    MainDispatcher.getInstance().callView(sub_package + "AnagraficaUpdate", response);
                else {
                    if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE") ){
                        System.out.println("Fallimento update anagrafica");
                        MainDispatcher.getInstance().callView("HomeSuperAdmin", null);//todo tbL
                    } else {
                        response.put("signin", "yes");
                        MainDispatcher.getInstance().callView("user.UserAccount", response);
                    }
                }
                break;
            default:
                System.out.println("\n 404 pagina non trovata");
                MainDispatcher.getInstance().callView("user.HomeAdmin", null);
        }
    }
}
