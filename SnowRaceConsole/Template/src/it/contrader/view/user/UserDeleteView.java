package it.contrader.view.user;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class UserDeleteView extends AbstractView {
	private Request request;

	private int id;
	private final String mode = "DELETE";

	public UserDeleteView() {
	}

	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode DELETE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Response response) {
		if (response!=null) {
			System.out.println("Cancellazione andata a buon fine.\n");
			Request request = new Request("User", "getAll", null);
			MainDispatcher.getInstance().callAction(request);
		}
	}

	/**
	 * Chiede all'utente di inserire l'id dell'utente da cancellare
	 */
	@Override
	public void showOptions() {
			System.out.println("Inserisci id dell'utente:");
			id = Integer.parseInt(getInput());

	}

	/**
	 * impacchetta la request con l'id dell'utente da cancellare
	 */
	@Override
	public void submit() {
		request = new Request();
		request.getBody().put("id", id);
		request.setController("User");
		request.setMethod("delete");
		MainDispatcher.getInstance().callAction(request);
	}


}
