package it.contrader.view.attrezzature;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dao.AttrezzatureDAO;
import it.contrader.dto.AttrezzatureDTO;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.enums.PackageEnum;
import it.contrader.main.MainDispatcher;
import it.contrader.service.AttrezzatureService;
import it.contrader.view.AbstractView;
import it.contrader.controller.AttrezzatureController;
import java.util.List;

public class UserAttrezzatureView extends AbstractView{

    private final AttrezzatureService attrezzatureService = new AttrezzatureService();
    private String scelta;

    @Override
    public void showResults(Response response) {      //mostra la lista attrezzatura
        if (response != null) {
            List<AttrezzatureDTO> attrezzature = (List<AttrezzatureDTO>) response.getBody().get("idAttrezzature");
            System.out.println("\n-------------------Dettagli Attrezzatura----------------\n");
            for (AttrezzatureDTO a : attrezzature)
                System.out.println(a);
        }
    }

    @Override
    public void showOptions() {
        System.out.println(" Inserisci id Impianto per visualizzare l'attrezzatura correlata  ");
        this.scelta = getInput();
    }

    @Override
    public void submit() {

        Request request = new Request();
        request.setController("Attrezzature");
        request.setMethod("AttrezzatureImpianto");
        request.getBody().put("idAttrezzature", this.scelta);
        MainDispatcher.getInstance().callAction(request);
    }
}
