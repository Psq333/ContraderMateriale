package it.contrader.controller;

import it.contrader.main.MainDispatcher;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.contrader.utilities.userSpecs;

public class SuperAdminController implements Controller{
    @Override
    public void doControl(Request request) {
        Response response = new Response();

        switch (request.getMethod()) {
            case "Credenziali": //todo aggiunta visualizzazione
                //request.setController("User");
                //request.setMethod("getAll");
                //request.setBody(null);
                //MainDispatcher.getInstance().callAction(request);
                MainDispatcher.getInstance().callView("user.UserCRUD", null);
                break;
            case "Anagrafica":
                MainDispatcher.getInstance().callView("anagrafica.AnagraficaCRUD", null);
                break;
            case "Impianto":
                request.setController("Impianto");
                request.setMethod("getAll");
                request.setBody(null);
                MainDispatcher.getInstance().callAction(request);
                break;
            case "Piste":
                request.setController("Piste");
                request.setMethod("getAll");
                request.setBody(null);
                MainDispatcher.getInstance().callAction(request);
                break;
            case "Attrezzatura":
                request.setController("Attrezzature");
                request.setMethod("getAll");
                request.setBody(null);
                MainDispatcher.getInstance().callAction(request);
                break;
            case "Noleggi":
                request.setController("Noleggio");
                request.setMethod("getAll");
                request.setBody(null);
                MainDispatcher.getInstance().callAction(request);
                break;
            case "Prenotazioni":
                request.setController("Prenotazioni");
                request.setMethod("getAll");
                request.setBody(null);
                MainDispatcher.getInstance().callAction(request);
                break;
            case "Esci":
                    userSpecs.getInstance().esci();
                    MainDispatcher.getInstance().callView("Home",null);
                break;
            default:
                userSpecs.getInstance().esci();
                MainDispatcher.getInstance().callView("MenuSuperAdmin",null);
                break;

        }
    }
}
