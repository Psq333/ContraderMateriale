package it.contrader.controller;

import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.LoginService;
import it.contrader.dto.AnagraficaDTO;
import it.contrader.service.AnagraficaService;
import it.contrader.service.UserService;
import it.contrader.utilities.userSpecs;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller implementation for handling user authentication requests.
 * This Controller interacts with the LoginService to authenticate user credentials.
 */
public class HomeController implements Controller {

    private final LoginService loginService;
    private final AnagraficaService anagraficaService;


    /**
     * Constructs a HomeController with a new instance of LoginService.
     */
    public HomeController() {
        loginService = new LoginService();
        anagraficaService = new AnagraficaService();
    }

    /**
     * Implements the method from Controller interface. Unpacks a request, extracts the username and password,
     * and uses LoginService to authenticate the user. Depending on the user type, it redirects to the corresponding view.
     * If authentication fails, it redirects back to the Login view.
     *
     * @param request A Request object that encapsulates the user's request.
     */
    public void doControl(Request request) {
        Response response = new Response();
        if (request != null) {
            switch (request.getMethod().toUpperCase()) {
                case "LOGIN":
                    UserDTO userDTO = loginService.login((LoginDTO) request.getBody().get("loginDTO"));
                    if (userDTO != null) {
                        switch (userDTO.getUsertype().toUpperCase()) {
                            case "ADMIN":
                                MainDispatcher.getInstance().callView("user.HomeAdmin", null);
                                break;
                            case "USER":
                                MainDispatcher.getInstance().callView("user.HomeUser", null);
                                break;
                            case "AMMINISTRATORE":
                                MainDispatcher.getInstance().callView("superAdmin.MenuSuperAdmin", null);
                                break;
                            default: //todo necessario?
                                MainDispatcher.getInstance().callView("Login", null);
                                break;
                        }
                    } else {
                        System.out.println("Invalid credentials");
                        MainDispatcher.getInstance().callView("Login", null);
                    }
                    break;
                case "REGISTER":
                    switch (userSpecs.getInstance().getUsertype()) {
                        case "ADMIN":
                        case "USER":
                            anagraficaService.insert((AnagraficaDTO) request.getBody().get("anagraficaDTO"), userSpecs.getInstance().getUsername());
                            MainDispatcher.getInstance().callView("anagrafica.AnagraficaInsert", response);
                            break;
                        case "AMMINISTRATORE":
                            anagraficaService.insert((AnagraficaDTO) request.getBody().get("anagraficaDTO"), (String) request.getBody().get("username"));
                            MainDispatcher.getInstance().callView("anagrafica.AnagraficaInsert", response);//todo anagrafica home menu?
                            break;
                        default:
                            MainDispatcher.getInstance().callView("user.UserInsert", null);
                            break;
                    }
                    break;
                default:
                    System.out.println("No method matches the inserted method: " + request.getMethod());
                    MainDispatcher.getInstance().callView("Login", null);
            }
        } else MainDispatcher.getInstance().callView("Login", null);
    }
}

