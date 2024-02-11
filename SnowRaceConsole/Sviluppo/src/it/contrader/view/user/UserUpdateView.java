package it.contrader.view.user;


import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;
import it.contrader.main.MainDispatcher;
import it.contrader.service.UserService;
import it.contrader.utilities.userSpecs;
import it.contrader.utilities.userTypeMap;
import it.contrader.view.AbstractView;


public class UserUpdateView extends AbstractView {
	private int id;
	private String username, startusername;
	private String password;
	private String usertype;

	public UserUpdateView() {
	}

	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode UPDATE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Response response) {
		if (response!=null) {
			System.out.println("Modifica credenziali andata a buon fine.\n");
			//Request request = new Request("User", "getAll", null);
			//MainDispatcher.getInstance().callAction(request);
			switch (userSpecs.getInstance().getUsertype().toUpperCase()){
				case "AMMINISTRATORE":
					MainDispatcher.getInstance().callView("superAdmin.MenuSuperAdmin",null); //Todo nome view homesuperadmin
					break;
				case "ADMIN":
				case "UTENTE":
					MainDispatcher.getInstance().callView("anagrafica.AnagraficaUpdate",null);
					break;
			}
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da modificare
	 */
	@Override
	public void showOptions() { //todo amminstratore deve chiedere username
		if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE")) {
			System.out.println("Inserisci l'username dell'utente da modificare");
			startusername = getInput();
		} else
			startusername = userSpecs.getInstance().getUsername();

		UserService userService = new UserService();
		UserDTO user;
		user = userService.read(startusername);
		id = user.getId();
		System.out.println("------MODIFICA CREDENZIALI------\n(Premi Invio per saltare la modifica dell'elemento):\n");
		System.out.println("Inserisci il nuovo username:");
		username = getInput();
		System.out.println("Inserisci la nuova password:");
		password = getInput();
		if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE")) {
			System.out.println("Inserisci il nuovo usertype dell'utente: ( [A]dmin [U]ser [S]uperAdmin)");
			usertype = userTypeMap.getInstance().getUserType(getInput());
		} else usertype = userSpecs.getInstance().getUsertype();
		if (username.equals("") || username == null){
			username = startusername;
		}


	}


	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		Request request = new Request();
		UserDTO userToUpdate;


		if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE")) {
			userToUpdate = new UserDTO(id, username, password, usertype);
			//request.getBody().put("startusername", startusername);
		} else
			userToUpdate = new UserDTO(username, password, usertype);
		request.getBody().put("userToUpdate", userToUpdate);
		request.setMethod("update");
		request.setController("User");
		MainDispatcher.getInstance().callAction(request);
	}
}
