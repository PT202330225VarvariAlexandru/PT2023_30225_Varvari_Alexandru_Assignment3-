package presentation;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private JButton btnViewClients = new JButton("View Clients");
    private JButton btnViewProducts = new JButton("View Products");
    private JButton btnViewOrders = new JButton("View Orders"); // new "View Orders" button

    public MainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        add(btnViewClients);
        add(btnViewProducts);
        add(btnViewOrders); // add "View Orders" button to view

        setVisible(true);
    }

    public JButton getBtnViewClients() {
        return btnViewClients;
    }

    public JButton getBtnViewProducts() {
        return btnViewProducts;
    }

    // Getter for the "View Orders" button
    public JButton getBtnViewOrders() {
        return btnViewOrders;
    }
}
