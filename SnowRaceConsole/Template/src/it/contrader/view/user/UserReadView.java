package it.contrader.view.user;

import it.contrader.controller.Request;

import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

/**
 * 
 * @author Vittorio
 *
 *Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class UserReadView extends AbstractView {

	private int id;

	public UserReadView() {
	}

	/**
	 * Se la request � null (ovvero quando arriva dal controller con mode GETCHOICE e choice L 
	 * il metodo � vuoto.
	 * 
	 * Altrimenti se arriva con uno user nella request (ovvero quando arriva
	 * dal controller con mode READ) mostra lo user. In questo caso torna alla UserView senza eseguire
	 * gli altri due metodi
	 */
	@Override
	public void showResults(Response response) {
		if (response != null) {
			UserDTO user = (UserDTO) response.getBody().get("user");
			System.out.println(user);
			Request request = new Request("User", "getAll", null);
			MainDispatcher.getInstance().callAction(request);
		}
	}

	
	/**
	 * chiede all'utente di inserire l'id dell'utente da leggere
	 */
	@Override
	public void showOptions() {
		System.out.println("Inserisci l'ID dell'utente:");
		id = Integer.parseInt(getInput());
	}

	/**
	 * impacchetta una request con l'id dell'utente da leggere e la manda al controller tramite il Dispatcher
	 */
	@Override
	public void submit() {
		Request request = new Request();
		request.getBody().put("id", id);
		request.setController("User");
		request.setMethod("read");
		MainDispatcher.getInstance().callAction(request);
	}

}
