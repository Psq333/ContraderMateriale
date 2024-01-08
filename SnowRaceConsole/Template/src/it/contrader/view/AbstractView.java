package it.contrader.view;

import java.util.Scanner;

public abstract class AbstractView implements View {
	public String getInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
}
