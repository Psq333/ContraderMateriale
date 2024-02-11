package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.scannerUtils;
import it.contrader.utilities.userSpecs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MenuNoleggiUser extends AbstractView{

    private int impianto;
    private int attrezzatura;
    private String username;
    private Date data_inizio, data_fine;
    public void MenuNoleggiUser(){

    }

    //creare una classe MenuPrenotazioniAdminView uguale a ListImpiantoView (Pasquale) per gestire cosa può fare l'admin -- puo solo visualizzare e basta, da lui questo lo fa lo user.

    public void showResults(Response response) {
        System.out.println("---EFFETTUA IL TUO NOLEGGIO---");
    };



    public void showOptions(){
        //qui richiamo il controller di Impianti, body null perchè non ne scelgo uno speicifo ma li mostro tutti
        MainDispatcher.getInstance().callAction(new Request("Impianto", "getAll", null) );
        System.out.println("          Quale impianto vuoi scegliere?");
        scannerUtils.getInstance().askInt();
        impianto = scannerUtils.getInstance().getNumIn();

        Map<String, Object> impiantoMap = new HashMap<>();
        impiantoMap.put("idImpianto", impianto ); //creo una mappa dove mi salvo l'id impianto che ricevo in input
        //richiamo l'attrezzatura del relativo impianto scelto
        MainDispatcher.getInstance().callAction(new Request("Attrezzatura","AttrezzaturaImpianto", impiantoMap ));
        System.out.println("          Quale Attrezzatura vuoi scegliere?");
        scannerUtils.getInstance().askInt();
        attrezzatura= scannerUtils.getInstance().getNumIn();


        System.out.println("          Inserisci data di inizio noleggio (dd-MM-yyyy)");
        scannerUtils.getInstance().askDate();
        data_inizio = scannerUtils.getInstance().getDateIn();
        System.out.println("          Inserisci data di fine noleggio (dd-MM-yyyy)");
        scannerUtils.getInstance().askDate();
        data_fine = scannerUtils.getInstance().getDateIn();

    }

    public void submit(){
        /*Request request = new Request();
        request.setController("Attrezzature");
        request.setMethod("insert");
        username = userSpecs.getInstance().getUsername();
        //PrenotazioniDTO prenotazioni = new PrenotazioniDTO(attrezzatura, username, data_inizio, data_fine);
        request.getBody().put("attrezzatureToInsert", prenotazioni); //inserisco l'attrezzatura.
        MainDispatcher.getInstance().callAction(request);*/

    };
}

