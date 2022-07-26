package service;

import model.Product;
import utils.MySQLConnUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utils.MySQLConnUtils.getConnection;
import static utils.MySQLConnUtils.printSQLException;

public class ProductServiceImpl implements ProductService{

    private static final String DELETE_PRODUCT_SQL = "delete from product where id = ?;";

    private static final String  SEARCH_BY_KEY_PRODUCT = "SELECT * " + "FROM product " +
            "WHERE `id` LIKE ? OR name LIKE ? OR price LIKE ? " +
            "OR quantity LIKE ?  OR color LIKE ? " +
            "OR `describe` LIKE ?  OR  idCategory like ? ;";



    private static final String SELECT_USER_BY_ID = "select id,name,price,quantity,color,`describe`,idCategory from product where id =?";

    private static final String SELECT_ALL_USERS = "select * from product";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product" +
            "  (`name`, price, quantity, color,`describe` ,idCategory ) VALUES " +
            " (?, ?, ?, ? , ? , ?);";
    private static final String UPDATE_PRODUCT_SQL = "update product set name = ? , price= ? , " +
            " quantity = ? , color = ? , `describe` = ? , idCategory =  ? where id = ? ;";
    @Override
    public List findAll() {

        List<Product> productList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String describe = rs.getString("describe");
                int idCategory = rs.getInt("idCategory");
                productList.add(new Product(id, name, price, quantity,color,describe, idCategory));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productList;
    }

    @Override
    public void create(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescribe());
            preparedStatement.setInt(6, product.getIdCategory());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
    @Override
    public void update(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescribe());
            preparedStatement.setInt(6, product.getIdCategory());
            preparedStatement.setInt(7, product.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String describe = rs.getString("describe");
                int idCategory = rs.getInt("idCategory");

                int idA = Integer.parseInt(rs.getString("id"));
                 product = new Product(id, name, price, quantity, color ,describe , idCategory);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }


    @Override
    public boolean existByid(int id) {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            rowDeleted = false;
        }
        return rowDeleted;
    }

    @Override
    public List searchByKey(String key) {
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            CallableStatement statement = connection.prepareCall(SEARCH_BY_KEY_PRODUCT);
            statement.setString(1, '%' + key + '%');
            statement.setString(2, '%' + key + '%');
            statement.setString(3, '%' + key + '%');
            statement.setString(4, '%' + key + '%');
            statement.setString(5, '%' + key + '%');
            statement.setString(6, '%' + key + '%' );
            statement.setString(7, '%' + key + '%' );
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String describe = rs.getString("describe");
                int idCategory = rs.getInt("idCategory");
                productList.add(new Product(id ,name,price,quantity,color ,describe, idCategory));
            }
        }
        catch (SQLException e) {
            printSQLException(e);
        }
        return productList;
    }
}
