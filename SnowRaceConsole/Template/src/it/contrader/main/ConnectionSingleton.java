package it.contrader.main;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * A singleton class for managing the database connection throughout the application.
 * The instance of Connection will be the same for the entire course of the program.
 */
public class ConnectionSingleton {

    /**
     * The singleton instance of the database connection.
     */
    private static Connection connection = null;

    /**
     * A private constructor to prevent instantiation of the class.
     */
    private ConnectionSingleton() {
    }

    /**
     * This method provides a singleton instance of a database connection.
     * If the connection has not been established before, it will create a new one.
     * The properties for the connection are loaded from a config.properties file.
     *
     * @return The singleton instance of a database connection.
     * @throws Exception if there is any issue with loading the properties or establishing the connection.
     */
    public static Connection getInstance() {
        if (connection == null) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = Files.newInputStream(Paths.get("config.properties"));
                properties.load(inputStream);
                String vendor = properties.getProperty("db.vendor");
                String driver = properties.getProperty("db.driver");
                String host = properties.getProperty("db.host");
                String port = properties.getProperty("db.port");
                String dbName = properties.getProperty("db.name");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                String jdbcAdditionalParams = properties.getProperty("db.jdbc_params");

                Class.forName(driver);
                String url = "jdbc:" + vendor + "://" + host + ":" + port + "/" + dbName + "?" + jdbcAdditionalParams;
                connection = DriverManager.getConnection(url, username, password);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

