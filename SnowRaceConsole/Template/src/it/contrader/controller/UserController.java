package it.contrader.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.UserService;

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
                List<UserDTO> usersDTO = userService.getAll();
                response.put("users", usersDTO);
                MainDispatcher.getInstance().callView("User", response);
                break;
            case "read":
                UserDTO userDTO = userService.read(Integer.parseInt(request.getBody().get("id").toString()));
                response.put("user", userDTO);
                MainDispatcher.getInstance().callView(sub_package + "UserRead", response);
                break;
            case "insert":
                UserDTO usertoinsert = (UserDTO) request.getBody().get("userToInsert");
                userService.insert(usertoinsert);
                MainDispatcher.getInstance().callView(sub_package + "UserInsert", response);
                break;
            case "delete":
                userService.delete(Integer.parseInt(request.getBody().get("id").toString()));
                MainDispatcher.getInstance().callView(sub_package + "UserDelete", response);
                break;
            case "update":
                UserDTO userToUpdate = (UserDTO) request.getBody().get("userToUpdate");
                userService.update(userToUpdate);
                MainDispatcher.getInstance().callView(sub_package + "UserUpdate", response);
                break;
            default:
                System.out.println("\n 404 pagina non trovata");
                MainDispatcher.getInstance().callView(sub_package + "HomeAdmin", null);
        }
    }
}
