package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.scannerUtils;
import it.contrader.utilities.userSpecs;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class MenuPrenotazioniUserView extends  AbstractView{

    private int impianto;
    private int pista;
    private String username;
    private Date data_inizio, data_fine;
    public MenuPrenotazioniUserView(){

    }

    //creare una classe MenuPrenotazioniAdminView uguale a ListImpiantoView (Pasquale) per gestire cosa può fare l'admin -- puo solo visualizzare e basta, da lui questo lo fa lo user.

    public void showResults(Response response) {
        System.out.println("---EFFETTUA LA TUA PRENOTAZIONE---");
    };



    public void showOptions(){
        //qui richiamo il controller di Impianti, body null perchè non ne scelgo uno speicifo ma li mostro tutti
        MainDispatcher.getInstance().callAction(new Request("Impianto", "getAll", null) );
        System.out.println("          Quale impianto vuoi scegliere?");
        scannerUtils.getInstance().askInt();
        impianto = scannerUtils.getInstance().getNumIn();

        Map <String, Object> impiantoMap = new HashMap<>();
        impiantoMap.put("idImpianto", impianto ); //creo una mappa dove mi salvo l'id impianto che ricevo in input
        //richiamo le piste del relativo impianto scelto
        MainDispatcher.getInstance().callAction(new Request("Piste","pisteImpianto", impiantoMap ));
        System.out.println("          Quale pista vuoi scegliere?");
        scannerUtils.getInstance().askInt();
        pista= scannerUtils.getInstance().getNumIn();


        System.out.println("          Inserisci data di inizio prenotazione (dd-MM-yyyy)");
        scannerUtils.getInstance().askDate();
        data_inizio = scannerUtils.getInstance().getDateIn();
        System.out.println("          Inserisci data di fine prenotazione (dd-MM-yyyy)");
        scannerUtils.getInstance().askDate();
        data_fine = scannerUtils.getInstance().getDateIn();

    }

   public void submit(){
        Request request = new Request();
        request.setController("Prenotazioni");
        request.setMethod("insert");
        username = userSpecs.getInstance().getUsername();
        PrenotazioniDTO prenotazioni = new PrenotazioniDTO(pista, username, data_inizio, data_fine);
        request.getBody().put("prenotazioniToInsert", prenotazioni); //inserisco la prenotazione.
        MainDispatcher.getInstance().callAction(request);

    };
}
