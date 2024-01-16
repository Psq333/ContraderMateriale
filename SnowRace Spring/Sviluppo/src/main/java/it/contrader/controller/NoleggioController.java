package it.contrader.controller;

import it.contrader.dto.AttrezzaturaDTO;
import it.contrader.dto.NoleggioDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;
import it.contrader.service.AttrezzaturaService;
import it.contrader.service.ImpiantoService;
import it.contrader.service.NoleggioService;
import it.contrader.service.UserService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Controller
@Builder
@RequestMapping("/noleggio")
public class NoleggioController {

    @Autowired
    private NoleggioService noleggioService;

    @Autowired
    private AttrezzaturaService attrezzaturaService;
    @Autowired
    private UserService userService;
    @Autowired
    private ImpiantoService imp;

    @GetMapping("/getall")
    public String getAll(HttpServletRequest request) {
        setAll(request);
        List<UserDTO> lista = userService.getAll();
        request.setAttribute("users", lista);
        List<AttrezzaturaDTO> list = attrezzaturaService.getAll();
        request.setAttribute("attrezzature", list);
        return "/noleggio/noleggiogetall";
    }

    @GetMapping("/getalluser")
    public String getAllUser(HttpServletRequest request) {
        setAll(request);
        List<AttrezzaturaDTO> list = attrezzaturaService.getAll();
        request.setAttribute("attrezzature", list);
        return "/noleggio/usergetall";
    }




    @GetMapping("/read")
    public String read(HttpServletRequest request, @RequestParam("idnoleggio") Long idnoleggio) {
        request.getSession().setAttribute("noleggioDTO", noleggioService.read(idnoleggio));
        return "/noleggio/readnoleggio";
    }

    @PostMapping("/insert")
    public String insert(HttpServletRequest request, //@RequestParam("idnoleggio") Long idnoleggio,
                         @RequestParam("user") Long user, @RequestParam("idattrezzatura") Long idattrezzatura,
                         @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {

        NoleggioDTO noleggioDTO = NoleggioDTO.builder()
                //             .idnoleggio(idnoleggio)
                .user(userService.read(user))
                .idattrezzatura(attrezzaturaService.read(idattrezzatura))
                .startDate(startDate)
                .endDate(endDate)
                .build();
        this.noleggioService.insert(noleggioDTO);
        List<UserDTO> list = userService.getAll();
        request.setAttribute("users", list);
        setAll(request);
        if(((UserDTO)request.getSession().getAttribute("user")).getUsertype().equals(User.Usertype.USER))
            return "/homeuser";
        else  return "/noleggio/noleggiogetall";
    }


    @GetMapping("/delete")
    public String delete(HttpServletRequest request, @RequestParam("idnoleggio") Integer idnoleggio) {
        this.noleggioService.delete(idnoleggio);
        setAll(request);
        return "/noleggio/noleggiogetall";
    }

    @GetMapping("/preupdate")
    public String preUpdate(HttpServletRequest request, @RequestParam("idnoleggio") Long idnoleggio) {
        request.getSession().setAttribute("noleggioDTO", this.noleggioService.read(idnoleggio));
        List<UserDTO> list = userService.getAll();
        request.setAttribute("users", list);
        return "/noleggio/updatenoleggio";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request, @RequestParam("idnoleggio") Long idnoleggio,
                         @RequestParam("user") Long user, @RequestParam("idattrezzatura") Long idattrezzatura,
                         @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        NoleggioDTO noleggioDTO = NoleggioDTO.builder()
                .idnoleggio(idnoleggio)
                .user(userService.read(user))
                .idattrezzatura(attrezzaturaService.read(idattrezzatura))
                .startDate(startDate)
                .endDate(endDate)
                .build();
        this.noleggioService.update(noleggioDTO);
        List<UserDTO> list = userService.getAll();
        request.setAttribute("users", list);
        setAll(request);
        return "/noleggio/noleggiogetall";

    }

    @GetMapping ("/prenUser")
    public String user_prenotazione (HttpServletRequest request, @RequestParam("idAttrezzatura") Long idAttrezzatura){
        //request.setAttribute("impiantoDTO", imp.read(idImpianto));
        request.setAttribute("attrezzaturaDTO", attrezzaturaService.read(idAttrezzatura));
        return "/noleggio/insertnoleggiouser";}
    //chiamato dalle Piste






    private void setAll(HttpServletRequest request) {
        List<AttrezzaturaDTO> list = attrezzaturaService.getAll();
        List<UserDTO> lista = userService.getAll();
        request.setAttribute("users", lista);
        request.setAttribute("attrezzature", list);
        request.getSession().setAttribute("noleggios", this.noleggioService.getAll());
    }
}
