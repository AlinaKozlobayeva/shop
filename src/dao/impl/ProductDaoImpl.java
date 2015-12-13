package dao.impl;

import dao.GenericDao;
import dao.ProductDao;
import entity.Product;
import entity.ProductFields;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Alina on 04.11.2015.
 */
public class ProductDaoImpl implements ProductDao {

    private final String URL;
    private final String DB_PROPERTIES = "dao/db_prop.txt";

    private final String SQL_SELECT_PRODUCT_BY_NAME = "SELECT * FROM products WHERE " + ProductFields.PRODUCT_NAME + "=?";
    private final String SQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE" + ProductFields.PRODUCT_ID + "=?";
    private final String SQL_SELECT_ALL_PRODUCTS = "SELECT * FROM products";
    private final String SQL_INSERT_PRODUCT = "INSERT INTO products VALUES (DEFAULT,?,?,?)";

    public ProductDaoImpl() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Properties props = new Properties();

        // this way we obtain db.properties file from the CLASSPATH
        InputStream in;
        try {
            in = getClass().getClassLoader().getResourceAsStream(DB_PROPERTIES);
            if (in == null) {
                throw new IllegalStateException(
                        "File must be resides in classpath: " + DB_PROPERTIES);
            }
            props.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        URL = props.getProperty("connection.url");
        if (URL == null) {
            throw new IllegalStateException(
                    "You have to define connection.url property in " + DB_PROPERTIES);
        }
    }

    @Override
    public Product findProductByName(String productName) {
        Connection con = null;
        Product res = null;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_SELECT_PRODUCT_BY_NAME);
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                res = extractProduct(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                close(con);
            }
        }

        return res;

    }

    @Override
    public Product findById(int id) {
        Connection con = null;
        Product res = null;
        int k = 1;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_SELECT_PRODUCT_BY_ID);
            pstmt.setInt(k, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                res = extractProduct(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                close(con);
            }
        }

        return res;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_SELECT_ALL_PRODUCTS);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
               products.add(extractProduct(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean create(Product product) {
        boolean res = false;
        Connection con = null;
        int k = 1;
        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(k++, product.getProductName());
            pstmt.setDouble(k++, product.getProductPrice());
            pstmt.setString(k++, product.getProductView());

            int count = pstmt.executeUpdate();
            if (count > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                res = true;
                if (rs.next()) {
                    product.setProductId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Cannot insert the product: " + product);
            e.printStackTrace();
        } finally {
            if (con != null) {
                close(con);
            }
        }
        return res;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public boolean delete(String value) {
        return false;
    }

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
        Connection con = DriverManager.getConnection(URL);
        return con;
    }

    private Product extractProduct(ResultSet rs) throws SQLException{
       Product product = new Product(rs.getString(ProductFields.PRODUCT_NAME), rs.getDouble(ProductFields.PRODUCT_PRICE), rs.getString(ProductFields.PRODUCT_VIEW));
       product.setProductId(rs.getInt(ProductFields.PRODUCT_ID));
        return product;
    }

    private void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot close a connection");
            ex.printStackTrace();
        }
    }

}
