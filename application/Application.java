package application;

import bll.*;
import presentation.*;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the Main View
            MainView mainView = new MainView();

            // Create the BLL instances
            ClientBLL clientBLL = new ClientBLL();
            ProductBLL productBLL = new ProductBLL();
            OrderBLL orderBLL = new OrderBLL();

            // Create the Client and Product Views
            ClientView clientView = new ClientView();
            ProductView productView = new ProductView();

            // Create the Controllers and wire the Views and the BLLs
            ClientController clientController = new ClientController(clientView, clientBLL);
            ProductController productController = new ProductController(productView, productBLL);

            // Set up action listeners for the main view buttons
            mainView.getBtnViewClients().addActionListener(e -> clientView.setVisible(true));
            mainView.getBtnViewProducts().addActionListener(e -> productView.setVisible(true));

            // Set up action listener for the "View Orders" button
            mainView.getBtnViewOrders().addActionListener(e -> {
                // Create the Order View and Controller when the button is clicked
                OrderView orderView = new OrderView(clientBLL.getAllClientNames(), productBLL.getAllProductNames());
                new OrderController(orderView, orderBLL, clientBLL, productBLL);
            });
        });
    }
}
