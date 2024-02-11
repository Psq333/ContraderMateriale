package it.contrader.view.noleggio;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.NoleggioDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.utilities.scannerUtils;

import java.util.Date;


public class NoleggioInsertView extends AbstractView {

    private Date data_inizio;
    private Date data_fine;

    private String username;

    private int idnoleggio;
    private int idattrezzatura;

    public NoleggioInsertView(){

    }

    @Override
    public void showResults(Response response) {
        if (response!=null) {
            System.out.println("Inserimento andato a buon fine.\n");
            Request request = new Request("noleggio", "getAll", null);
            MainDispatcher.getInstance().callAction(request);
        }

    }

    @Override
    public void showOptions() {
        System.out.println("Inserisci username dell'utente:");
        username = getInput();
        System.out.println("inserisci data di inizio del noleggio:");
        scannerUtils.getInstance().askDate();
        data_inizio = scannerUtils.getInstance().getDateIn();
        System.out.println("Inserisci data di fine del noleggio:");
        scannerUtils.getInstance().askDate();
        data_fine = scannerUtils.getInstance().getDateIn();


    }

    @Override
    public void submit() {
        Request request = new Request();
        NoleggioDTO noleggioToInsert = new NoleggioDTO(idnoleggio, username, data_inizio, data_fine, idattrezzatura);
        request.getBody().put("noleggioToInsert", noleggioToInsert);
        request.setMethod("insert");
        request.setController("noleggio");
        MainDispatcher.getInstance().callAction(request);


    }
}
