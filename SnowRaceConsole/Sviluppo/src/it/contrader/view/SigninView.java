package it.contrader.view;

import it.contrader.controller.Response;
import it.contrader.controller.Request;
import it.contrader.dto.LoginDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.scannerUtils;

import java.util.Date;
import it.contrader.utilities.userTypeMap;
import it.contrader.dto.UserDTO;
import it.contrader.dto.AnagraficaDTO;

public class SigninView extends AbstractView{

    private String username, password, nome, cognome, indirizzo, luogoNascita, usertype;
    //private Date dataNascita = new Date();

    public SigninView(){}
    public void showResults(Response response) {
        if (response!=null){


            //if (response.getBody().get("login").equals("REGISTRAZIONEANAG")){
              //  MainDispatcher.getInstance().callView("AnagraficaInsert", response);
            //}
        //} else {
          //  Response res = new Response();
           // res.put("login", "REGISTER");
            //MainDispatcher.getInstance().callView("user.UserInsert", res);

        }else {

        }
    }

    public void showOptions() {
        System.out.println("----- .:REGISTRAZIONE:. ----");

    }

    public void submit(){
        Request request = new Request();
        request.setController("Home");
        request.setMethod("register");
        MainDispatcher.getInstance().callAction(request);//todo non accetta null!!!
    }
}
