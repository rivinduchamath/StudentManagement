package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
    private static Connection connection;
    public static Connection getConnection() throws Exception{

        if (null==connection){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagement?" +
                            "createDatabaseIfNotExist=true&allowMultiQueries=true",
                    "root",
                    "1234");
            PreparedStatement pstm = connection.prepareStatement("SHOW TABLES");
            ResultSet resultSet = pstm.executeQuery();
            if (!resultSet.next()){
                String sql = "create table student(\n" +
                        " id varchar(19)primary key,\n" +
                        "name varchar(30),\n" +
                        " address varchar(30),\n" +
                        "telephone int(10)\n" +
                        ");\n" +
                        "create table timeTable(\n" +
                        " id varchar(19)primary key,\n" +
                        " subject varchar(30),\n" +
                        " lecturer varchar(30)\n" +
                        ");\n" +
                        "\n" +
                        "\n" +
                        "\n";
                pstm = connection.prepareStatement(sql);
                pstm.execute();
            }
        }
        return connection;
    }

}