package it.contrader.view.user;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class UserInsertView extends AbstractView{
	private String username;
	private String password;
	private String usertype;

	public UserInsertView() {
	}
	
	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode INSERT del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Response response) {
		if (response!=null) {
			System.out.println("Inserimento andato a buon fine.\n");
			Request request = new Request("User", "getAll", null);
			MainDispatcher.getInstance().callAction(request);
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da inserire
	 */
	@Override
	public void showOptions() {
			System.out.println("Inserisci username dell'utente:");
			username = getInput();
			System.out.println("Inserisci password dell'utente:");
			password = getInput();
			System.out.println("Inserisci tipo dell'utente:");
			usertype = getInput();
	}

	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		Request request = new Request();
		UserDTO userToInsert = new UserDTO(username, password, usertype);
		request.getBody().put("userToInsert", userToInsert);
		request.setMethod("insert");
		request.setController("User");
		MainDispatcher.getInstance().callAction(request);
	}


}
