package application.data;

import application.dto.Product;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ProductDAOMockImpl implements ProductDAO {
    
    private static final Log log = LogFactory.getLog(ProductDAOMockImpl.class);

    @Override
    public List<Product> findProducts() {
        List<Product> products = new ArrayList<Product>();

        Product product1 = new Product();
        product1.setSku("A00000001");
        product1.setDescription("Antiflu-Des");

        products.add(product1);

        return products;
    }

	@Override
	public void saveProduct(Product product) {
        if (log.isDebugEnabled())
            log.debug("Saving product : " + product.getSku());
	}
}