package it.contrader.servlets;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.NoleggioDTO;
import it.contrader.dto.AttrezzatureDTO;
import it.contrader.dto.UserDTO;
import it.contrader.enums.Usertype;
import it.contrader.service.AttrezzatureService;
import it.contrader.service.ImpiantoService;
import it.contrader.service.NoleggioService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
@WebServlet("/NoleggioServlet")
public class NoleggioServlet extends HttpServlet {
    private NoleggioService service;
    private AttrezzatureService servicea;
    @Override
    public void init(){
        this.servicea = new AttrezzatureService();
        this.service = new NoleggioService();
    }

    //La chiamata GET ci da' la possibilita' di inserire i dati della ricerca nell'URL
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AttrezzatureDTO> attrezzatures;
        String mode = request.getParameter("mode");
        if(mode != null){
            switch (mode.toUpperCase()){
                case "GET_ALL":
                    List<NoleggioDTO> noleggi = service.getAll();
                    request.setAttribute("noleggi", noleggi);
                    attrezzatures = servicea.getAll(/*ATTENZIONE qua va inserito il GetAll di biagio il quale restituisce la lista delle attrezzature in baase allo usertype */);
                    request.setAttribute("attrezzatures", attrezzatures);
                    updateList(request);
                    request.getRequestDispatcher("noleggio/amministratorenoleggio.jsp").forward(request, response);
                    break;
                case "READ":
                    int idNoleggioDaLeggere = Integer.parseInt(request.getParameter("id"));
                    NoleggioDTO noleggio = service.read(idNoleggioDaLeggere);
                    request.setAttribute("noleggio", noleggio);
                    getServletContext().getRequestDispatcher("/noleggio/readnoleggio.jsp").forward(request,response);
                    break;
                case "PREPARE_UPDATE": //Ti da' la possibilita' di vedere se l'utente esiste, se esiste lo reindirizza sulla pagina dell'update con i dati del db
                    int idNoleggioDaModificare = Integer.parseInt(request.getParameter("id"));
                    NoleggioDTO noleggioDaModificare = service.read(idNoleggioDaModificare);
                    request.setAttribute("noleggioDaModificare", noleggioDaModificare);
                    updateList(request);
                    getServletContext().getRequestDispatcher("/noleggio/updatenoleggio.jsp").forward(request, response);
                    break;
                case "DELETE":
                    int idNoleggioDaEliminare = Integer.parseInt(request.getParameter("id"));
                    if(service.delete(idNoleggioDaEliminare)){
                        List<NoleggioDTO> noleggi1 = service.getAll();
                        request.setAttribute("noleggi", noleggi1);
                        List <AttrezzatureDTO> attrezzatures1 = servicea.getAll(/*ATTENZIONE qua va inserito il GetAll di biagio il quale restituisce la lista delle attrezzature in baase allo usertype */);
                        request.setAttribute("attrezzatures", attrezzatures1);
                        updateList(request);
                        request.getRequestDispatcher("/noleggio/amministratorenoleggio.jsp").forward(request, response);
                    } else throw new RuntimeException("Cancellazione non andata a buon fine");
                    break;
                default:
                    throw new RuntimeException("Metodo " + mode + " non disponibile");
            }
        }
        else throw new RuntimeException("Nessun metodo specificato");
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        if(mode != null){
            String username = request.getParameter("username");
            int idAttrezzatura = Integer.parseInt(request.getParameter("idattrezzatura"));
            String dataIn = request.getParameter("data_inizio");
            Date data_inizio = service.dateToSQL(dataIn);
            String dataFin = request.getParameter("data_fine");
            Date data_fine = service.dateToSQL(dataFin);
            NoleggioDTO noleggio = new NoleggioDTO(username,idAttrezzatura,data_inizio, data_fine);
            switch (mode.toUpperCase()){
                case "INSERT":
                    if(service.insert(noleggio)){
                        List<NoleggioDTO> noleggi = service.getAll();
                        request.setAttribute("noleggi", noleggi);
                        List <AttrezzatureDTO> attrezzatures = servicea.getAll(/*ATTENZIONE qua va inserito il GetAll di biagio il quale restituisce la lista delle attrezzature in baase allo usertype */);
                        request.setAttribute("attrezzatures", attrezzatures);
                        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
                        if(user.getUsertype().equals(Usertype.AMMINISTRATORE)){
                            request.getRequestDispatcher("/noleggio/amministratorenoleggio.jsp").forward(request,response);
                        }else{
                            List<NoleggioDTO> Nolegg = service.getAllAttrezzature(user.getUsername());
                            request.setAttribute("noleggoatt", Nolegg);
                            request.getRequestDispatcher("homeuser.jsp").forward(request, response);
                        }


                        updateList(request);
                        request.getRequestDispatcher("/noleggio/amministratorenoleggio.jsp").forward(request,response); //todo REINDIRIZZAMENTO
                    } else throw  new RuntimeException("Inserimento non andato a buon fine!");
                    break;
                case "UPDATE":
                    int idNoleggio = Integer.parseInt(request.getParameter("idnoleggio"));
                    noleggio.setIdNoleggio(idNoleggio);
                    if(service.update(noleggio)){
                        List<NoleggioDTO> noleggi = service.getAll();
                        request.setAttribute("noleggi", noleggi);
                        List <AttrezzatureDTO> attrezzatures = servicea.getAll(/*ATTENZIONE qua va inserito il GetAll di biagio il quale restituisce la lista delle attrezzature in baase allo usertype */);
                        request.setAttribute("attrezzatures", attrezzatures);
                        updateList(request);
                        request.getRequestDispatcher("/noleggio/amministratorenoleggio.jsp").forward(request,response); //todo REINDIRIZZAMENTO
                    } else throw  new RuntimeException("Inserimento non andato a buon fine!");
                    break;
            }
        }
        else throw new RuntimeException("Nessun metodo specificato");
    }

    public void updateList(HttpServletRequest request) {
        List<NoleggioDTO> listDTO = service.getAll();
        request.setAttribute("noleggi", listDTO);
    }

}



