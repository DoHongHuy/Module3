package service;

import model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void create(Product product);

    void update(Product product);

    boolean remove(int id);

    Product findById(int id);

    boolean existByid(int id);

    public List<Product> searchByKey(String key);
}
