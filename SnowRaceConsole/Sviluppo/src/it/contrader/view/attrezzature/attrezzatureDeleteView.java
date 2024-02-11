package it.contrader.view.attrezzature;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.utilities.userSpecs;
import it.contrader.view.AbstractView;

    public class attrezzatureDeleteView extends AbstractView {

        private Request request;

        private final String mode = "DELETE";
        private int idAttrezzature;

        public attrezzatureDeleteView(){

        }

        @Override
        public void showResults(Response response) {
            if(response != null){
                if((userSpecs.getInstance().getUsertype().equals("ADMIN") && (boolean)response.getBody().get("boolean"))) {
                    System.out.println("Cancellazione Attrezzatura andata a buon fine. \n");
                } else if(userSpecs.getInstance().getUsertype().equals("AMMINISTRATORE")){
                    System.out.println("Cancellazione Attrezzatura andata a buon fine. \n");
                }
                request = new Request("Attrezzature", "getAll", null);
                MainDispatcher.getInstance().callAction(request);
            }
        }

        @Override
        public void showOptions() {
            System.out.println("Inserisci id dell'attrezzatura:");
            idAttrezzature = Integer.parseInt(getInput());
        }

        @Override
        public void submit() {
            request = new Request();
            request.getBody().put("idAttrezzature", idAttrezzature);
            request.setController("Attrezzature");
            request.setMethod("delete");
            MainDispatcher.getInstance().callAction(request);
        }
    }

