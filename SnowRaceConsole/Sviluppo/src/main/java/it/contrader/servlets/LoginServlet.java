package it.contrader.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.UserDTO;
import it.contrader.service.LoginService;

/**
 * La classe LoginServlet estende la classe HttpServlet e gestisce
 * il processo di autenticazione degli utenti.
 *
 * <p>Quando riceve una richiesta POST, tenta di eseguire il login con
 * le credenziali fornite. Se l'autenticazione ha successo, salva il
 * DTO dell'utente nella sessione corrente e reindirizza alla pagina
 * appropriata in base al tipo di utente. In caso di insuccesso,
 * reindirizza alla pagina di login con un messaggio di errore.
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginService service;

    /**
     * Inizializza la servlet creando una nuova istanza del servizio di login.
     */
    @Override
    public void init() {
        this.service = new LoginService();
    }

    /**
     * Gestisce le richieste POST ricevute. In questo caso si gestisce la richiesta di login.
     *
     * @param request la richiesta HTTP ricevuta
     * @param response la risposta HTTP da inviare
     * @throws ServletException se si verifica un errore generale per questa richiesta
     * @throws IOException se si verifica un errore di I/O durante la gestione della richiesta
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDTO userDTO = service.login(username, password);

        if (userDTO != null) {
            request.getSession().setAttribute("user", userDTO);
            if (userDTO.getUsertype() != null) {
                String redirectPage;
                switch (userDTO.getUsertype()) {
                    case ADMIN:
                        redirectPage = "/homeadmin.jsp";
                        break;
                    case USER:
                        redirectPage = "/homeuser.jsp";
                        break;
                    case AMMINISTRATORE:
                        redirectPage = "/homeamministratore.jsp";
                        break;
                    default:
                        redirectPage = "/index.jsp";
                        break;
                }
                redirectToPage(request, response, redirectPage);
            } else {
                throw new RuntimeException("Lo usertype non è stato valorizzato");
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            redirectToPage(request, response, "/index.jsp");
        }
    }

    /**
     * Reindirizza alla pagina specificata.
     *
     * @param request la richiesta HTTP ricevuta
     * @param response la risposta HTTP da inviare
     * @param page la pagina verso cui reindirizzare
     * @throws ServletException se si verifica un errore generale per questa richiesta
     * @throws IOException se si verifica un errore di I/O durante la gestione della richiesta
     */
    private void redirectToPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
