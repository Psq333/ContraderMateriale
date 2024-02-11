package it.contrader.view.attrezzature;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.AttrezzatureDTO;
import it.contrader.dto.AttrezzatureDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.utilities.*;

public class attrezzatureInsertView extends AbstractView {


    private int idAttrezzatura,idimpianto;
    private double prezzo;
    private String descrizione,nome;


    public attrezzatureInsertView(){}


    @Override
    public void showResults(Response response) {
        if (response!=null) {
            System.out.println("Inserimento andato a buon fine.\n");
            Request request = new Request("Attrezzature", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    @Override
    public void showOptions() {
            System.out.println("Inserisci prezzo per l'attrezzatura:");
            scannerUtils.getInstance().askDouble();
            prezzo = scannerUtils.getInstance().getDouble();
            System.out.println("Inserisci nome attrezzatura:");
            nome = getInput();
            System.out.println("Inserire descrizione dell'attrezzatura:");
            descrizione = getInput();
            System.out.println("Inserire id impianto");
            scannerUtils.getInstance().askInt();
            idimpianto = scannerUtils.getInstance().getNumIn();
        }

    @Override
    public void submit() {
        Request request = new Request();
        AttrezzatureDTO attrezzatureToInsert = new AttrezzatureDTO(prezzo,descrizione,nome,idimpianto);
        request.getBody().put("attrezzatureToInsert", attrezzatureToInsert);
        request.setMethod("insert");
        request.setController("Attrezzature");
        MainDispatcher.getInstance().callAction(request);
    }

        //creare dto attrezzature e controllare bene se c'è qualche errore



}



