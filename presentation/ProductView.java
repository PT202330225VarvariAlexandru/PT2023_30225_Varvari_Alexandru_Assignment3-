package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import Model.Product;

public class ProductView extends JFrame {
    private JTextField tfProductName = new JTextField(20);
    private JTextField tfProductQuantity = new JTextField(20);

    private JButton btnAddProduct = new JButton("Add Product");
    private JButton btnEditProduct = new JButton("Edit Product");
    private JButton btnDeleteProduct = new JButton("Delete Product");

    private JTable productTable;
    private DefaultTableModel tableModel;

    public ProductView() {
        String[] columnNames = {"ID", "Name", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new FlowLayout());

        add(new JLabel("Product Name"));
        add(tfProductName);

        add(new JLabel("Product Quantity"));
        add(tfProductQuantity);

        add(btnAddProduct);
        add(btnEditProduct);
        add(btnDeleteProduct);

        JScrollPane jScrollPane = new JScrollPane(productTable);
        add(jScrollPane);

        setVisible(false);  // Make the frame initially hidden
        productTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                tfProductName.setText((String) productTable.getValueAt(selectedRow, 1));
                tfProductQuantity.setText(productTable.getValueAt(selectedRow, 2).toString());
            }
        });
    }

    public JTextField getTfProductName() {
        return tfProductName;
    }

    public JTextField getTfProductQuantity() {
        return tfProductQuantity;
    }

    public JButton getBtnAddProduct() {
        return btnAddProduct;
    }

    public JButton getBtnEditProduct() {
        return btnEditProduct;
    }

    public JButton getBtnDeleteProduct() {
        return btnDeleteProduct;
    }

    public JTable getProductTable() {
        return productTable;
    }

    public int getProductId() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            return (int) productTable.getValueAt(selectedRow, 0);
        } else {
            throw new RuntimeException("No product is selected.");
        }
    }

    public void refreshTable(List<Product> products) {
        tableModel.setRowCount(0);

        for (Product product : products) {
            Object[] row = new Object[]{product.getId(), product.getName(), product.getQuantity()};
            tableModel.addRow(row);
        }
    }
}
