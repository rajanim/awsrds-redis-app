package org.example;


        import org.apache.ibatis.jdbc.ScriptRunner;

        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.Reader;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;

public class TestConnection {

    public static void main(String[] args) throws Exception {

        //Let's Connect to our database
        String RDS_INSTANCE_HOSTNAME = "database-1.ci7sqfgsr8ao.us-east-2.rds.amazonaws.com";
        String RDS_INSTANCE_PORT = "5432";

            String JDBC_URL = "jdbc:postgresql://" + RDS_INSTANCE_HOSTNAME + ":" + RDS_INSTANCE_PORT + "/Chinook";

        try {

            Class.forName("org.postgresql.Driver");
            Connection connection=DriverManager.getConnection(
                    JDBC_URL,"postgres","admin123");

            //verify the connection is successful
            Statement stmt= connection.createStatement();
            ScriptRunner sr = new ScriptRunner(connection);
            Reader reader = new BufferedReader(new FileReader("/Users/rajanimaski/datasets/chinook" +
                    "/Chinook_PostgreSql.sql"));
            //Running the script
            sr.runScript(reader);
            sr.setAutoCommit(true);

            ResultSet rs=stmt.executeQuery("SELECT table_name FROM information_schema.tables\n" +
                    " WHERE table_schema='public' ");
            while (rs.next()) {
                String id = rs.getString(1);
                System.out.println(id); //Should print "X"
            }
            System.out.println("table");
            ResultSet rs1=stmt.executeQuery("SELECT * FROM \"Album\";");
            while (rs1.next()){
                String id = rs1.getString("Title");
                System.out.println(id); //Should print "X"
            }


            ResultSet rs2=stmt.executeQuery("SELECT *\n" +
                    "FROM pg_catalog.pg_tables\n" +
                    "WHERE schemaname != 'pg_catalog' AND \n" +
                    "    schemaname != 'information_schema';");
            while (rs2.next()){
                String id = rs2.getString(3);
                System.out.println(id); //Should print "X"
            }


            //close the connection
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

    }

}