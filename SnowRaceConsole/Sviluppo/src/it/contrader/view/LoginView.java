package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.LoginDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.*;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;

public class LoginView extends AbstractView {

	private String username;
	private String usertype;
	private String password;
	private Request request;

	public LoginView(){this.request = new Request();}

	public void showResults(Response response) {
	}
	public void showOptions() {
		
		System.out.println("----- .:LOGIN:. ----");
		
		System.out.println(" Nome utente:");
		this.username = getInput();
		
		System.out.println(" Password:");
		this.password = getInput();
	}
	public void submit() {
		LoginDTO loginDTO = new LoginDTO(username, password);
		this.request.getBody().put("loginDTO", loginDTO);
		this.request.setController("Home");
		this.request.setMethod("login");
		MainDispatcher.getInstance().callAction(this.request);
	}
}
