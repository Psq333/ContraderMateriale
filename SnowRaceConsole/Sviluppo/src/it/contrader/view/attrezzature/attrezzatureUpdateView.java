package it.contrader.view.attrezzature;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.AttrezzatureDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.scannerUtils;
import it.contrader.view.AbstractView;

public class attrezzatureUpdateView extends AbstractView {


    private int idAttrezzature,idimpianto;

    private double prezzo;

    private String descrizione,nome;


    public attrezzatureUpdateView(){

}


    @Override
    public void showResults(Response response) {
        if (response!=null) {
            System.out.println("Modifica andata a buon fine.\n");
            Request request = new Request("Attrezzature", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }

    @Override
    public void showOptions() {
        try {
            System.out.println("Inserisci id dell'attrezzatura da modificare:");
            idAttrezzature = Integer.parseInt(getInput());
            System.out.println("Inserisci il nuovo prezzo:");
            scannerUtils.getInstance().askDouble();
            prezzo = scannerUtils.getInstance().getDouble();
            System.out.println("Inserisci la nuova descrizione:");
            descrizione = getInput();
            System.out.println("Inserisci il nome dell'attrezzatura:");
            nome = getInput();
            System.out.println("Inserisci id impianto");
            idimpianto = Integer.parseInt(getInput());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void submit() {
        Request request = new Request();
        AttrezzatureDTO AttrezzatureToUpdate = new AttrezzatureDTO(idAttrezzature, prezzo, descrizione, nome,idimpianto);
        request.getBody().put("attrezzatureToUpdate", AttrezzatureToUpdate);
        request.setMethod("update");
        request.setController("Attrezzature");

        MainDispatcher.getInstance().callAction(request);
    }
}







