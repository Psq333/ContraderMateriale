package it.contrader.view.attrezzature;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.dto.AttrezzatureDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaAttrezzatureAdminView extends AbstractView {

   private String scelta;
   private Map<AttrezzatureDTO, List<AttrezzatureDTO>> attr;
    @Override
    public void showResults(Response response) {
        if(response != null){
            attr = new HashMap<AttrezzatureDTO, List<AttrezzatureDTO>>();
            List<AttrezzatureDTO> attrezzature = (List<AttrezzatureDTO>) response.getBody().get("attrezzature");
            System.out.println("-------------Ecco le attrezzature presenti nel sistema-------------");
            System.out.println("...................................................................");
            for (AttrezzatureDTO a : attrezzature){
                attr.put(attrezzature.get(a.getIdAttrezzature()),attrezzature);
                System.out.println(attrezzature);

            }
        }
    }

    @Override
    public void showOptions() {

    }

    @Override
    public void submit() {
        Request request = new Request();
        request.setController("Attrezzature");
        request.setMethod("Attrezzature.AttrezzatureAdmin");
        request.getBody().put("attrezzatureDTO",null);
        MainDispatcher.getInstance().callAction(request);
    }
}
