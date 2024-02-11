package it.contrader.view.user;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;
public class UserAccountView extends AbstractView{

    String choice;

    Request request;

    public UserAccountView(){
        request = new Request();
    }
    @Override
    public void showResults(Response response) {
        if (response!=null){
            if (response.getBody().get("signin").equals("yes")){

            }
        }
    }

    @Override
    public void showOptions() {
        System.out.println("-------------ACCOUNT MENU------------\n");
        System.out.println("[V]isualizza Dettagli Profilo\n[M]odifica Dettagli Profilo\n[E]limina Account\n");
        System.out.println("[I]ndietro");
        this.choice = getInput();
    }

    @Override
    public void submit() {
        //Response response = new Response();
        switch(choice.toUpperCase()){
            case "V":
                MainDispatcher.getInstance().callView("user.UserRead", null);
                //MainDispatcher.getInstance().callView("anag.AnagraficaRead", null);
                break;
            case "M":
                MainDispatcher.getInstance().callView("user.UserUpdate",null);
                MainDispatcher.getInstance().callView("anag.AnagraficaUpdate", null);
                break;
            case "E":
                MainDispatcher.getInstance().callView("user.UserDelete",null);
                break;
            case "I":
                if(userSpecs.getInstance().getUsertype().equals("USER"))
                    MainDispatcher.getInstance().callView("user.HomeUser",null);
                else if(userSpecs.getInstance().getUsertype().equals("ADMIN"))
                    MainDispatcher.getInstance().callView("HomeAdmin", null);
                else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE"))
                    MainDispatcher.getInstance().callView("superAdmin.MenuSuperAdmin",null);
                break;
            default:
                System.out.println("Input non valido.\nInserisci una delle lettere tra parentesi quadra per selezionare una funzione");
                if(userSpecs.getInstance().getUsertype().equals("USER"))
                    MainDispatcher.getInstance().callView("user.HomeUser",null);
                else if(userSpecs.getInstance().getUsertype().equals("ADMIN"))
                    MainDispatcher.getInstance().callView("HomeAdmin", null);
                else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE"))
                    MainDispatcher.getInstance().callView("superAdmin.MenuSuperAdmin",null);
                break;
        }
       //MainDispatcher.getInstance().callAction(request);
    }
}
