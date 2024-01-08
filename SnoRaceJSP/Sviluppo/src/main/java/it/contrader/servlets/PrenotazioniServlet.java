package it.contrader.servlets;

import it.contrader.dto.PisteDTO;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.dto.UserDTO;
import it.contrader.enums.Usertype;
import it.contrader.service.ImpiantoService;
import it.contrader.service.PrenotazioniService;
import it.contrader.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/PrenotazioniServlet")
public class PrenotazioniServlet extends HttpServlet{



        private static final long serialVersionUID = 1L;

        /** Service used for user-related operations. */
        private PrenotazioniService service;

        /**
         * Initializes the servlet and creates a new instance of the UserService.
         */
        @Override
        public void init() {
            this.service = new PrenotazioniService();
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
                        request.getRequestDispatcher("/prenotazioni/prenotazionimanager.jsp").forward(request, response);
                        break;
                    case "READ":
                        int idPrenotazioneToRead = Integer.parseInt(request.getParameter("id"));
                        PrenotazioniDTO prenotazione = service.read(idPrenotazioneToRead);
                        request.setAttribute("user", prenotazione);
                        getServletContext().getRequestDispatcher("/prenotazioni/readprenotazione.jsp").forward(request, response);
                        break;
                    case "PREPARE_UPDATE":
                        int idPrenotazioneToUpdate = Integer.parseInt(request.getParameter("id"));
                        PrenotazioniDTO prenotazioneToUpdate = service.read(idPrenotazioneToUpdate);
                        request.setAttribute("prenotazioneToUpdate", prenotazioneToUpdate);
                        ImpiantoService imp = new ImpiantoService();
                        Map<Integer,List<PisteDTO>> map = imp.getPisteImpianto();
                        request.setAttribute("map", map);
                        getServletContext().getRequestDispatcher("/prenotazioni/UpdatePrenotazione.jsp").forward(request, response);
                        break;
                    case "DELETE":
                        int idPrenotazioneToDelete = Integer.parseInt(request.getParameter("id"));
                        if(service.delete(idPrenotazioneToDelete)) {
                            updateList(request);
                            request.getRequestDispatcher("/prenotazioni/prenotazionimanager.jsp").forward(request, response);
                        } else throw new RuntimeException("Cancellazione non andata a buon fine");
                        break;

                    case "SEARCH":
                        List<PrenotazioniDTO> searchRes = null;
                        if (((Usertype)request.getSession().getAttribute("usertype")).name().equals("AMMINISTRATORE"))
                            searchRes = service.Search(request.getParameter("what"), request.getParameter("which"), request.getParameter("username"), ((Usertype)request.getSession().getAttribute("usertype")).name());
                        else
                            searchRes = service.Search(request.getParameter("what"), request.getParameter("which"), (String)request.getSession().getAttribute("username"), ((Usertype)request.getSession().getAttribute("usertype")).name());
                        request.setAttribute("prenotazioni", searchRes);
                        request.getRequestDispatcher("/prenotazioni/searchprenotazioni.jsp").forward(request, response);
                    case "PRENOTAZIONE_USER":
                        int idImpianto = Integer.parseInt(request.getParameter("idImpianto"));
                        int idPista = Integer.parseInt(request.getParameter("idPista"));
                        request.setAttribute("idImpianto", idImpianto);
                        request.setAttribute("idPista", idPista);
                        request.getRequestDispatcher("/prenotazioni/prenotazioneInsert.jsp").forward(request, response);
                        break;
                    case "AGGIUNTA":
                        ImpiantoService imp1 = new ImpiantoService();
                        Map<Integer,List<PisteDTO>> map1 = imp1.getPisteImpianto();
                        request.setAttribute("map", map1);
                        UserService serv = new UserService();
                        request.setAttribute("users", serv.getAll());
                        request.getRequestDispatcher("/prenotazioni/prenotazioneInsertAmministratore.jsp").forward(request, response);
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
                String username = request.getParameter("username");
                int idpista = Integer.parseInt(request.getParameter("idpista"));
                String data_inizio = request.getParameter("data_i");
                String data_fine = request.getParameter("data_f");
                PrenotazioniDTO prenotazione = new PrenotazioniDTO (idpista, username, data_inizio, data_fine);
                switch (mode.toUpperCase()) {
                    case "INSERT":
                        if(service.insert(prenotazione)) {
                            updateList(request);
                            UserDTO user = (UserDTO) request.getSession().getAttribute("user");
                            if(user.getUsertype().equals(Usertype.AMMINISTRATORE)){
                                request.getRequestDispatcher("/prenotazioni/prenotazionimanager.jsp").forward(request, response);
                            }else if(user.getUsertype().equals(Usertype.USER)){
                                getServletContext().getRequestDispatcher("/homeuser.jsp").forward(request, response);
                            }

                        } else throw new RuntimeException("Inserimento non andato a buon fine");
                        break;
                    case "UPDATE":
                        int id = Integer.parseInt(request.getParameter("id"));
                        prenotazione.setId_prenotazione(id);
                        if(service.update(prenotazione)) {
                            if (((Usertype)request.getSession().getAttribute("usertype")).name().equals("AMMINISTRATORE")) {
                                updateList(request);
                                request.getRequestDispatcher("/prenotazioni/prenotazionimanager.jsp").forward(request, response);
                            }
                        } else throw new RuntimeException("Modifica non andata a buon fine");
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
            List<PrenotazioniDTO> listDTO = service.getAll();
            request.setAttribute("prenotazioni", listDTO);
        }


    }
