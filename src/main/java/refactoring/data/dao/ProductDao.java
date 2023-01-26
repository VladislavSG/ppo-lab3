package refactoring.data.dao;

import refactoring.data.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    void insert(Product product) throws SQLException;

    List<Product> getProducts() throws SQLException;

    Optional<Product> maxPriceProduct() throws SQLException;

    Optional<Product> minPriceProduct() throws SQLException;

    long sumPriceProducts() throws SQLException;

    int getProductsCount() throws SQLException;
}