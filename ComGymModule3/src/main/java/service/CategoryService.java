package service;

import model.Category;

import java.util.List;

public interface CategoryService<Category> {
    public List<Category> selectAllCategory();
}
