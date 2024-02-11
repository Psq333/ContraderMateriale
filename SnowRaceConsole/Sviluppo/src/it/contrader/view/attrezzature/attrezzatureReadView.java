package it.contrader.view.attrezzature;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.AttrezzatureDTO;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;

public class attrezzatureReadView extends AbstractView {

    private int idAttrezzature;

    public attrezzatureReadView(){

    }


    @Override
    public void showResults(Response response) {
        if(response != null){
            if((userSpecs.getInstance().getUsertype().equals("ADMIN") && (boolean)response.getBody().get("boolean"))) {
                AttrezzatureDTO attrezzature = (AttrezzatureDTO) response.getBody().get("Attrezzature");
                System.out.println(attrezzature);
            } else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                AttrezzatureDTO attrezzature = (AttrezzatureDTO) response.getBody().get("attrezzature");
                System.out.println(attrezzature);
            }
            Request request = new Request("Attrezzature", "getAll",null);
            MainDispatcher.getInstance().callAction(request);
        }
    }


    @Override
    public void showOptions() {
        System.out.println("Inserisci l'ID dell'attrezzatura:");
        idAttrezzature = Integer.parseInt(getInput());
    }

    @Override
    public void submit() {
        Request request = new Request();
        request.setController("Attrezzature");
        request.setMethod("read");
        request.getBody().put("idAttrezzature", idAttrezzature);
        MainDispatcher.getInstance().callAction(request);
    }
}
