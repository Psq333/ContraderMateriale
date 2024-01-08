package it.contrader.servlets;

import java.util.List;
import java.sql.Date;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.AnagraficaDTO;
import it.contrader.dto.UserDTO;
import it.contrader.enums.Usertype;
import it.contrader.service.AnagraficaService;
/**
 * This servlet class handles various CRUD operations for users.
 * It provides functionality to read, create, update, and delete users.
 */
@WebServlet("/AnagraficaServlet")
public class AnagraficaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** Service used for user-related operations. */
    private AnagraficaService service;

    /**
     * Initializes the servlet and creates a new instance of the UserService.
     */
    @Override
    public void init() {
        this.service = new AnagraficaService();
    }

    /**
     * Handles GET requests. It provides functionality based on the "mode" parameter.
     *
     * @param request the HttpServletRequest object containing client request data
     * @param response the HttpServletResponse object to send data to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        if(mode != null) {
            switch (mode.toUpperCase()) {
                case "GETALL":
                    updateList(request);
                    request.getRequestDispatcher("/anagrafica/anagraficamanager.jsp").forward(request, response);
                    break;
                case "READ":
                    int idAnagToRead = Integer.parseInt(request.getParameter("id"));
                    AnagraficaDTO anag = service.read(idAnagToRead);
                    request.setAttribute("anag", anag);
                    getServletContext().getRequestDispatcher("/anagrafica/readuser.jsp").forward(request, response);
                    break;
                case "PREPARE_UPDATE":
                    int idAnagToUpdate = Integer.parseInt(request.getParameter("id"));
                    AnagraficaDTO anagToUpdate = service.read(idAnagToUpdate);
                    request.setAttribute("anagToUpdate", anagToUpdate);
                    getServletContext().getRequestDispatcher("/anagrafica/updateanagrafica.jsp").forward(request, response);
                    break;
                case "DELETE":
                    int idAnagToDelete = Integer.parseInt(request.getParameter("id"));
                    if(service.delete(idAnagToDelete)) {
                        updateList(request);
                        request.getRequestDispatcher("/anagrafica/anagraficamanager.jsp").forward(request, response);
                    } else throw new RuntimeException("Cancellazione non andata a buon fine");
                    break;
                default:
                    throw new RuntimeException("Metodo " + mode + " non disponibile");
            }
        } else throw new RuntimeException("Nessun metodo specificato");
    }

    /**
     * Handles POST requests. It provides functionality based on the "mode" parameter.
     *
     * @param request the HttpServletRequest object containing client request data
     * @param response the HttpServletResponse object to send data to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        if(mode != null) {
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String indirizzo = request.getParameter("indirizzo");
            String dataIn = request.getParameter("data");
            Date data = service.dateToSQL(dataIn);
            String luogo = request.getParameter("luogo");
            String username = request.getParameter("username");

            AnagraficaDTO anag = new AnagraficaDTO (nome, cognome, indirizzo, luogo, username, data);
            switch (mode.toUpperCase()) {
                case "INSERT":
                    if(service.insert(anag)) {
                        updateList(request);
                        request.getRequestDispatcher("/anagrafica/anagraficamanager.jsp").forward(request, response);
                    } else throw new RuntimeException("Inserimento non andato a buon fine");
                    break;
                case "UPDATE":
                    //int id = Integer.parseInt(request.getParameter("id"));
                    //anag.setId(id);
                    if(service.update(anag)) {
                        if (((Usertype)request.getSession().getAttribute("usertype")).name().equals("AMMINISTRATORE")) {
                            updateList(request);
                            request.getRequestDispatcher("/anagrafica/anagraficamanager.jsp").forward(request, response);
                        } else {
                            anag = service.read(anag.getUsername());
                            request.setAttribute("anag", anag);
                            request.getRequestDispatcher("home" + ((Usertype)request.getSession().getAttribute("usertype")).name().toLowerCase() + ".jsp").forward(request, response);
                        }
                    } else throw new RuntimeException("Modifica non andata a buon fine");


                    break;
                case "REGISTER":
                    if(service.insert(anag)) {
                        updateList(request);
                        //UserDTO user = new UserDTO(request.getParameter("username"), null, null);
                        //request.setAttribute("user", user);
                        request.getRequestDispatcher(("/home" + request.getParameter("usertype")).toLowerCase() + ".jsp").forward(request, response);
                    } else throw new RuntimeException("Inserimento non andato a buon fine");
                    break;
            }
        } else throw new RuntimeException("Nessun metodo specificato");
    }

    /**
     * Updates the list of users and sets it as a request attribute.
     *
     * @param request the HttpServletRequest object containing client request data
     */
    public void updateList(HttpServletRequest request) {
        List<AnagraficaDTO>listDTO = service.getAll();
        request.setAttribute("anags", listDTO);
    }
}