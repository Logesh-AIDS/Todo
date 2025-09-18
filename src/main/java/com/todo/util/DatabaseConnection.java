package com.todo.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/todo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static final String USERNAME="root";
    public static final String PASSWORD="logeshaastha01";
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println("Error: Unable to load database driver.");
        }
    }
    public  Connection getDBConnection() throws SQLException{
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }
}
