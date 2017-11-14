package application.data;

import java.util.List;

import application.dto.Product;

public interface ProductDAO {

    public List<Product> findProducts();
    public void saveProduct(Product product);

}