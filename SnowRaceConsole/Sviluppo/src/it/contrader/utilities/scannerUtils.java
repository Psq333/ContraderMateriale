package it.contrader.utilities;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;
import java.text.SimpleDateFormat;
import java.sql.Date;

import java.util.Scanner;
public class scannerUtils {
    private int numIn;
    private double doubIn;
    private Date dateIn;

    private scannerUtils() {
    }

    private static scannerUtils instance;

    public static scannerUtils getInstance() {
        if (instance == null) {
            instance = new scannerUtils();
        }
        return instance;
    }

    public void askInt() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        try {
            int num = Integer.parseInt(input);
            this.numIn = num;
        } catch (NumberFormatException e) {
            System.out.println("Input non valido. Inserisci un numero intero.");
        }
    }

    public void askDouble() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        try {
            double num = Double.parseDouble(input);
            this.doubIn = num;
        } catch (NumberFormatException e) {
            System.out.println("Input non valido. Inserisci un valore valido.");
        }
    }

    public void askDate() {
        Scanner scanner = new Scanner(System.in);
        String inputDate = scanner.next();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            java.util.Date utilDate = sdf.parse(inputDate);

            long millisecondiDaEpoch = utilDate.getTime();

            dateIn = new Date(millisecondiDaEpoch);
        } catch (Exception e) {
            System.out.println("Formato data non valido. Riprova");
            askDate();
        }


    }

    public int getNumIn() {
        return numIn;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public Double getDouble(){
        return doubIn;
    }

    public void empty(){
        instance = null;

    }
}


