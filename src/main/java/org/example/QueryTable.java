package org.example;

import java.sql.*;

public class QueryTable {

    public static void main(String[] args) throws Exception {

        //Let's Connect to our database
        String RDS_INSTANCE_HOSTNAME = "database-1.ci7sqfgsr8ao.us-east-2.rds.amazonaws.com";
        String RDS_INSTANCE_PORT = "5432";

        String JDBC_URL = "jdbc:postgresql://" + RDS_INSTANCE_HOSTNAME + ":" + RDS_INSTANCE_PORT + "/";

        try {

            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    JDBC_URL, "postgres", "admin123");
            Statement stmt= connection.createStatement();
            ResultSet rs=stmt.executeQuery("SHOW wal_level");
            while (rs.next()) {
                String id = rs.getString(1);
                System.out.println(id); //Should print "X"
            }

            //close the connection
            stmt.close();
            connection.close();
        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}}
