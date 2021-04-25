package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    //static method manipulates static attributes
    private static DataSource instance;
    private Connection cnx;
    //constantes (final)
    private final String USERNAME ="root";
    private final String PASSWORD ="";
    private final String URL ="jdbc:mysql://localhost:3306/travelagency";

    //private : only 1 instance datasource (conflict values canal de communication)
    private DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to DB");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //static method returns unique instance Datasource
    public static  DataSource getInstance(){
        if (instance == null)
            instance = new DataSource();
        return instance;
    }
    public Connection getCnx() {
        return cnx;
    }

}
