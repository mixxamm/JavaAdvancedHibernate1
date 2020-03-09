package be.apti;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3306/toezichten_mc";
    static final String USER = "admin";
    static final String PASS = "admin";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)){
            System.out.println("Verbinding geslaagd");
            System.out.print("Geef lector naam: ");
            String lector = scanner.nextLine();
            System.out.println("Gekozen lector: " + lector);
            PreparedStatement statement = conn.prepareStatement("select * from reservations where lecturer_id like (select id from lecturers where name like ?)");
            statement.setString(1, lector);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Reservaties voor " + lector);
            while(resultSet.next()){
                System.out.println("ID: " + resultSet.getString("id") + " Slot id: " + resultSet.getString("slot_id") + " Gecreëerd op: " + resultSet.getString("created_at") + " Gewijzigd op: " + resultSet.getString("updated_at"));
                PreparedStatement statement1 = conn.prepareStatement("select * from slots where id = ?");
                statement1.setString(1, resultSet.getString("slot_id"));
                ResultSet resultSet1 = statement1.executeQuery();
                resultSet1.next();
                System.out.println("Datum: " + resultSet1.getString("date") + " Start: " + resultSet1.getString("start") + " Einde: " + resultSet1.getString("end") + " Duur: " + resultSet1.getString("duration") + " Capaciteit: " + resultSet1.getString("capacity"));
                System.out.println("---------------------------------------------------------------------------------------------------------\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
