package it.contrader.view.user;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import it.contrader.utilities.*;

/**
 * 
 * @author Vittorio
 *
 *Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class UserReadView extends AbstractView {

	private int id = 0;
	private String username;

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
			if (response.getBody().containsKey("supadmin")) {

			} else {
				UserDTO user = (UserDTO) response.getBody().get("user");
				System.out.println(user);//todo non stampa

				//Request request;
				if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE")) {
					MainDispatcher.getInstance().callView("superAdmin.MenuSuperAdmin", null); //Todo nome view homesuperadmin
				} else
					MainDispatcher.getInstance().callView("anagrafica.AnagraficaRead", null);
				//request = new Request("", "", null);
				//MainDispatcher.getInstance().callView("",null);
			}
		}
	}

	
	/**
	 * chiede all'utente di inserire l'username oppure l'id dell'utente da leggere
	 */
	@Override
	public void showOptions() {
		if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE")) {
			System.out.println("Inserisci l'username dell'utente da cercare:");
			username = getInput();
			if (username.equals("")){
				System.out.println("Username non inserito. Inserire id user da ricercare:");
				scannerUtils.getInstance().askInt();
				id = scannerUtils.getInstance().getNumIn();
			}

		} else {
			username = userSpecs.getInstance().getUsername();
		}

	}

	/**
	 * impacchetta una request con l'id dell'utente da leggere e la manda al controller tramite il Dispatcher
	 */
	@Override
	public void submit() {
		Request request = new Request();
		if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE"))
			if (id > 0)
				request.getBody().put("iduser", id);
			else
				request.getBody().put("username", username);
		request.setController("User");
		request.setMethod("read");
		MainDispatcher.getInstance().callAction(request);
	}

}
