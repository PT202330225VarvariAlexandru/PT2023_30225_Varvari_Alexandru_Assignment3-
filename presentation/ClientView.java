package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import Model.Client;

public class ClientView extends JFrame {
    private JTextField tfClientName = new JTextField(20);
    private JTextField tfClientEmail = new JTextField(20);

    private JButton btnAddClient = new JButton("Add Client");
    private JButton btnEditClient = new JButton("Edit Client");
    private JButton btnDeleteClient = new JButton("Delete Client");
    private JButton btnViewClients = new JButton("View Clients");

    private JLabel lblSeeClients = new JLabel("See Clients");

    private JTable clientTable;
    private DefaultTableModel tableModel;

    public ClientView() {
        // Initialize clientTable
        String[] columnNames = {"ID", "Name", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        clientTable = new JTable(tableModel);

        // Set up layout, add components, and other JFrame settings
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new FlowLayout());

        add(new JLabel("Client Name"));
        add(tfClientName);

        add(new JLabel("Client Email"));
        add(tfClientEmail);

        add(btnAddClient);
        add(btnEditClient);
        add(btnDeleteClient);

        add(lblSeeClients);
        add(btnViewClients);

        // Add clientTable into a JScrollPane for better handling of data display
        JScrollPane jScrollPane = new JScrollPane(clientTable);
        add(jScrollPane);

        setVisible(false);  // Make the frame initially hidden
        clientTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = clientTable.getSelectedRow();
            if (selectedRow != -1) {
                tfClientName.setText((String) clientTable.getValueAt(selectedRow, 1));
                tfClientEmail.setText((String) clientTable.getValueAt(selectedRow, 2));
            }
        });
    }

    // ... rest of your code remains the same



    // Getter methods for controller to use

    public JTextField getTfClientName() {
        return tfClientName;
    }

    public JTextField getTfClientEmail() {
        return tfClientEmail;
    }

    public JButton getBtnAddClient() {
        return btnAddClient;
    }

    public JButton getBtnEditClient() {
        return btnEditClient;
    }

    public JButton getBtnDeleteClient() {
        return btnDeleteClient;
    }

    public JButton getBtnViewClients() {
        return btnViewClients;
    }

    public JTable getClientTable() {
        return clientTable;
    }

    public int getClientId() {
        int selectedRow = clientTable.getSelectedRow();
        if (selectedRow != -1) {
            return (int) clientTable.getValueAt(selectedRow, 0);
        } else {
            // This is up to you, you could throw an exception or return a sentinel value
            throw new RuntimeException("No client is selected.");
        }
    }

    public void refreshTable(List<Client> clients) {
        // Clear existing data
        tableModel.setRowCount(0);

        // Add rows one by one from the list
        for (Client client : clients) {
            Object[] row = new Object[]{client.getId(), client.getName(), client.getEmail()};
            tableModel.addRow(row);
        }
    }
}
