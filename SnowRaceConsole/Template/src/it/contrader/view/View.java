package it.contrader.view;

import it.contrader.controller.Response;

/**
 * Interface for all View classes.
 * It's important to note that View classes NEVER directly invoke controller methods.
 * Instead, they communicate through requests transferred via the MainDispatcher.
 * All classes implementing this interface must provide implementations for the following methods:
 * - showResults: to display the results to the user
 * - showOptions: to show the available options to the user
 * - submit: to submit user input or selections
 */
public interface View {

	/**
	 * Displays the results of a previous operation to the user.
	 *
	 * @param response The results of a previous operation.
	 */
	void showResults(Response response);

	/**
	 * Presents the available options to the user.
	 */
	void showOptions();

	/**
	 * Submits the user's input or selection for further processing.
	 */
	void submit();
}
