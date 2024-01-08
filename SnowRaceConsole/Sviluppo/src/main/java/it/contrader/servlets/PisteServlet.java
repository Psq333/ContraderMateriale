package it.contrader.servlets;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.enums.Usertype;
import it.contrader.dto.PisteDTO;
import it.contrader.service.PisteService;

/**
 * This servlet class handles various CRUD operations for Pistes.
 * It provides functionality to read, create, update, and delete Pistes.
 */
@WebServlet("/PisteServlet")
public class PisteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /** Service used for Piste-related operations. */
    private PisteService service;

    /**
     * Initializes the servlet and creates a new instance of the PisteService.
     */
    @Override
    public void init() {
        this.service = new PisteService();
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
                    List<PisteDTO> Pistes = service.getAll();
                    request.setAttribute("pistes", Pistes);
                    updateList(request);
                    request.getRequestDispatcher("/piste/homepiste.jsp").forward(request, response);
                    break;
                case "READ":
                    int idPisteToRead = Integer.parseInt(request.getParameter("id"));
                    PisteDTO Piste = service.read(idPisteToRead);
                    request.setAttribute("Piste", Piste);
                    getServletContext().getRequestDispatcher("/piste/readpista.jsp").forward(request, response);
                    break;
                case "PREPARE_UPDATE":
                    int idPisteToUpdate = Integer.parseInt(request.getParameter("id"));
                    PisteDTO PisteToUpdate = service.read(idPisteToUpdate);
                    request.setAttribute("PisteToUpdate", PisteToUpdate);
                    updateList(request);
                    getServletContext().getRequestDispatcher("/piste/updatepiste.jsp").forward(request, response);
                    break;
                case "DELETE":
                    int idPisteToDelete = Integer.parseInt(request.getParameter("id"));
                    if(service.delete(idPisteToDelete)) {
                        updateList(request);
                        request.getRequestDispatcher("/piste/homepiste.jsp").forward(request, response);
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
            int idPista = Integer.parseInt(request.getParameter("idpista"));
            int idImpianto = Integer.parseInt(request.getParameter("idimpianto"));
            String Difficolta = request.getParameter("difficolta");
            double Prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int PrenMax = Integer.parseInt(request.getParameter("prenmax"));

            PisteDTO Piste = new PisteDTO (idPista, idImpianto, Difficolta, Prezzo, PrenMax);
            switch (mode.toUpperCase()) {
                case "INSERT":
                    if(service.insert(Piste)) {
                        updateList(request);
                        request.getRequestDispatcher("/piste/homepiste.jsp").forward(request, response);
                    } else throw new RuntimeException("Inserimento non andato a buon fine");
                    break;
                case "UPDATE":
                    int id = Integer.parseInt(request.getParameter("idpista"));
                    Piste.setIdPista(id);
                    if(service.update(Piste)) {
                        updateList(request);
                        request.getRequestDispatcher("/piste/homepiste.jsp").forward(request, response);
                    } else throw new RuntimeException("Modifica non andata a buon fine");
                    break;
            }
        } else throw new RuntimeException("Nessun metodo specificato");
    }

    /**
     * Updates the list of Pistes and sets it as a request attribute.
     *
     * @param request the HttpServletRequest object containing client request data
     */
    public void updateList(HttpServletRequest request) {
        List<PisteDTO>listDTO = service.getAll();
        request.setAttribute("pistes", listDTO);
    }
}