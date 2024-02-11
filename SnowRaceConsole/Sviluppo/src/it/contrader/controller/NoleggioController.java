package it.contrader.controller;


import it.contrader.converter.NoleggioConverter;
import it.contrader.dto.NoleggioDTO;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.NoleggioService;
import it.contrader.service.UserService;

import java.util.List;

public class NoleggioController implements Controller{
    private static String sub_package = "noleggio.";

    private final NoleggioService noleggioService;


    public NoleggioController() {
        this.noleggioService = new NoleggioService();
    }


    @Override
    public void doControl(Request request) {
        Response response = new Response();

        switch (request.getMethod()) {
            case "getAll":
                //List<NoleggioDTO> NoleggioDTO = NoleggioService.getAll();
                //response.put("noleggio", NoleggioDTO);
                MainDispatcher.getInstance().callView("MenuNoleggiAdmin", response);
                break;
            case "read":
                NoleggioDTO noleggioDTO = noleggioService.read(Integer.parseInt(request.getBody().get("idnoleggio").toString()));
                response.put("noleggi", noleggioDTO);
                MainDispatcher.getInstance().callView("noleggio.NoleggioRead", response);
                break;
            case "insert":
                NoleggioDTO noleggioToInsert = (NoleggioDTO) request.getBody().get("noleggioToInsert");
                noleggioService.insert(noleggioToInsert);
                MainDispatcher.getInstance().callView("noleggio.NoleggioInsert", response);
                break;
            case "delete":
                noleggioService.delete(Integer.parseInt(request.getBody().get("idnoleggio").toString()));
                MainDispatcher.getInstance().callView("noleggio.NoleggioDelete", response);
                break;
            case "update":
                NoleggioDTO noleggioToUpdate = (NoleggioDTO) request.getBody().get("noleggioToUpdate");
                noleggioService.update(noleggioToUpdate);
                MainDispatcher.getInstance().callView("noleggio.NoleggioUpdate", response);
                break;
            case "Noleggio.NoleggiCRUDView":
                NoleggioDTO noleggi = (NoleggioDTO) request.getBody().get("noleggioDTO");
                response.put("noleggioDTO", noleggi);
                MainDispatcher.getInstance().callView("noleggi.NoleggiCRUDView", response);
                break;
            default:
                System.out.println("\n 404 pagina non trovata");
                MainDispatcher.getInstance().callView("HomeAdmin", null);
        }
    }

    }
