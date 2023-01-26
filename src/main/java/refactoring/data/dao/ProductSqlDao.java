package refactoring.data.dao;

import refactoring.data.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.DriverManager.getConnection;

public class ProductSqlDao implements ProductDao {
    private static final String DB_URL = "jdbc:sqlite:test.db";

    private List<Product> parseProducts(ResultSet rs) throws SQLException {
        List<Product> result = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("name");
            int price = rs.getInt("price");
            result.add(new Product(name, price));
        }
        return result;
    }

    @Override
    public void insert(Product product) throws SQLException {
        try (Statement stmt = getConnection(DB_URL).createStatement()) {
            String sql = String.format(
                    "INSERT INTO PRODUCT (NAME, PRICE) VALUES (\"%s\",%d)",
                    product.getName(), product.getPrice());
            stmt.executeUpdate(sql);
        }
    }

    @Override
    public List<Product> getProducts() throws SQLException {
        try (Statement stmt = getConnection(DB_URL).createStatement()) {
            String sql = "SELECT * FROM PRODUCT";
            ResultSet rs = stmt.executeQuery(sql);
            return parseProducts(rs);
        }
    }

    @Override
    public Optional<Product> maxPriceProduct() throws SQLException {
        try (Statement stmt = getConnection(DB_URL).createStatement()) {
            String sql = "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            return parseProducts(rs).stream().findFirst();
        }
    }

    @Override
    public Optional<Product> minPriceProduct() throws SQLException {
        try (Statement stmt = getConnection(DB_URL).createStatement()) {
            String sql = "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            return parseProducts(rs).stream().findFirst();
        }
    }

    @Override
    public long sumPriceProducts() throws SQLException {
        try (Statement stmt = getConnection(DB_URL).createStatement()) {
            String sql = "SELECT SUM(price) as sum FROM PRODUCT";
            ResultSet rs = stmt.executeQuery(sql);
            return rs.getLong("sum");
        }
    }

    @Override
    public int getProductsCount() throws SQLException {
        try (Statement stmt = getConnection(DB_URL).createStatement()) {
            String sql = "SELECT COUNT(*) as cnt FROM PRODUCT";
            ResultSet rs = stmt.executeQuery(sql);
            return rs.getInt("cnt");
        }
    }
}
