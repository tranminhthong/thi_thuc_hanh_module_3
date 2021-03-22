package Model.Service;

import Model.Entities.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    void editProduct(Product product);

    void deleteProduct(int id);

    List<Product> getAll();

    List<Product> searchByKeyword(String key);

    Product getProductById(int id);
}
