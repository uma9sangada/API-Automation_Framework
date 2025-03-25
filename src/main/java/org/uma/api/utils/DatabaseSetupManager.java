package org.uma.api.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetupManager {

    public static void main(String[] args) {
        String host = "localhost"; // Corrected host
        String port = "3306";
        String databaseName = "sakila";
        String username = "root";
        String password = "Um@11Q22021";

        Connection con = null;
        Statement st = null;
        ResultSet rs1 = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName, username, password);
            st = con.createStatement();

            String updateQuery = "UPDATE actor SET first_name = 'umas' WHERE actor_id = 1";
            int rowsAffected = st.executeUpdate(updateQuery);

            if (rowsAffected > 0) {
                System.out.println("Update successful. Rows affected: " + rowsAffected);
            } else {
                System.out.println("No rows updated.");
            }

            String selectQuery = "SELECT last_update FROM actor WHERE actor_id = 1";
            rs1 = st.executeQuery(selectQuery);

            while (rs1.next()) {
                System.out.println(rs1.getString("last_update"));
            }

            System.out.println("Database operations completed successfully.");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace(); // Print the full stack trace for debugging
        } finally {
            // Close resources in a finally block to ensure they are closed even if an exception occurs
            try {
                if (rs1 != null) {
                    rs1.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println("Program finished.");
    }
}
