package application.data;

import java.util.ArrayList;
import java.util.List;

import application.dto.Product;

public class ProductDAO {

    public List<Product> findProducts() {
        List<Product> products = new ArrayList<Product>();
        Product product1 = new Product();
        product1.setSku("A0000001");
        product1.setDescription("Aspirina");

        Product product2 = new Product();
        product2.setSku("A0000002");
        product2.setDescription("Antiflu Des");
        products.add(product1);
        products.add(product2);

        return products;

    }

}