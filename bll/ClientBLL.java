package bll;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

import dao.ClientDAO;
import Model.Client;

public class ClientBLL {

    private ClientDAO clientDAO;

    public ClientBLL() {
        this.clientDAO = new ClientDAO();
    }

    public Client findClientById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return client;
    }

    public int insertClient(Client client) {
        return clientDAO.insert(client);
    }

    public void updateClient(Client client) {
        System.out.println("ClientBLL: Updating client with ID: " + client.getId());
        System.out.println("New name: " + client.getName() + ", new email: " + client.getEmail());
        clientDAO.update(client);
    }

    public void deleteClient(int id) {
        clientDAO.delete(id);
    }

    public List<Client> findAllClients() {
        return clientDAO.findAll();
    }
    
    public Client findByName(String name) {
        return clientDAO.findByName(name);
    }

    public List<String> getAllClientNames() {
        List<String> clientNames = new ArrayList<>();
        for (Client client : findAllClients()) {
            clientNames.add(client.getName());
        }
        return clientNames;
    }

}
