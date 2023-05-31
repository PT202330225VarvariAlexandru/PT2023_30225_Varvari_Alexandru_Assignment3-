package bll;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import dao.ProductDAO;
import Model.Product;

public class ProductBLL {

    private ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    public Product findProductById(int id) {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return product;
    }

    public Product findByName(String name) {
        Product product = productDAO.findByName(name);
        if (product == null) {
            throw new NoSuchElementException("The product with name =" + name + " was not found!");
        }
        return product;
    }

    public int insertProduct(Product product) {
        return productDAO.insert(product);
    }

    public void updateProduct(Product product) {
        productDAO.update(product);
    }

    public void deleteProduct(int id) {
        productDAO.delete(id);
    }
    
    public List<Product> findAllProducts() {
        return productDAO.findAll();
    }

    public String[][] generateTableFromProducts() {
        List<Product> products = productDAO.findAll();
        return productDAO.generateTableFromObjects(products);
    }
    
    public List<String> getAllProductNames() {
        List<String> productNames = new ArrayList<>();
        for (Product product : findAllProducts()) {
            productNames.add(product.getName());
        }
        return productNames;
    }
    
    
}
