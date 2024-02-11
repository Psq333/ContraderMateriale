package it.contrader.view.user;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.contrader.utilities.userSpecs;

public class ListUserView extends AbstractView {

    private String choice;
    private Map<Integer, UserDTO> mappa;

    //private  ListUserAdminView(){

    //}



    @Override
    public void showResults(Response response) {
        if(response != null){
            mappa = new HashMap<Integer,UserDTO>();
            List<UserDTO> user = (List<UserDTO>) response.getBody().get("user");
            System.out.println("-------------CREDENZIALI UTENTI-------------");
            System.out.println("idUtente\tusername\tpassword\tusertype");
            for (UserDTO utente : user){
                mappa.put(utente.getId(),utente);
                System.out.println(utente);
                System.out.println();
            }
        }
    }

    @Override
    public void showOptions() {
    }

    @Override
    public void submit() {
        if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE") || userSpecs.getInstance().getUsertype().equals("ADMIN")) {
            Request request = new Request();
            request.setController("User");
            request.setMethod("UserCRUD");
            //request.getBody().put("userDTO", null);
            MainDispatcher.getInstance().callAction(request);
        }
    }
}
