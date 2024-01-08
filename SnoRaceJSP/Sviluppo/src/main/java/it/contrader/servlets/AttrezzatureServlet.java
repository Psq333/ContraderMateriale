package it.contrader.servlets;
import it.contrader.dto.AttrezzatureDTO;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PisteDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.AttrezzatureService;
import it.contrader.service.ImpiantoService;
import it.contrader.service.UserService;


import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet ("/AttrezzatureServlet")
public class AttrezzatureServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private AttrezzatureService service;
    private UserService userService;
    private ImpiantoService servicei;
    @Override
    public void init() {
        this.servicei = new ImpiantoService();
        this.service = new AttrezzatureService();
        this.userService = new UserService();

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ImpiantoDTO> Impiansis;
        String mode = request.getParameter("mode");
        if(mode != null) {
            switch (mode.toUpperCase()) {
                case "GET_ALL":
                    List<AttrezzatureDTO> Attrezzature = service.getAll();
                    request.setAttribute("attrezzature", Attrezzature);
                    Impiansis = servicei.getAll((UserDTO) request.getSession().getAttribute("user"));
                    request.setAttribute("impiantis", Impiansis);
                    updateList(request);
                    request.getRequestDispatcher("/attrezzature/homeAttrezzature.jsp").forward(request, response);
                    break;
                case "GETALLUSER":
                    List<AttrezzatureDTO> attrezzatures = service.getAll();
                    request.setAttribute("userattrezzature", attrezzatures);
                    updateList(request);
                    request.getRequestDispatcher("/attrezzature/userAttrezzature.jsp").forward(request, response);
                    break;
                case "READ":
                    int idAttrezzatureToRead = Integer.parseInt(request.getParameter("id"));
                    AttrezzatureDTO AttrezzatureE = service.read(idAttrezzatureToRead);
                    request.setAttribute("Attrezzature", AttrezzatureE);
                    getServletContext().getRequestDispatcher("/attrezzature/readAttrezzature.jsp").forward(request, response);
                    break;
                case "PREPARE_UPDATE":
                    int idAttrezzatureToUpdate = Integer.parseInt(request.getParameter("id"));
                    AttrezzatureDTO AttrezzatureToUpdate = service.read(idAttrezzatureToUpdate);
                    request.setAttribute("AttrezzatureToUpdate", AttrezzatureToUpdate);
                    updateList(request);
                    getServletContext().getRequestDispatcher("/attrezzature/updateAttrezzature.jsp").forward(request, response);
                    break;
                case "DELETE":
                    int idAttrezzatureToDelete = Integer.parseInt(request.getParameter("id"));
                    if(service.delete(idAttrezzatureToDelete)) {
                        updateList(request);
                        request.getRequestDispatcher("/attrezzature/homeAttrezzature.jsp").forward(request, response);
                    } else throw new RuntimeException("Cancellazione non andata a buon fine");
                    break;

                case "SCELTAUSER":
                    int idAttrezzature = Integer.parseInt(request.getParameter("IdAttrezzatura"));
                    request.setAttribute("IdAttrezzatura",idAttrezzature );
                    request.getRequestDispatcher("/noleggio/noleggiouser.jsp").forward(request, response);
                    break;
                default:
                    throw new RuntimeException("Metodo " + mode + " non disponibile");
            }
        } else throw new RuntimeException("Nessun metodo specificato");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        List<ImpiantoDTO> Impiansis;
        if(mode != null) {

            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            String descrizione = request.getParameter("descrizione");
            String nome = request.getParameter("nome");
            int idimpianto = Integer.parseInt(request.getParameter("idimpianto"));

            AttrezzatureDTO Attrezzature = new AttrezzatureDTO (prezzo, descrizione, nome,idimpianto );
            switch (mode.toUpperCase()) {
                case "INSERT":
                    if(service.insert(Attrezzature)) {
                        updateList(request);
                        Impiansis = servicei.getAll((UserDTO) request.getSession().getAttribute("user"));
                        request.setAttribute("impiantis", Impiansis);
                        request.getRequestDispatcher("/attrezzature/homeAttrezzature.jsp").forward(request, response);
                    } else throw new RuntimeException("Inserimento non andato a buon fine");
                    break;
                case "UPDATE":
                    int id = Integer.parseInt(request.getParameter("idattrezzature"));
                    Attrezzature.setIdAttrezzature(id);
                    if(service.update(Attrezzature)) {
                        updateList(request);
                        request.getRequestDispatcher("/attrezzature/homeAttrezzature.jsp").forward(request, response);
                    } else throw new RuntimeException("Modifica non andata a buon fine");
                    break;
            }
        } else throw new RuntimeException("Nessun metodo specificato");
    }

    public void updateList(HttpServletRequest request) {
        List<AttrezzatureDTO>listDTO = service.getAll();
        List<ImpiantoDTO> Impiansis;
        Impiansis = servicei.getAll((UserDTO) request.getSession().getAttribute("user"));
        request.setAttribute("impiantis", Impiansis);
        request.setAttribute("attrezzature", listDTO);

    }




}
