package it.contrader.utilities;
import java.util.HashMap;
import java.util.Map;


public class userTypeMap {

    private final Map<String, String> userType = new HashMap<>();
    private static userTypeMap instance;

    private userTypeMap() {
        userType.put("A", "ADMIN");
        userType.put("U", "USER");
        userType.put("S", "AMMINISTRATORE");
    }

    public static userTypeMap getInstance() {
        if (instance == null) {
            instance = new userTypeMap();
        }
        return instance;
    }

    public String getUserType(String choice) {
        return userType.get(choice);
    }

}