package Util;


import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnetionFactory {

    public static Connection connectDB(){
        Connection conn = null;

        try{
            String url = "jdbc:mysql://localhost:3306/listproduct";
            conn = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        }catch (SQLException error){
            System.out.println("Erro ao fechar a conexão: " + error.getMessage());
        }

    }
}
