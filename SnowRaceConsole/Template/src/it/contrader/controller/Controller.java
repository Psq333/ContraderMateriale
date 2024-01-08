package it.contrader.controller;

/**
 * This interface represents a Controller in the application. It serves as a contract for all concrete
 * Controller classes, ensuring they implement the doControl method.
 * The purpose of the Controller is to handle the application logic in response to Requests received
 * from the View via the MainDispatcher.
 */
public interface Controller {

	/**
	 * Method responsible for controlling the flow of the application based on the provided Request.
	 * All concrete Controller classes should provide an implementation of this method.
	 *
	 * @param request Request object encapsulating the details of the request made by the user
	 *                from the View.
	 */
	public void doControl(Request request);
}

