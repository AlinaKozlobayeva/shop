package dao;

import dao.impl.ProductDaoImpl;
import entity.Product;

import java.util.List;

/**
 * Created by Alina on 04.11.2015.
 */
public interface ProductDao extends GenericDao <String, Product> {

    public Product findProductByName(String productName);

    @Override
    Product findById(int id);

    @Override
    List<Product> findAll();

    @Override
    boolean create(Product product);

    @Override
    boolean update(Product product);

    @Override
    boolean delete(String value);
}
