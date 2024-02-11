package it.contrader.view.anagrafica;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.AnagraficaDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;
import it.contrader.utilities.scannerUtils;
import java.util.Date;

public class AnagraficaUpdateView extends AbstractView {

    private int id = 0;
    private String nome;
    private String cognome;
    private String indirizzo;
    private Date data_nascita;
    private String luogo_nascita;
    private String username;
    public AnagraficaUpdateView(){}
    @Override
    public void showResults(Response response) {
        if (response!=null) {
            System.out.println("Modifica anagrafica andata a buon fine.\n");
            //Request request = new Request("User", "getAll", null);
            //MainDispatcher.getInstance().callAction(request);
            switch (userSpecs.getInstance().getUsertype().toUpperCase()){
                case "AMMINISTRATORE":
                    MainDispatcher.getInstance().callView("superAdmin.MenuSuperAdmin",null); //Todo nome view homesuperadmin
                    break;
                case "ADMIN":
                case "UTENTE":
                    MainDispatcher.getInstance().callView("user.UserAccount",null);
                    break;
            }
        }
    }

    @Override
    public void showOptions() {
        System.out.println("------MODIFICA ANAGRAFICA------\n(Premi Invio per saltare la modifica dell'elemento):\n");
        if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE")) {
            System.out.println("Inserisci l'username dell'user di cui si vogliono modificare i dati:");
            username = getInput();
            if (username.equals("")) {
                System.out.println("Username non selezionato. Inserisci l'id dell'user di cui si vogliono modificare i dati:");
                scannerUtils.getInstance().askInt();
                id = scannerUtils.getInstance().getNumIn();
            }
        } else
                username = userSpecs.getInstance().getUsername();
        System.out.println("Inserisci il nuovo nome:");
        nome = getInput();
        System.out.println("Inserisci il nuovo cognome:");
        cognome = getInput();
        System.out.println("Inserisci il nuovo indirizzo:");
        indirizzo = getInput();
        System.out.println("Inserisci la nuova data di nascita:");
        scannerUtils.getInstance().askDate();
        data_nascita = scannerUtils.getInstance().getDateIn();
        System.out.println("Inserisci il nuovo luogo di nascita:");
        luogo_nascita = getInput();
    }

    @Override
    public void submit(){
        Request request = new Request();
        AnagraficaDTO anagraficaToUpdate = new AnagraficaDTO(nome, cognome, indirizzo, luogo_nascita, username, data_nascita);
        request.getBody().put("anagraficaToUpdate", anagraficaToUpdate);
        request.setMethod("update");
        request.setController("Anagrafica");
        MainDispatcher.getInstance().callAction(request);
    }

}
