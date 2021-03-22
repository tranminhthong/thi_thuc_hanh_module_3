package Model.Service;

import Model.Entities.Category;
import Model.Entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/thi_module3";
    private String jdbcUsername = "root";
    private String jdbcPassword = "blue123@";

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void addProduct(Product product) {
        String sql = "{call insert_product(?,?,?,?,?,?)}";
        try (Connection connection = getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, product.getName());
            cs.setLong(2, product.getPrice());
            cs.setInt(3, product.getQuantity());
            cs.setString(4, product.getColor());
            cs.setInt(5, product.getCategory().getId());
            cs.setString(6,product.getDes());
            cs.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editProduct(Product product) {
        String sql = "{call edit(?,?,?,?,?,?,?)}";
        try (Connection connection = getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, product.getName());
            cs.setLong(2, product.getPrice());
            cs.setInt(3, product.getQuantity());
            cs.setString(4, product.getColor());
            cs.setInt(5, product.getCategory().getId());
            cs.setString(6,product.getDes());
            cs.setInt(7, product.getId());
            cs.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id) {
        String sql = "{call delete_product(?)}";
        try (Connection connection = getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, id);
            cs.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "{call getAll()}";
        try (Connection connection = getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                int cate_id = rs.getInt("cate_id");
                Category category = getCategoryById(rs.getInt("cate_id"));
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("p_name"));
                product.setPrice(rs.getLong("price"));
                product.setColor(rs.getString("color"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategory(category);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    public Category getCategoryById(int _id) {
        String sql = "select * from category where id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, _id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("cate_name"));
                return category;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Category> getAllCate() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from category")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("cate_name"));
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    @Override
    public List<Product> searchByKeyword(String key) {
        List<Product> products = new ArrayList<>();
        String sql = "{call search_by_key(?)}";
        try (Connection connection = getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, "%" + key + "%");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                Category category = getCategoryById(rs.getInt("cate_id"));
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("p_name"));
                product.setPrice(rs.getLong("price"));
                product.setColor(rs.getString("color"));
                product.setQuantity(rs.getInt("quantity"));
                product.setCategory(category);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProductById(int id) {

        String sql = "{call get_product_by(?)}";
        try (Connection connection = getConnection();
             CallableStatement cs = connection.prepareCall(sql)) {
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Product product = new Product();
                Category category = getCategoryById(rs.getInt("cate_id"));
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("p_name"));
                product.setPrice(rs.getLong("price"));
                product.setColor(rs.getString("color"));
                product.setQuantity(rs.getInt("quantity"));
                product.setDes(rs.getString("des"));
                product.setCategory(category);
                return product;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
