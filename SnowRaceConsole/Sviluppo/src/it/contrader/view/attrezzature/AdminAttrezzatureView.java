package it.contrader.view.attrezzature;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.AttrezzatureDTO;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.enums.PackageEnum;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;


import java.util.List;


public class AdminAttrezzatureView extends AbstractView {

    private String choice;

    @Override
    public void showResults(Response response) {
         if (response != null) {
             System.out.println("-------------ATTREZZATURE------------\n");
             List<AttrezzatureDTO> attrezzature = (List<AttrezzatureDTO>) response.getBody().get("attrezzature");
             for(AttrezzatureDTO attrezzatura : attrezzature){
                System.out.println(attrezzatura);
             }
        }
    }
    @Override
    public void showOptions () {
        System.out.println(" Seleziona cosa vuoi fare:");
        System.out.println("[I]nserire [V]isualizzare [C]ancellare [M]odificare");
        // The method saves the user's input into the 'choice' variable.
        // getInput() is defined in AbstractView.
        choice = this.getInput();
    }

    @Override
    public void submit () {
        switch (choice.toUpperCase()) {
            case "I":
                MainDispatcher.getInstance().callView("attrezzature.attrezzatureInsert", null);
                break;
            case "V":
                MainDispatcher.getInstance().callView("attrezzature.attrezzatureRead", null);
                break;
            case "C":
                MainDispatcher.getInstance().callView("attrezzature.attrezzatureDelete", null);
                break;
            case "M":
                MainDispatcher.getInstance().callView("attrezzature.attrezzatureUpdate", null);

            default:
                System.out.println("\nNessuna scelta valida");
                Request request = new Request("Attrezzature", "getAll", null);
                MainDispatcher.getInstance().callAction(request);
                break;
        }
    }

    }



















