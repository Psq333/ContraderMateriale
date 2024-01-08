package it.contrader.main;


import it.contrader.controller.Controller;
import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.view.View;


/**
 * This class is a singleton class that controls the main workflow of the application.
 * It utilizes the concept of reflection to call appropriate controllers and views
 * based on the provided Request object or the specified view name.
 */
public class MainDispatcher {

	/**
	 * A private constructor to prevent instantiation of the class.
	 */
	private MainDispatcher() {
	}

	/**
	 * A static instance of the MainDispatcher class.
	 */
	private static MainDispatcher instance;

	/**
	 * This method returns a singleton instance of MainDispatcher.
	 * If the instance is null, it creates a new instance of MainDispatcher.
	 *
	 * @return instance of MainDispatcher.
	 */
	public static MainDispatcher getInstance() {
		if (instance == null) {
			instance = new MainDispatcher();
		}
		return instance;
	}

	/**
	 * This method calls the appropriate controller's action based on the
	 * information provided in the Request object.
	 *
	 * @param request Request object containing details about the controller
	 *                and method to be invoked.
	 * @throws RuntimeException if the specified controller is not found.
	 */
	public void callAction(Request request) {
		Controller oggettoController = (Controller) ReflectionUtils
				.instantiateClass("it.contrader.controller." + request.getController() + "Controller");
		try {
			if(oggettoController != null) {
				oggettoController.doControl(request);
			} else throw new RuntimeException("404 controller " + request.getController() + " non trovato");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method calls the appropriate view based on the specified view name.
	 * It then triggers the display of results, options, and submission of the view.
	 *
	 * @param view     Name of the view to be called.
	 * @param response Response object containing data to be displayed in the view.
	 * @throws RuntimeException if the specified view is not found.
	 */
	public void callView(String view, Response response) {
		View oggettoView = (View) ReflectionUtils.instantiateClass("it.contrader.view." + view + "View");
		if(oggettoView != null) {
			oggettoView.showResults(response);
			oggettoView.showOptions();
			oggettoView.submit();
		} else throw new RuntimeException("404 pagina " + view + " non trovata");
	}
}

