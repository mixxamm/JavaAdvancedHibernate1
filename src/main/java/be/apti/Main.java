package be.apti;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3306/toezichten_mc";
    static final String USER = "admin";
    static final String PASS = "admin";
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hallo");
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            System.out.println("Verbinding geslaagd");
            System.out.print("Geef lector naam: ");
            String lector = scanner.nextLine();
            System.out.println("Gekozen lector: " + lector);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM lecturers WHERE name LIKE ?");
            statement.setString(1, lector);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println("ID: " + resultSet.getString("id") + " Naam: " + resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
