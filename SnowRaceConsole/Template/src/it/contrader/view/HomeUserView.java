package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;

public class HomeUserView extends AbstractView{

	String choice;

	@Override
	public void showResults(Response response) {
		if(response!=null) {
			System.out.println("\n Benvenuto in SAMPLE PROJECT " + response.getBody().get("username").toString() + "\n");
		}
	}

	@Override
	public void showOptions() {
		System.out.println("-------------MENU------------\n");
		System.out.println("Buona implementazione ;)");

	}

	@Override
	public void submit() {

	}

}
