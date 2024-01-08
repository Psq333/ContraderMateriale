package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;

/**
 * A representation of the Admin's Home page.
 * Extends AbstractView and contains methods to display results, show options, and submit user inputs.
 */
public class HomeAdminView extends AbstractView {

    private String choice;

    /**
     * Displays a welcome message if the response object is not null.
     *
     * @param response The results of a previous operation.
     */
    public void showResults(Response response) {
        if (response != null) {
            System.out.println("\n Benvenuto in SAMPLE PROJECT " + response.getBody().get("username").toString() + "\n");
        }
    }

    /**
     * Presents the available options to the user through console.
     * User can choose to manage Users ('U') or Exit ('E').
     */
    public void showOptions() {
        System.out.println("-------------MENU------------\n");
        System.out.println(" Seleziona cosa vuoi gestire:");
        System.out.println("[U]tenti  [E]sci");
        // The method saves the user's input into the 'choice' variable.
        // getInput() is defined in AbstractView.
        choice = this.getInput();
    }

    /**
     * Based on user's choice, it creates and sends a request to appropriate controller via the Dispatcher.
     * If an invalid option is chosen, it prompts the user to choose again.
     */
    public void submit() {
        Request request = new Request();
        switch (choice.toUpperCase()) {
            case "U":
                request.setController("User");
                request.setMethod("getAll");
                MainDispatcher.getInstance().callAction(request);
                break;
            case "E":
                MainDispatcher.getInstance().callView("Login", null);
                break;
            default:
                System.out.println("Azione non disponibile");
                showOptions();
        }
    }
}
