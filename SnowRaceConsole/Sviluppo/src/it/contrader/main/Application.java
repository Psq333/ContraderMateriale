package it.contrader.main;

import it.contrader.controller.Request;
import it.contrader.dao.PrenotazioniDAO;
import it.contrader.dto.PrenotazioniDTO;
import it.contrader.utilities.userSpecs;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/*
 * Ogni applicazione java necessita di una funzione main() 
 * Essa sar� di fatto la prima funzione chiamata dalla JVM, la sua mancanza verr� infatti notata dall'interprete che 
 * quindi non permetter� l'avvio del programma.
 * Banalmente qualsiasi applicazione a cascata ( stile C ) potrebbe essere interamente scritta all'interno del main
 * ma questo non avrebbe senso all'interno di un linguaggio di programmazione ad oggetti.
 * Per ulteriori dettagli GUIDA SEZIONE 1.
*/
public class Application {

    public static void main(String[] args) throws ParseException {
        MainDispatcher.getInstance().callView("Home", null);


    }

}
