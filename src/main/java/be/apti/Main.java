package be.apti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3306";
    static final String USER = "admin";
    static final String PASS = "admin";
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Hallo");
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            System.out.println("Verbinding geslaagd");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
