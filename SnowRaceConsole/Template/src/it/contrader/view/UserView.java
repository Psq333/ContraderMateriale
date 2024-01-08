package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.enums.PackageEnum;
import it.contrader.controller.Response;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;


/**
 * 
 * @author Vittorio
 *
 * Si osservi che alla View arrivano solo oggetti di tipo DTO!
 */
public class UserView extends AbstractView {

	private String choice;

	public UserView() {
		
	}

	/**
	 * Mostra la lista utenti
	 */
	@Override
	public void showResults(Response response) {
		if (response != null) {
			System.out.println("\n------------------- Gestione utenti ----------------\n");
			System.out.println("ID\tUsername\tPassword\tTipo Utente");
			System.out.println("----------------------------------------------------\n");

			List<UserDTO> users = (List<UserDTO>) response.getBody().get("users");
			for (UserDTO u: users)
				System.out.println(u);
			System.out.println();
		}
	}

	/**
	 * Chiede all'utente un input (lettera da tastiera) per la choice (vedi UserController). 
	 * Mette la modalit� GETCHOICE nella mode.
	 */
	@Override
	public void showOptions() {
		System.out.println("          Scegli l'operazione da effettuare:");
		System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");

		this.choice = getInput();

		
	}
	
	/**
	 * Impacchetta la request e la manda allo UserController.
	 */
	@Override
	public void submit() {
		switch (choice.toUpperCase()) {
			case "L":
				MainDispatcher.getInstance().callView(PackageEnum.USER_PACKAGE.getPackageName() + "UserRead", null);
				break;
			case "I":
				MainDispatcher.getInstance().callView(PackageEnum.USER_PACKAGE.getPackageName() + "UserInsert", null);
				break;
			case "M":
				MainDispatcher.getInstance().callView(PackageEnum.USER_PACKAGE.getPackageName() + "UserUpdate", null);
				break;
			case "C":
				MainDispatcher.getInstance().callView(PackageEnum.USER_PACKAGE.getPackageName() + "UserDelete", null);
				break;
			case "B":
				MainDispatcher.getInstance().callView("HomeAdmin", null);
				break;
			case "E":
				MainDispatcher.getInstance().callView("Login", null);
				break;
			default:
				System.out.println("\nNessuna scelta valida");
				Request request = new Request("User", "getAll", null);
				MainDispatcher.getInstance().callAction(request);
		}
	}

}
