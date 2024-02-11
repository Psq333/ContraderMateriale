package it.contrader.view.noleggio;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.NoleggioDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.utilities.scannerUtils;

import java.util.Date;
import java.util.Scanner;

public class NoleggioUpdateView extends AbstractView {

    private int idnoleggio;
    private int idattrezzatura;

    private String username;

    private Date data_inizio;
    private Date data_fine;

    public NoleggioUpdateView(){
    }
    @Override
    public void showResults(Response response) {
        if (response != null) {
            System.out.println("Modifica andata a buon fine.\n");
            Request request = new Request("Noleggio", "getAll", null);
            MainDispatcher.getInstance().callAction(request);

        }
    }

    @Override
    public void showOptions() {
            try {
                System.out.println("Inserisci id del noleggio da modificare:");
                idnoleggio = Integer.parseInt(getInput());
                System.out.println("Inserisci il nuovo id dell'attrezzatura:");
                idattrezzatura = Integer.parseInt(getInput());
                System.out.println("Inserisci il tuo nuovo username  :");
                username = getInput();
                System.out.println("Inserisci la data di inizio :");
                scannerUtils.getInstance().askDate();
                data_inizio = scannerUtils.getInstance().getDateIn();
                System.out.println("Inserisci la data di fine :");
                scannerUtils.getInstance().askDate();
                data_fine = scannerUtils.getInstance().getDateIn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }




    @Override
    public void submit() {
        Request request = new Request();
        NoleggioDTO noleggiToUpdate = new NoleggioDTO(idnoleggio, idattrezzatura, username, data_inizio, data_fine);
        request.getBody().put("noleggiToUpdate", noleggiToUpdate);
        request.setMethod("update");
        request.setController("noleggi");
        MainDispatcher.getInstance().callAction(request);

    }
}
