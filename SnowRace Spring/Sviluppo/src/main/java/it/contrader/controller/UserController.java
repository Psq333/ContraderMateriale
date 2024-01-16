package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import it.contrader.dto.AnagraficaDTO;
import it.contrader.service.AnagraficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.UserDTO;
import it.contrader.model.User.Usertype;
import it.contrader.service.UserService;

import static it.contrader.model.User.Usertype.ADMIN;
import static it.contrader.model.User.Usertype.USER;

@Controller
@RequestMapping("/user")
//Questo sarà il percorso base per poter raggiungere i metodi di questo controller
//quindi a /user arriveranno tutte le richieste GET
public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private AnagraficaService anagraficaService;

	@PostMapping("/login")
	//In questo caso invece viene indicato che il metodo login si trova all'URL /loginn ed accetta richieste di tipo POST
	public String login(HttpServletRequest request, @RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {

		UserDTO userDTO = service.findByUsernameAndPassword(username, password);
		request.getSession().setAttribute("user", userDTO);
			if (userDTO != null && userDTO.getUsertype() != null) {
				String link = "home"+userDTO.getUsertype().name().toLowerCase();
				return link;
			}
			else {
				request.getSession().setAttribute("errorMessage", "Invalid username or password.");
				return "index";
			}
	}

	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "/user/users";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "/user/users";
	}

	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		if (request.getParameter("source")!=null)
			request.setAttribute("source", request.getParameter("source"));
		return "/user/updateuser";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {

		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		service.update(dto);

		if (((UserDTO) request.getSession().getAttribute("user")).getUsertype().name().equals("AMMINISTRATORE")) {

			if ("readuser".equals(request.getParameter("source"))) {
				request.getSession().setAttribute("user", dto);
				request.setAttribute("dto", dto);
				request.setAttribute("anag", anagraficaService.findByUserId(id));
				return "/user/readuser";
			} else {
				setAll(request);
				return "/user/users";
			}

		} else {
			request.getSession().setAttribute("user", dto);
			request.setAttribute("dto", dto);
			request.setAttribute("anag", anagraficaService.findByUserId(id));
			return "/user/readuser";
		}

	}

	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {
		UserDTO dto = new UserDTO();
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		try {
			service.insert(dto);
			setAll(request);
			if (request.getSession().getAttribute("user") != null) {
				if (((UserDTO) request.getSession().getAttribute("user")).getUsertype().name().equals("AMMINISTRATORE"))
					return "/user/users";
			} else {
				dto.setId((service.findByUsername(username).getId()));
				request.getSession().setAttribute("user", dto);
				return "/anagrafica/insert";
			}
		} catch (Exception e){
			request.getSession().setAttribute("errorMessage", "Username già in uso.");
			return "/user/insert";
		}
		return "";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
			request.setAttribute("dto", service.read(id));
			request.setAttribute("anag", anagraficaService.findByUserId(id));
			return "/user/readuser";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";
	}

	@GetMapping("/register")
	private String register(HttpServletRequest request){
		return "/user/insert";
	}

	private void setAll(HttpServletRequest request) {

		request.getSession().setAttribute("list", service.getAll());
	}
}
