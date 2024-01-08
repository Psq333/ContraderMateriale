package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.LoginDTO;
import it.contrader.main.MainDispatcher;

import java.util.HashMap;
import java.util.Map;

public class LoginView extends AbstractView {

	private String username;
	
	private String password;

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
		Request request = new Request();
		request.getBody().put("loginDTO", loginDTO);
		request.setController("Home");
		request.setMethod("login");
		MainDispatcher.getInstance().callAction(request);
	}


}
