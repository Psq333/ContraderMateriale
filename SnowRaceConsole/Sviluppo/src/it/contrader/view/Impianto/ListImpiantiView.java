package it.contrader.view.Impianto;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.contrader.utilities.userSpecs;

import it.contrader.service.ImpiantoService;

public class ListImpiantiView extends AbstractView {

    private String choice;
    private Map<Integer, ImpiantoDTO> mappa;


    @Override
    public void showResults(Response response) {
        if(response != null){
            mappa = new HashMap<Integer,ImpiantoDTO>();
            List<ImpiantoDTO> impianti = (List<ImpiantoDTO>) response.getBody().get("impianti");
            System.out.println("-------------GLI IMPANTI-------------");
            System.out.println("idImpianti\tnome\tdescrizione\tluogo\tamministratore='");
            if (impianti!=null)
                for (ImpiantoDTO impianto : impianti){
                    mappa.put(impianto.getIdImpianto(),impianto);
                    System.out.println(impianto);
                    System.out.println();
                }
            if (response.getBody().containsKey("noLog")) {
                ImpiantoService impiantoService = new ImpiantoService();
                for (ImpiantoDTO impianto : impiantoService.getAll()){
                    mappa.put(impianto.getIdImpianto(),impianto);
                    System.out.println(impianto);
                    System.out.println();
                }
                MainDispatcher.getInstance().callView("Home", null);
            }
        }
    }

    @Override
    public void showOptions() {
    }

    @Override
    public void submit() {
        if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE") || userSpecs.getInstance().getUsertype().equals("ADMIN")) {
            Request request = new Request();
            request.setController("Impianto");
            request.setMethod("Impianto.ImpiantoCRUD");
            request.getBody().put("impiantoDTO", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }
}
