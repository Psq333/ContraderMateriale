package it.contrader.view.anagrafica;

import it.contrader.controller.Response;
import it.contrader.controller.Request;
import it.contrader.dto.LoginDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.scannerUtils;

import java.util.Date;

import it.contrader.utilities.userSpecs;
import it.contrader.utilities.userTypeMap;
import it.contrader.dto.UserDTO;
import it.contrader.dto.AnagraficaDTO;
import it.contrader.view.AbstractView;

public class AnagraficaInsertView extends AbstractView{

    int id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String luogoNascita;
    private String username;
    private Date dataNascita = new Date();

    public AnagraficaInsertView(){}
    public void showResults(Response response) {
        if (response != null) {
            System.out.println("Inserimento anagrafica andato a buon fine.\n");
            switch (userSpecs.getInstance().getUsertype().toUpperCase()) {
                    case "AMMINISTRATORE":
                        MainDispatcher.getInstance().callView("superAdmin.MenuSuperAdmin", null);
                        break;
                    case "ADMIN":
                        MainDispatcher.getInstance().callView("user.HomeAdmin", null);
                        break;
                    case "USER":
                        MainDispatcher.getInstance().callView("user.HomeUser", null);
                        break;
            }

        }
    }

    public void showOptions() {
        if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE")) {
            System.out.println("Inserisci l'username dell'utente da aggiungere");
            username = getInput();
            if (username.equals("") || username == null){
                System.out.println("Username non inserito. Inserisci l'id dell'utente da aggiungere");
                scannerUtils.getInstance().askInt();
                id = scannerUtils.getInstance().getNumIn();
            }

        } else {
            username = userSpecs.getInstance().getUsername();
            id = userSpecs.getInstance().getId();
            System.out.println("----- .:Inserisci i tuoi dati:. ----\n");
        }


        System.out.println("Inserisci il nome:");
        this.nome = getInput();

        System.out.println("Inserisci il cognome:");
        this.cognome = getInput();

        System.out.println("Inserisci il indirizzo di residenza:");
        this.indirizzo = getInput();

        System.out.println("Inserisci la città di nascita:");
        this.luogoNascita = getInput();

        System.out.println("Inserisci la data di nascita:");
        scannerUtils.getInstance().askDate();
        this.dataNascita = scannerUtils.getInstance().getDateIn();

    }

    public void submit(){
        AnagraficaDTO anagraficaDTO = new AnagraficaDTO(this.nome, this.cognome, this.indirizzo, this.luogoNascita, this.username, this.dataNascita);
        Request request = new Request();
        //if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE"))
            request.getBody().put("username", username);
        //else
        request.getBody().put("anagraficaDTO", anagraficaDTO);
        request.setController("Home");
        request.setMethod("register");
        MainDispatcher.getInstance().callAction(request);
    }
}

