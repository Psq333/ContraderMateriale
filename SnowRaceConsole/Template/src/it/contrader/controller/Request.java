package it.contrader.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a request in the application. It encapsulates all the details of a request,
 * including the controller name, the method to be invoked, and any parameters needed for the method.
 */
public class Request {

	private String controller;
	private String method;
	private Map<String, Object> body;

	/**
	 * Default constructor that initializes an empty body for the request.
	 */
	public Request() {
		this.body = new HashMap<>();
	}

	/**
	 * Constructs a request with the given controller name, method name and body.
	 *
	 * @param controller the name of the controller to handle the request.
	 * @param method     the name of the method to be invoked.
	 * @param body       the parameters needed for the method.
	 */
	public Request(String controller, String method, Map<String, Object> body) {
		this.controller = controller;
		this.method = method;
		this.body = body;
	}

	/**
	 * Returns the name of the controller.
	 *
	 * @return the name of the controller.
	 */
	public String getController() {
		return controller;
	}

	/**
	 * Sets the name of the controller.
	 *
	 * @param controller the name of the controller.
	 */
	public void setController(String controller) {
		this.controller = controller;
	}

	/**
	 * Returns the name of the method to be invoked.
	 *
	 * @return the name of the method.
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Sets the name of the method to be invoked.
	 *
	 * @param method the name of the method.
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Returns the body of the request which contains the parameters needed for the method.
	 *
	 * @return the body of the request.
	 */
	public Map<String, Object> getBody() {
		return body;
	}

	/**
	 * Sets the body of the request.
	 *
	 * @param body the body of the request.
	 */
	public void setBody(Map<String, Object> body) {
		this.body = body;
	}
}

