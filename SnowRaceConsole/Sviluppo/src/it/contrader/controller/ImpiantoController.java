package it.contrader.controller;

import it.contrader.dto.ImpiantoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.ImpiantoService;
import it.contrader.utilities.userSpecs;

import java.util.ArrayList;
import java.util.List;

public class ImpiantoController implements Controller{

    private static String sub_package = "Impianto.";

    private int idImpianto;
    private  ImpiantoDTO impiantoDTO;

    private final ImpiantoService impiantoService;


    public ImpiantoController() {
        this.impiantoService = new ImpiantoService();
    }
    @Override
    public void doControl(Request request) {
        Response response = new Response();
        response.put("boolean",false);
        switch (request.getMethod()){
            case "getAll":
                List<ImpiantoDTO> impiantiDTO = impiantoService.getAll();
                List<ImpiantoDTO> impiantiAdminDTO = new ArrayList<>();
                if(userSpecs.getInstance().getUsertype().equals("ADMIN")) {
                    for (ImpiantoDTO imp : impiantiDTO) {
                        if (imp.getAmministratore().equals(userSpecs.getInstance().getUsername())) {
                            impiantiAdminDTO.add(imp);
                        }
                    }
                    response.put("impianti", impiantiAdminDTO);
                    MainDispatcher.getInstance().callView(sub_package+"ListImpianti", response);
                }

                response.put("impianti", impiantiDTO);
                MainDispatcher.getInstance().callView(sub_package+"ListImpianti", response);
                break;
            case "read":
                idImpianto = (int) request.getBody().get("idImpianto");
                impiantoDTO = impiantoRead(idImpianto);
                response.put("impianto", impiantoDTO);
                response.put("boolean",true);
                if(userSpecs.getInstance().getUsertype().equals("ADMIN") && !impiantoDTO.getAmministratore().equals(userSpecs.getInstance().getUsername())){
                    response.put("impianto", null);
                    response.put("boolean",false);
                }
                MainDispatcher.getInstance().callView(sub_package+"ImpiantoRead", response);
                break;
            case "insert":
                ImpiantoDTO impiantoDaInserire = (ImpiantoDTO) request.getBody().get("impiantoDaInserire");
                impiantoService.insert(impiantoDaInserire);
                MainDispatcher.getInstance().callView(sub_package+"ImpiantoInsert", response);
                break;
            case "delete":
                idImpianto = (int) request.getBody().get("idImpianto");
                impiantoDTO = impiantoRead(idImpianto);
                if(userSpecs.getInstance().getUsertype().equals("ADMIN") && impiantoDTO.getAmministratore().equals(userSpecs.getInstance().getUsername())){
                    impiantoService.delete(idImpianto);
                    response.put("boolean",true);
                }else if (userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                    impiantoService.delete(idImpianto);
                }
                MainDispatcher.getInstance().callView(sub_package+"ImpiantoDelete", response);
                break;
            case "update":
                ImpiantoDTO impiantoDaModificare = (ImpiantoDTO) request.getBody().get("impiantoDaModificare");
                idImpianto = impiantoDaModificare.getIdImpianto();
                impiantoDTO = impiantoRead(idImpianto);
                if(userSpecs.getInstance().getUsertype().equals("ADMIN") && impiantoDTO.getAmministratore().equals(userSpecs.getInstance().getUsername())){
                    impiantoService.update(impiantoDaModificare);
                    response.put("boolean",true);
                }else if (userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                    impiantoService.update(impiantoDaModificare);
                }
                MainDispatcher.getInstance().callView(sub_package+"ImpiantoUpdate", response);
                break;
            case "Impianto.ImpiantoCRUD":
                ImpiantoDTO impianto = (ImpiantoDTO)request.getBody().get("impiantoDTO");
                response.put("impiantoDTO", impianto);
                MainDispatcher.getInstance().callView(sub_package+"ImpiantoCRUD", response);
                break;
            default:
                System.out.println("\n 404 pagina non trovata");
                if(userSpecs.getInstance().getUsertype().equals("ADMIN"))
                    MainDispatcher.getInstance().callView("user.HomeAdmin",null);
                else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE"))
                    MainDispatcher.getInstance().callView("superAdmin.MenuSuperAdmin",null);

        }

    }
    private ImpiantoDTO impiantoRead(int idImpianto){
        ImpiantoDTO impiantoDTO = impiantoService.read(idImpianto);
        return impiantoDTO;
    }
}
