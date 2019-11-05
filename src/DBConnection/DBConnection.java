package DBConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {

    private static DBConnection dBconnection;
    private Connection connection;


    private DBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagemen?createDatabaseIfNotExist=true&allowMultiQueries=true","root","1234");
            PreparedStatement show_tables = connection.prepareStatement("SHOW TABLES");
            ResultSet execute = show_tables.executeQuery();
            if(!execute.next()){
                PreparedStatement createTable =
                        connection.prepareStatement("create table student(\n" +
                        " id varchar(19)primary key,\n" +
                        "name varchar(30),\n" +
                        " address varchar(30),\n" +
                        "telephone int(10)\n" +
                        ");\n" +
                        "create table timeTable(\n" +
                        " id varchar(19)primary key,\n" +
                        " subject varchar(30),\n" +
                        " lecturer varchar(30)\n" +
                        ");\n");

                createTable.execute();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static DBConnection getInstance(){
        return (dBconnection==null)?(dBconnection=new DBConnection()):dBconnection;
    }

    public Connection getConnection(){return connection;}

}