package it.contrader.enums;

public enum Usertype {
    ADMIN, USER, AMMINISTRATORE;
    public static String getName(Usertype userType) {
        return userType.name().toLowerCase();
    }
}
