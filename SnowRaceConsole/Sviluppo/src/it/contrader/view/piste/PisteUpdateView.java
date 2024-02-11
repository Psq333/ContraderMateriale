package it.contrader.view.piste;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PisteDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;
import it.contrader.utilities.scannerUtils;
import it.contrader.service.ImpiantoService;
import java.util.List;

public class PisteUpdateView extends AbstractView {


    private ImpiantoService impiantoService = new ImpiantoService();
    private boolean impiantoAmministratore = false;
    private int idpista;
    private int idimpianto;
    private String difficolta;
    private double prezzo;
    private int prenMax;

    @Override
    public void showResults(Response response) {
        if(response != null){
            if((userSpecs.getInstance().getUsertype().equals("ADMIN") && (boolean)response.getBody().get("boolean"))) {
                System.out.println("Modifica dell'impianto andata a buon fine.\n");
            } else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                System.out.println("Modifica dell'impianto andata a buon fine.\n");
            }
            Request request = new Request();
            request.setController("Piste");
            request.setMethod("getAll");
            request.setBody(null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("Inserisci l'id della pista da modificare");
        scannerUtils.getInstance().askInt();
        idpista = scannerUtils.getInstance().getNumIn();
        System.out.println("Inserisci la nuova difficoltà della pista");
        difficolta = getInput();
        System.out.println("Inserisci il nuovo prezzo della pista");
        scannerUtils.getInstance().askDouble();
        prezzo = scannerUtils.getInstance().getDouble();
        System.out.println("Inserisci il nuovo numero di prenotazioni massime della pista");
        scannerUtils.getInstance().askInt();
        prenMax = scannerUtils.getInstance().getNumIn();
        System.out.println("Inserisci id impianto:");
        scannerUtils.getInstance().askInt();
        idimpianto = scannerUtils.getInstance().getNumIn();

    }


    @Override
    public void submit() {

            Request request = new Request();
            PisteDTO pisteDaModificare = new PisteDTO(idpista,idimpianto,difficolta,prezzo,prenMax);
            request.setController("Piste");
            request.setMethod("update");
            request.getBody().put("pisteToUpdate",pisteDaModificare);
            MainDispatcher.getInstance().callAction(request);
    }
}
