package Controller;

import Model.Products;
import Util.ConnectionFactory;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsController {

    public void created(Products products) {
        String sql = "INSERT INTO projects (name," +
                "price, " +
                "quantity) " +
                "VALUES (?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.connectDB();
            statement = connection.prepareStatement(sql);
            statement.setString(1, products.getName());
            statement.setDouble(2, products.getPrice());
            statement.setInt(3, products.getQuantity());
            statement.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar o produto " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Products products) {
        String sql = "UPDATE products SET (name = ?," +
                "price = ?, " +
                "quantity = ?) " +
                "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.connectDB();
            statement.setString(1, products.getName());
            statement.setDouble(2, products.getPrice());
            statement.setInt(3, products.getQuantity());
            statement.setInt(4, products.getId());
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o produto " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Products> getAll() {
        String sql = "SELECT * FROM products ";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Products> products1 = new ArrayList<>();

        try {
            connection = ConnectionFactory.connectDB();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Products products2 = new Products();

                products2.setId(resultSet.getInt("id"));
                products2.setName(resultSet.getString("name"));
                products2.setPrice(resultSet.getDouble("price"));
                products2.setQuantity(resultSet.getInt("quantity"));

                products1.add(products2);

            }
        } catch (SQLException e) {
            System.out.println("Não foi possivel retornar todos "+e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return products1;
    }
}
