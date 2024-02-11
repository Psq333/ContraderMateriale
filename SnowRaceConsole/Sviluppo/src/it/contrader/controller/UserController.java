package it.contrader.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.contrader.dto.AnagraficaDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Anagrafica;
import it.contrader.service.UserService;
import it.contrader.service.AnagraficaService;
import it.contrader.utilities.userSpecs;

/**
 * @author Vittorio
 * <p>
 * Si osservi che nel Controller compaiono solo oggetti del DTO e non del Model!
 */
public class UserController implements Controller {

    /**
     * definisce il pacchetto di vista user.
     */
    private static String sub_package = "user.";


    private final UserService userService;

    /**
     * Costruisce un oggetto di tipo UserService per poterne usare i metodi
     */
    public UserController() {
        this.userService = new UserService();
    }


    /**
     * Metodo dell'interfaccia Controller. Estrae dalla request la mode
     * (che riceve dalle view specifiche e pu� essere la richesta di una
     * scelta da parte dell'utente "GETCHOICE") e la scelta dell'utente.
     * <p>
     * Se la modalit� corrisponde ad una CRUD il controller chiama i service,
     * altrimenti rimanda alla View della CRUD per richiedere i parametri
     */
    @Override
    public void doControl(Request request) {
        Response response = new Response();

        switch (request.getMethod()) {
            case "getAll":
                List<UserDTO> usersDTO = userService.getAll(); //todo stampa via for each
                System.out.println(usersDTO);
                response.put("users", usersDTO);
                MainDispatcher.getInstance().callView("user.UserCRUD", response);
                break;
            case "read":
                UserDTO userToRead = new UserDTO();
                if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE")) {
                    if (request.getBody().containsKey("iduser")) {
                        int idusertoread = (int) request.getBody().get("iduser");
                        userToRead = userService.read(idusertoread);
                    } else
                        userToRead = userService.read(request.getBody().get("username").toString());
                    response.put("user", userToRead);
                    MainDispatcher.getInstance().callView("user.UserRead", response);
                } else {
                    userToRead = userService.read(userSpecs.getInstance().getUsername());
                    response.put("user", userToRead);
                    MainDispatcher.getInstance().callView("user.UserRead", response);
                }
                break;
            case "insert":
                UserDTO userToInsert = (UserDTO) request.getBody().get("userToInsert");
                userService.insert(userToInsert);
                if (request.getBody().containsKey("signin")) {
                    response.put("signin", "yes");
                    userSpecs.getInstance().setUsername(userToInsert.getUsername());
                    userSpecs.getInstance().setUsertype(userService.read(userToInsert.getUsername()).getUsertype());
                    userSpecs.getInstance().setId(userService.read(userToInsert.getUsername()).getId());
                }
                MainDispatcher.getInstance().callView(sub_package + "UserInsert", response);
                break;
            case "delete":
                boolean d = false;
                if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE")) {
                    if (request.getBody().containsKey("iduser")) {
                        int idusertodel = (int) request.getBody().get("iduser");
                        d = userService.delete(idusertodel);
                    } else
                        d = userService.delete(request.getBody().get("username").toString());
                } else
                    d = userService.delete(userSpecs.getInstance().getUsername());
                if (d)
                    MainDispatcher.getInstance().callView(sub_package + "UserDelete", response);
                else{
                    System.out.println("Fallimento cancellazione account");
                    if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE") )
                        MainDispatcher.getInstance().callView("superAdmin.UserSuperAdmin", null);//todo tbL
                    else
                        MainDispatcher.getInstance().callView(sub_package + "UserAccount", null);
                }
                break;
            case "update":
                UserDTO userToUpdate = (UserDTO) request.getBody().get("userToUpdate");
                String startusername;
                if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE"))
                    startusername = (String)request.getBody().get("startusername");
                boolean a = userService.update(userToUpdate);
                if (a) {
                    if (!userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                    userSpecs.getInstance().setUsername(userToUpdate.getUsername());
                    userSpecs.getInstance().setUsertype(userToUpdate.getUsertype());
                    }
                    MainDispatcher.getInstance().callView(sub_package + "UserUpdate", response);
                }else{
                    System.out.println("Fallimento update credenziali");
                    if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE") )
                        MainDispatcher.getInstance().callView(sub_package + "UserSuperAdmin", null);//todo tbL
                     else
                        MainDispatcher.getInstance().callView(sub_package + "UserAccount", null);

                }
                break;
            default:
                System.out.println("\n 404 pagina non trovata");
                MainDispatcher.getInstance().callView(sub_package + "HomeAdmin", null);
        }
    }
}
