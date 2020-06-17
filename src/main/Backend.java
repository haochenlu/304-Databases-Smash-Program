package main;

import java.sql.*;
import java.util.ArrayList;

public class Backend {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";

    private Connection connection = null;
    private static final String EXCEPTION_TAG = "[EXCEPTION]";

    public Backend() {
        try {
            // Load the Oracle JDBC driver
            // Note that the path could change for new drivers
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    public String query(String query) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String ret = "";
            while(rs.next()) {
                ret = ret + rs.getString("sgID") + "\n";
            }
            rs.close();
            stmt.close();
            return ret;
        } catch(Exception e) {
            return e.getMessage();
        }
    }

    public void load(String query) {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        try {
            stmt.close();
        } catch (SQLException e) {
            System.out.println("could not close");;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }
}
