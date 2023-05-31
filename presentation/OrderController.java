package presentation;

import bll.OrderBLL;
import bll.ClientBLL;
import bll.ProductBLL;
import Model.Client;
import Model.Product;
import Model.Order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class OrderController {
    private OrderView orderView;
    private OrderBLL orderBLL;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;

    public OrderController(OrderView orderView, OrderBLL orderBLL, ClientBLL clientBLL, ProductBLL productBLL) {
        this.orderView = orderView;
        this.orderBLL = orderBLL;
        this.clientBLL = clientBLL;
        this.productBLL = productBLL;

        orderView.getBtnCreateOrder().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String clientName = (String)orderView.getClientBox().getSelectedItem();
                    String productName = (String)orderView.getProductBox().getSelectedItem();
                    int quantity = Integer.parseInt(orderView.getTfQuantity().getText());

                    // Find client and product by name
                    Client client = clientBLL.findByName(clientName);
                    Product product = productBLL.findByName(productName);

                    // Check if there's enough stock
                    if (product.getQuantity() >= quantity) {
                        // Create order
                        Order order = new Order(client.getId(), product.getId(), quantity);
                        orderBLL.insertOrder(order);
                        // Decrement product quantity
                        product.setQuantity(product.getQuantity() - quantity);
                        productBLL.updateProduct(product);
                    } else {
                        // Show under-stock message
                        JOptionPane.showMessageDialog(null, "Not enough stock for the product.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Show error message for invalid quantity
                    JOptionPane.showMessageDialog(null, "Invalid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
