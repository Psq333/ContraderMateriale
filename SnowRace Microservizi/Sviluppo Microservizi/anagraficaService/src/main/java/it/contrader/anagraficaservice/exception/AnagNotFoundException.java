package it.contrader.anagraficaservice.exception;

public class AnagNotFoundException extends RuntimeException{

    public AnagNotFoundException(String message){
        super(message);
    }

    public AnagNotFoundException(){}
}
