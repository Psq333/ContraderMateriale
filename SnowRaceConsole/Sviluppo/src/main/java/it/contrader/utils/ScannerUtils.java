package it.contrader.utils;
import java.text.SimpleDateFormat;
import java.sql.Date;

import java.util.Scanner;
public class ScannerUtils {
    private int numIn;
    private double doubIn;
    private Date dateIn;

    private ScannerUtils() {
    }

    private static ScannerUtils instance;

    public static ScannerUtils getInstance() {
        if (instance == null) {
            instance = new ScannerUtils();
        }
        return instance;
    }

    public int setInt() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        try {
            int num = Integer.parseInt(input);
            this.numIn = num;
        } catch (NumberFormatException e) {
            System.out.println("Input non valido. Inserisci un numero intero.");
        }
        return numIn;
    }

    public Double setDouble() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        try {
            double num = Double.parseDouble(input);
            this.doubIn = num;
        } catch (NumberFormatException e) {
            System.out.println("Input non valido. Inserisci un valore valido.");
            setDouble();
        }
        return doubIn;
    }

    public Date setDate() {
        Scanner scanner = new Scanner(System.in);
        String inputDate = scanner.next();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            java.util.Date utilDate = sdf.parse(inputDate);

            long millisecondiDaEpoch = utilDate.getTime();

            dateIn = new Date(millisecondiDaEpoch);
        } catch (Exception e) {
            System.out.println("Formato data non valido. Riprova");
            setDate();
        }
        return dateIn;

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


