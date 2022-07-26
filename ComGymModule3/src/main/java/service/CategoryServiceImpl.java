package service;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static utils.MySQLConnUtils.getConnection;
import static utils.MySQLConnUtils.printSQLException;

public class CategoryServiceImpl implements CategoryService {
    private  static  String SELECT_ALL_CATEGORY = "SELECT * FROM category;";
    @Override
    public List selectAllCategory() {
        List<Category> listCategory = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                listCategory.add(new Category(id, name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listCategory;

    }
}
