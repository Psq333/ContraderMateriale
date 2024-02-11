package it.contrader.view.noleggio;

import it.contrader.controller.Request;
import it.contrader.controller.Response;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class NoleggioCRUDView extends AbstractView {

        private String choice;
        public NoleggioCRUDView(){

        }

        public void showResults(Response response){
        }

        @Override
        public void showOptions() {
            System.out.println("          Scegli l'operazione da effettuare:");
            System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");

            this.choice = getInput();

        }

        @Override
        public void submit() {
            switch (choice.toUpperCase()){
                case "L":
                    MainDispatcher.getInstance().callView("Noleggio.NoleggioRead",null);
                    break;
                case "I":
                    MainDispatcher.getInstance().callView("Noleggio.NoleggioInsert",null);
                    break;
                case "M":
                    MainDispatcher.getInstance().callView("Noleggio.NoleggioUpdate",null);
                    break;
                case "C":
                    MainDispatcher.getInstance().callView("Noleggio.NoleggioDelete",null);
                    break;
                case "B":
                    MainDispatcher.getInstance().callView("HomeAdmin",null);
                    break;
                case "E":
                    MainDispatcher.getInstance().callView("Login",null);
                    break;
                default:
                    System.out.println("\nNessuna scelta valida");
                    Request request = new Request("Noleggio", "getAll", null);
                    MainDispatcher.getInstance().callAction(request);
                    break;
            }
        }
    }


