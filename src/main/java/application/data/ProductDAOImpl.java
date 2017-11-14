package application.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import application.dto.Product;

public class ProductDAOImpl implements ProductDAO {

    private static final Log log = LogFactory.getLog(ProductDAOImpl.class);

    private static final String DS_NAME = "jdbc/inventory";
    private static final String GET_PRODUCTS = "SELECT SKU, DESCRIPTION FROM PRODUCTS";
    private static final String SAVE_PRODUCTS = "INSERT INTO PRODUCTS VALUES (?, ?)";

    @Override
    public List<Product> findProducts() {
        
        Context context;
        Connection connection = null;
        List<Product> products = new ArrayList<Product>();
        try {
            context = new InitialContext();
            DataSource datasource = (DataSource) context.lookup(DS_NAME);
            connection = datasource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS);
            ResultSet result = preparedStatement.executeQuery();
            if (log.isDebugEnabled())
                log.debug("Parsing Result Set");
            while (result.next()) {
                Product product = new Product();
                product.setSku(result.getString("SKU"));
                product.setDescription(result.getString("DESCRIPTION"));
                if (log.isDebugEnabled())
                    log.debug("SKU : " + product.getSku());
                products.add(product);
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return products;

    }

	@Override
	public void saveProduct(Product product) {
        Context context;
        Connection connection = null;
        try {
            context = new InitialContext();
            DataSource datasource = (DataSource) context.lookup(DS_NAME);
            connection = datasource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_PRODUCTS);
            preparedStatement.setString(1, product.getSku());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.executeUpdate();
        } catch (NamingException | SQLException e) {
            log.error("Exception save product", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Error save product closing connection : ", e);
                }
            }
        }  		
	}

    
}