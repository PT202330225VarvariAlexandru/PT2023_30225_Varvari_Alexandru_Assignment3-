package presentation;

import bll.ClientBLL;
import Model.Client;

import java.util.List;

public class ClientController {
    private ClientView clientView;
    private ClientBLL clientBLL;

    public ClientController(ClientView clientView, ClientBLL clientBLL) {
        this.clientView = clientView;
        this.clientBLL = clientBLL;

        clientView.getBtnAddClient().addActionListener(e -> addClient());
        clientView.getBtnEditClient().addActionListener(e -> editClient());
        clientView.getBtnDeleteClient().addActionListener(e -> deleteClient());

        // Initially populate table
        refreshTable();
    }

    private void addClient() {
        String name = clientView.getTfClientName().getText();
        String email = clientView.getTfClientEmail().getText();
        Client client = new Client(name, email);
        clientBLL.insertClient(client);

        // Clear the text fields
        clientView.getTfClientName().setText("");
        clientView.getTfClientEmail().setText("");

        // Update table
        refreshTable();
    }

    private void editClient() {
        int id = clientView.getClientId();
        String name = clientView.getTfClientName().getText().trim();
        String email = clientView.getTfClientEmail().getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            System.out.println("Name and email fields must not be empty.");
            return;
        }

        Client client = new Client(id, name, email);
        clientBLL.updateClient(client);

        // Clear the text fields
        clientView.getTfClientName().setText("");
        clientView.getTfClientEmail().setText("");

        // Update your JTable data here
        refreshTable();
    }

    public void deleteClient() {
        int row = clientView.getClientTable().getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(clientView.getClientTable().getValueAt(row, 0).toString());
            clientBLL.deleteClient(id);

            // Update table
            refreshTable();
        } else {
            System.out.println("No row selected for deletion");
        }
    }

    private void refreshTable() {
        List<Client> clients = clientBLL.findAllClients();
        clientView.refreshTable(clients);
    }
}
