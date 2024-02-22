package it.contrader.authenticationservice.customException;

import it.contrader.authenticationservice.model.User;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(){}
}
