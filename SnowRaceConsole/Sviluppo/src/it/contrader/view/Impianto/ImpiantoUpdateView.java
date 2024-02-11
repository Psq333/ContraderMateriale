package it.contrader.view.Impianto;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Impianto;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;

public class ImpiantoUpdateView extends AbstractView {
    private int idImpianto;
    private String nome;
    private String descrizione;
    private String luogo;
    private String amministratore;

    public ImpiantoUpdateView(){

    }

    @Override
    public void showResults(Response response) {
        if(response != null){
            if((userSpecs.getInstance().getUsertype().equals("ADMIN") && (boolean)response.getBody().get("boolean"))) {
                System.out.println("Modifica dell'impianto andata a buon fine.\n");
            } else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                System.out.println("Modifica dell'impianto andata a buon fine.\n");
            }
            Request request = new Request();
            request.setController("Impianto");
            request.setMethod("getAll");
            request.setBody(null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("Inserisci l'id dell'impianto da modificare");
        idImpianto = Integer.parseInt(getInput());;
        System.out.println("Inserisci il nuovo nome dell'impianto");
        nome = getInput();
        System.out.println("Inserisci la nuova descrizione dell'impianto");
        descrizione = getInput();
        System.out.println("Inserisci il nuovo luogo dell'impianto");
        luogo = getInput();
        if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
            System.out.println("Inserisci il nuovo amministratore dell'impianto:");
            amministratore = getInput();
        }
        else if(userSpecs.getInstance().getUsertype().equals("ADMIN")){
            amministratore = userSpecs.getInstance().getUsername();
        }
    }

    @Override
    public void submit() {
        Request request = new Request();
        ImpiantoDTO impiantoDaModificare = new ImpiantoDTO(idImpianto,nome,descrizione,luogo,amministratore);
        request.setController("Impianto");
        request.setMethod("update");
        request.getBody().put("impiantoDaModificare",impiantoDaModificare);
        MainDispatcher.getInstance().callAction(request);
    }
}
