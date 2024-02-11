package it.contrader.view.user;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;
import it.contrader.utilities.scannerUtils;

public class UserDeleteView extends AbstractView {
	private Request request;

	private int id;
	private String username;
	private String choice;
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
			switch (userSpecs.getInstance().getUsertype().toUpperCase()){
				case "AMMINISTRATORE":
					MainDispatcher.getInstance().callView("HomeSuperAdmin",null); //Todo nome view homesuperadmin
					break;
				case "ADMIN":
				case "UTENTE":
					MainDispatcher.getInstance().callView("Home",null);
					break;
			}
		}
	}

	/**
	 * Chiede all'utente di inserire l'id dell'utente da cancellare
	 */
	@Override
	public void showOptions() {

		System.out.println("------CANCELLAZIONE ACCOUNT------\n\n");
		if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE")) {
			System.out.println("Inserisci l'username dell'utente da cancellare");
			username = getInput();
			if (username.equals("") || username == null){
				System.out.println("Username non inserito. Inserisci l'id dell'utente da cancellare");
				scannerUtils.getInstance().askInt();
				id = scannerUtils.getInstance().getNumIn();
			}

		} else {
			username = userSpecs.getInstance().getUsertype();
			id = userSpecs.getInstance().getId();
		}

		System.out.println("SEI TOTALMENTE SICURO DI VOLER CONTINUARE?\nI tuoi dati verranno cancellati\nPer poter accedere, occorrerà registrarsi nuovamente\n\t[S]i [N]o");
		this.choice = getInput().toUpperCase();


	}

	/**
	 * impacchetta la request con l'id dell'utente da cancellare
	 */
	@Override
	public void submit() {
		request = new Request();
		if (userSpecs.getInstance().getUsertype().toUpperCase().equals("AMMINISTRATORE")) {
			if (id>0)
				request.getBody().put("iduser", id);
		 	else
				request.getBody().put("username", username);
		} else
			request.getBody().put("iduser", id);
		request.setController("User");
		request.setMethod("delete");
		MainDispatcher.getInstance().callAction(request);
	}


}
