package presentation;

import bll.ProductBLL;
import Model.Product;

import java.util.List;

public class ProductController {
    private ProductView productView;
    private ProductBLL productBLL;

    public ProductController(ProductView productView, ProductBLL productBLL) {
        this.productView = productView;
        this.productBLL = productBLL;

        productView.getBtnAddProduct().addActionListener(e -> addProduct());
        productView.getBtnEditProduct().addActionListener(e -> editProduct());
        productView.getBtnDeleteProduct().addActionListener(e -> deleteProduct());

        refreshTable();
    }

    private void addProduct() {
        String name = productView.getTfProductName().getText();
        int quantity = Integer.parseInt(productView.getTfProductQuantity().getText());
        Product product = new Product(name, quantity);
        productBLL.insertProduct(product);

        productView.getTfProductName().setText("");
        productView.getTfProductQuantity().setText("");

        refreshTable();
    }

    private void editProduct() {
        int id = productView.getProductId();
        String name = productView.getTfProductName().getText().trim();
        int quantity = Integer.parseInt(productView.getTfProductQuantity().getText().trim());

        Product product = new Product(id, name, quantity);
        productBLL.updateProduct(product);

        productView.getTfProductName().setText("");
        productView.getTfProductQuantity().setText("");

        refreshTable();
    }

    private void deleteProduct() {
        int row = productView.getProductTable().getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(productView.getProductTable().getValueAt(row, 0).toString());
            productBLL.deleteProduct(id);

            refreshTable();
        } else {
            System.out.println("No row selected for deletion");
        }
    }

    private void refreshTable() {
        List<Product> products = productBLL.findAllProducts();
        productView.refreshTable(products);
    }
}
