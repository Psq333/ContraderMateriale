package it.contrader.view.piste;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PisteDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;
import it.contrader.utilities.scannerUtils;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import it.contrader.service.ImpiantoService;

public class PisteInsertView extends AbstractView {

    private ImpiantoService impiantoService;
    private boolean impiantoAmministratore = false;
    private int idimpianto;
    private int idadmin;
    private String difficolta;
    private String username;
    private double prezzo;
    private int prenMax;


    public PisteInsertView(){
        impiantoService = new ImpiantoService();
    }
    @Override
    public void showResults(Response response) {
        if(response != null){
            System.out.println("Inserimento andato a buon fine.\n");
            Request request = new Request("Piste", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    @Override
    public void showOptions() {
        System.out.println("----------------------------------");
        System.out.println("Inserisci la difficoltà della pista:");
        difficolta = getInput();
        System.out.println("Inserisci il prezzo della pista");
        scannerUtils.getInstance().askDouble();
        prezzo = scannerUtils.getInstance().getDouble();
        System.out.println("inserisci il numero massimo di prenotazioni della pista:  ");
        scannerUtils.getInstance().askInt();
        prenMax = scannerUtils.getInstance().getNumIn();
        System.out.println("Inserisci id impianto:");
        scannerUtils.getInstance().askInt();
        idimpianto = scannerUtils.getInstance().getNumIn();

    }

    @Override
    public void submit() {
            Request request = new Request();
            PisteDTO pistadainserire = new PisteDTO(idimpianto, difficolta, prezzo, prenMax);
            request.setController("Piste");
            request.setMethod("insert");
            request.getBody().put("pisteToInsert", pistadainserire);
            MainDispatcher.getInstance().callAction(request);
    }
}
