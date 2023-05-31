package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderView extends JFrame {
    private JComboBox<String> clientBox;
    private JComboBox<String> productBox;
    private JTextField tfQuantity = new JTextField(20);
    private JButton btnCreateOrder = new JButton("Create Order");

    public OrderView(List<String> clientNames, List<String> productNames) {
        // Initialize combo boxes with data
        clientBox = new JComboBox<>(clientNames.toArray(new String[0]));
        productBox = new JComboBox<>(productNames.toArray(new String[0]));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new FlowLayout());

        add(new JLabel("Select Client"));
        add(clientBox);

        add(new JLabel("Select Product"));
        add(productBox);

        add(new JLabel("Quantity"));
        add(tfQuantity);

        add(btnCreateOrder);

        setVisible(true);
    }

    public JComboBox<String> getClientBox() {
        return clientBox;
    }

    public JComboBox<String> getProductBox() {
        return productBox;
    }

    public JTextField getTfQuantity() {
        return tfQuantity;
    }

    public JButton getBtnCreateOrder() {
        return btnCreateOrder;
    }
}
