package it.contrader.view.Impianto;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;

public class ImpiantoReadView extends AbstractView {

    private int idImpianto;

    public ImpiantoReadView(){
    }
    /*
        Se response = null (la chiamata arrica dal controller come GETCHOICE). Esegue showOption e submit
        Se response != null ( la chiamata arrica dal controller come READ) mostra lo user e torna alla View, senza fare showOption e submit
     */
    @Override
    public void showResults(Response response) {
        //Per capire vai in ImpiantiInsertView
        if(response != null){
            if((userSpecs.getInstance().getUsertype().equals("ADMIN") && (boolean)response.getBody().get("boolean"))) {
                ImpiantoDTO impianto = (ImpiantoDTO) response.getBody().get("impianto");
                System.out.println(impianto);
            } else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                ImpiantoDTO impianto = (ImpiantoDTO) response.getBody().get("impianto");
                System.out.println(impianto);
            }
            Request request = new Request("Impianto", "getAll",null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("Inserimento l'ID dell'impianto");
        idImpianto = Integer.parseInt(getInput());
    }

    @Override
    public void submit() {
        Request request = new Request();
        request.setController("Impianto");
        request.setMethod("read");
        request.getBody().put("idImpianto",idImpianto);
        MainDispatcher.getInstance().callAction(request);
    }
}
