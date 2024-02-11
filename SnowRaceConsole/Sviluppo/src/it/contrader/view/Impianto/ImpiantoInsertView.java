package it.contrader.view.Impianto;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

import it.contrader.utilities.userSpecs;

public class ImpiantoInsertView extends AbstractView {
    private String nome;
    private String descrizione;
    private String luogo;
    private String amministratore;

    public ImpiantoInsertView(){

    }
    @Override
    public void showResults(Response response) {
        //Se è response = null vuol dire che non ho modificiato niente, ma voglio modificare uno user che prenderò da console
        //quindi farò showOptions e submit
        if(response != null){
            //Se la response è diversa da null, allora vuol dire che ho inserito l'impianto
            //quindi visualizza tutti gli impianti e non fa showOptions e submit
            System.out.println("Inserimento andato a buon fine.\n");
            Request request = new Request("Impianto", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("----------------------------------");
        System.out.println("Inserisci nome impianto:");
        nome = getInput();
        System.out.println("Inserisci descrizione impianto:");
        descrizione = getInput();
        System.out.println("Inserisci luogo impianto:");
        luogo = getInput();
        if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
            System.out.println("Inserisci amministratore impianto:");
            amministratore = getInput();
        }
        else if(userSpecs.getInstance().getUsertype().equals("ADMIN")){
            amministratore = userSpecs.getInstance().getUsername();
        }

    }

    @Override
    public void submit() {
        Request request = new Request();
        ImpiantoDTO impiantoDaInserire = new ImpiantoDTO(nome,descrizione,luogo,amministratore);
        request.setController("Impianto");
        request.setMethod("insert");
        request.getBody().put("impiantoDaInserire", impiantoDaInserire);
        MainDispatcher.getInstance().callAction(request);
    }
}
