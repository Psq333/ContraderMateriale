package it.contrader.view.user;


import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;


public class UserUpdateView extends AbstractView {
	private int id;
	private String username;
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
			System.out.println("Modifica andata a buon fine.\n");
			Request request = new Request("User", "getAll", null);
			MainDispatcher.getInstance().callAction(request);
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da modificare
	 */
	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci id dell'utente da modificare:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci il nuovo username:");
			username = getInput();
			System.out.println("Inserisci la nuova password:");
			password = getInput();
			System.out.println("Inserisci il tipo di utente:");
			usertype = getInput();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		Request request = new Request();
		UserDTO userToUpdate = new UserDTO(id, username, password, usertype);
		request.getBody().put("userToUpdate", userToUpdate);
		request.setMethod("update");
		request.setController("User");
		MainDispatcher.getInstance().callAction(request);
	}

}
