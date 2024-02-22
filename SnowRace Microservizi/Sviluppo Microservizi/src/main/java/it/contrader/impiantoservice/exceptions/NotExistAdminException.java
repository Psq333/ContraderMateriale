package it.contrader.impiantoservice.exceptions;

public class NotExistAdminException extends RuntimeException{
    public NotExistAdminException(String message){
        super(message);
    }
}
