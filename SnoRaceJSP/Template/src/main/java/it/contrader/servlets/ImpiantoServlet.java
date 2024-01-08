package it.contrader.servlets;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.service.ImpiantoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ImpiantoServlet")
public class ImpiantoServlet extends HttpServlet {

    private ImpiantoService impiantoService;

    @Override
    public void init(){
        this.impiantoService = new ImpiantoService();
    }

    //La chiamata GET ci da' la possibilita' di inserire i dati della ricerca nell'URL
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");
        if(mode != null){
            switch (mode.toUpperCase()){
                case "GET_ALL":
                    break;
                case "READ":
                    int idImpiantoDaLeggere = Integer.parseInt(request.getParameter("IdImpianto"));
                    ImpiantoDTO impianto = impiantoService.read(idImpiantoDaLeggere);
                    request.setAttribute("impianto", impianto);
                    getServletContext().getRequestDispatcher("/impianto/readimpianto.jsp").forward(request,response);
                    break;
                case "PREPARE_UPDATE": //Ti da' la possibilita' di vedere se l'utente esiste, se esiste lo reindirizza sulla pagina dell'update con i dati del db
                    int idImpiantoDaModificare = Integer.parseInt(request.getParameter("idImpianto"));
                    ImpiantoDTO impiantoDaModificare = impiantoService.read(idImpiantoDaModificare);
                    //todo vedere se bisogna fare controllo in caso non c'è l'utente
                    request.setAttribute("impiantoDaModificare", impiantoDaModificare);
                    getServletContext().getRequestDispatcher("/impianto/updateimpianto").forward(request, response);
                    break;
                case "DELETE":
                    int idImpiantoDaEliminare = Integer.parseInt(request.getParameter("idImpianto"));
                    if(impiantoService.delete(idImpiantoDaEliminare)){
                        updateList(request);
                        request.getRequestDispatcher("/impianto/...").forward(request,response); //todo REINDIRIZZAMENTO
                    } else throw new RuntimeException("Cancellazione impianto non andata a buon fine!");
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
            String nome = request.getParameter("nomeImpianto");
            String descrizione = request.getParameter("descrizioneImpianto");
            String luogo = request.getParameter("luogoImpianto");
            String amministratore = request.getParameter("amministratoreImpianto"); //todo capire se il controllo se l'amministratore esiste farlo qui o nel service (PENSO NEL SERVICE)
            ImpiantoDTO impianto = new ImpiantoDTO(nome,descrizione,luogo,amministratore);
            switch (mode.toUpperCase()){
                case "INSERT":
                    if(impiantoService.insert(impianto)){
                        updateList(request);
                        request.getRequestDispatcher("/impianto/...").forward(request,response); //todo REINDIRIZZAMENTO
                    } else throw  new RuntimeException("Inserimento non andato a buon fine!");
                    break;
                case "UPDATE":
                    int idImpianto = Integer.parseInt(request.getParameter("idImpianto"));
                    impianto.setIdImpianto(idImpianto);
                    if(impiantoService.update(impianto)){
                        updateList(request);
                        request.getRequestDispatcher("/impianto/...").forward(request,response); //todo REINDIRIZZAMENTO
                    } else throw  new RuntimeException("Inserimento non andato a buon fine!");
                    break;
            }
        }
        else throw new RuntimeException("Nessun metodo specificato");
    }

    public void updateList(HttpServletRequest request) {
        List<ImpiantoDTO> listDTO = impiantoService.getAll();
        request.setAttribute("impianti", listDTO);
    }

}
