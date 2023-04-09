package Util;

import java.sql.*;

public class ConnectionFactory {

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
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    public static void closeConnection(Connection connection, PreparedStatement statement){
        try {
            if (connection != null){
                connection.close();
            }   if (statement != null){
                statement.close();
            }
        }catch (SQLException error){
            System.out.println("Erro ao fechar a conexão: " + error.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet){
        try {
            if (connection != null){
                connection.close();
            }   if (statement != null){
                statement.close();
            }   if (resultSet != null){
                resultSet.close();
            }
        }catch (SQLException error){
            System.out.println("Erro ao fechar a conexão: " + error.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
    }
}
