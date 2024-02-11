package it.contrader.view.user;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.utilities.*;

public class UserInsertView extends AbstractView{
	private String username;
	private String password;
	private String usertype;
	private Request request;

	public UserInsertView() {
		request = new Request();
	}
	
	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode INSERT del controller) mostra
	 * l'esito dell'operazione
	 */


	@Override
	public void showResults(Response response) {
		if (response!=null) {
			System.out.println("Inserimento credenziali andato a buon fine.\n");
			if (response.getBody().containsKey("signin")) {
				//Request request = new Request("User", "getAll", null); //todo spostarsi in Home::superuseer, admin, user
				MainDispatcher.getInstance().callView("anagrafica.AnagraficaInsert", null);
			} else {
				MainDispatcher.getInstance().callView("AnagraficaSuperAdmin", response); //todo tbL to menu gestione anagraficheper superadmin
			}
			// else {
			//	MainDispatcher.getInstance().callView("UserAccount",response);
			//}

		}
				//MainDispatcher.getInstance().callAction(request);
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da inserire
	 */
	@Override
	public void showOptions() {
			System.out.println("-----.:INSERIMENTO CREDENZIALI:.-----");
			System.out.println("Inserisci username dell'utente:");
			username = getInput();
			System.out.println("Inserisci password dell'utente:");
			password = getInput();
			System.out.println("Inserisci tipo dell'utente ( [A]dmin o [U]ser ...):");
			usertype = userTypeMap.getInstance().getUserType(getInput().toUpperCase());
	}

	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		UserDTO userToInsert = new UserDTO(username, password, usertype);
		//request = new Request("User", "insert", null);
		if (!userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE"))
			this.request.getBody().put("signin", "yes");
		this.request.getBody().put("userToInsert", userToInsert);
		this.request.setMethod("insert");
		this.request.setController("User");
		MainDispatcher.getInstance().callAction(this.request);
	}


}
