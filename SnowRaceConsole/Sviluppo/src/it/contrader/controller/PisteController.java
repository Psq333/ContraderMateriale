package it.contrader.controller;
import it.contrader.dto.ImpiantoDTO;
import it.contrader.dto.PisteDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Piste;
import it.contrader.service.ImpiantoService;
import it.contrader.service.PisteService;
import it.contrader.utilities.userSpecs;

import java.util.ArrayList;
import java.util.List;

public class PisteController implements Controller {

    private static String sub_package = "piste.";
    private ImpiantoService impiantoService;
    private final PisteService pisteService;
    private int idpista;
    private boolean esiste;

    public PisteController() {
        this.pisteService = new PisteService();
    }

    @Override
    public void doControl(Request request) {
        Response response = new Response();
        response.getBody().put("boolean",true);
        impiantoService = new ImpiantoService();

        switch (request.getMethod()) {
            case "getAll":
                List<PisteDTO> pisteDTO = pisteService.getAll();
                response.put("pistes", pisteDTO);
                if(userSpecs.getInstance().getUsertype().equals("ADMIN")){
                    response.put("pistes",pisteService.getAllUsername(userSpecs.getInstance().getUsername()));
                }
                MainDispatcher.getInstance().callView("piste.ListPiste", response);
                break;
            case "read":
                idpista = (int) request.getBody().get("idpista");
                PisteDTO pistaDTO = pisteService.read(idpista);
                response.put("piste", pistaDTO);
                if(userSpecs.getInstance().getUsertype().equals("ADMIN")){
                    if(!pistaAdmin(idpista)) { //Se pistaAdmin è false vuol dire che la pista non è dell'admin, quindi !
                        response.put("piste", null);
                        response.getBody().put("boolean",false);
                    }
                }
                MainDispatcher.getInstance().callView("piste.PisteRead", response);
                break;
            case "insert":
                PisteDTO pistetoinsert = (PisteDTO) request.getBody().get("pisteToInsert");
                if(userSpecs.getInstance().getUsertype().equals("ADMIN")){
                    List<ImpiantoDTO> impianti = impiantoService.getAll();
                    for(ImpiantoDTO imp: impianti) {
                        if(imp.getAmministratore().equals(userSpecs.getInstance().getUsername())){
                            if(userSpecs.getInstance().getUsername().equals(imp.getAmministratore()) && imp.getIdImpianto() == pistetoinsert.getIdImpianto()){ //TODO Da controllare
                                pisteService.insert(pistetoinsert);
                                break;
                            }
                        }
                    }
                } else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                    pisteService.insert(pistetoinsert);
                }

                MainDispatcher.getInstance().callView("piste.PisteInsert", response);
                break;
            case "delete":
                idpista = (int)request.getBody().get("id");
                if(userSpecs.getInstance().getUsertype().equals("ADMIN")){
                    if(pistaAdmin(idpista)) //Se è true allora vuol dire che l'impianto è dell'admin
                        pisteService.delete(idpista);
                    else response.getBody().put("boolean",false);
                }
                else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                    pisteService.delete(idpista);
                }
                MainDispatcher.getInstance().callView("piste.PisteDelete", response);
                break;
            case "update":
                PisteDTO pisteToUpdate = (PisteDTO) request.getBody().get("pisteToUpdate");
                if(userSpecs.getInstance().getUsertype().equals("ADMIN")){
                    if(pistaAdmin(pisteToUpdate.getIdPista())) //Se è true allora vuol dire che l'impianto è dell'admin
                        pisteService.update(pisteToUpdate);
                    else response.getBody().put("boolean",false);
                }else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                    pisteService.update(pisteToUpdate);
                }
                MainDispatcher.getInstance().callView("piste.PisteUpdate", response);
                break;
            case "piste.PisteCRUD":
                PisteDTO piste = (PisteDTO)request.getBody().get("pisteDTO");
                response.put("pisteDTO", piste);
                MainDispatcher.getInstance().callView("piste.PisteCRUD", response);
                break;
            case "pisteImpianto":
                List<PisteDTO> pisteList = pisteService.getAll();
                List<PisteDTO> pisteImpianto = new ArrayList<>();
                for (PisteDTO pista : pisteList){
                    if (pista.getIdImpianto() == (int)request.getBody().get("idImpianto"))
                        pisteImpianto.add(pista);
                }
                Response resp = new Response();
                resp.getBody().put("pistes",pisteImpianto);
                MainDispatcher.getInstance().callView("piste.ListPiste", resp);
                break;
            default:
                System.out.println("\n 404 pagina non trovata");
                MainDispatcher.getInstance().callView(sub_package + "HomeAdmin", null);


        }
    }

    private boolean pistaAdmin(int idpista){
        List<PisteDTO> pisteAdmin = pisteService.getAllUsername(userSpecs.getInstance().getUsername());
        for(PisteDTO piste : pisteAdmin){
            if (piste.getIdPista() == idpista) {
                return true;
            }
        }
        return false;
    }
}