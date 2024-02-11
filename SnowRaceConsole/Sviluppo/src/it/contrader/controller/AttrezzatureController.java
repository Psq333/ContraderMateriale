package it.contrader.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import it.contrader.dto.AttrezzatureDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.AttrezzatureService;
import org.w3c.dom.Attr;

public class AttrezzatureController implements Controller{


    private static String sub_package = "attrezzature.";

    private final AttrezzatureService attrezzatureService;

    private AttrezzatureDTO attrezzatureDTO;

    public AttrezzatureController(){this.attrezzatureService = new AttrezzatureService();}

    @Override
    public void doControl(Request request) {

        Response response = new Response();

        switch (request.getMethod()) {
            case "getAll":
                List<AttrezzatureDTO> attrezzatureDTOList = attrezzatureService.getAll();
                response.put("attrezzature", attrezzatureDTOList);
                MainDispatcher.getInstance().callView("attrezzature.AdminAttrezzature", response);
                break;
            case "read":
                int idAttrezzature = (int) request.getBody().get("idAttrezzature");
                AttrezzatureDTO attrezzatureDTO = attrezzatureService.read(idAttrezzature);
                response.put("attrezzature", attrezzatureDTO);
                MainDispatcher.getInstance().callView("attrezzature.attrezzatureRead", response);
                break;
            case "insert":
                AttrezzatureDTO attrezzaturetoinsert = (AttrezzatureDTO) request.getBody().get("attrezzatureToInsert");
                attrezzatureService.insert(attrezzaturetoinsert);
                MainDispatcher.getInstance().callView("attrezzature.attrezzatureInsert", response);
                break;
            case "delete":
                attrezzatureService.delete(Integer.parseInt(request.getBody().get("idAttrezzature").toString()));
                MainDispatcher.getInstance().callView("attrezzature.attrezzatureDelete", response);
                break;
            case "update":
                AttrezzatureDTO attrezzatureToUpdate = (AttrezzatureDTO) request.getBody().get("attrezzatureToUpdate");
                attrezzatureService.update(attrezzatureToUpdate);
                MainDispatcher.getInstance().callView("attrezzature.attrezzatureUpdate", response);
                break;
            case "vistaUser":
                attrezzatureDTO = attrezzatureService.vistaUser(Integer.parseInt(request.getBody().get("idAttrezzatura").toString()));
                response.put("attrezzature", attrezzatureDTO);
                MainDispatcher.getInstance().callView("attrezzature.attrezzatureRead", response);
            default:
                System.out.println("\n 404 pagina non trovata");
                MainDispatcher.getInstance().callView("attrezzature.attrezzature", null);
        }
    }




    }


