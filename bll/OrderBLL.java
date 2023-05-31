package bll;

import java.util.List;
import java.util.NoSuchElementException;
import dao.OrderDAO;
import Model.Order;

public class OrderBLL {

    public OrderBLL() {
    }

    public Order findOrderById(int id) {
        Order order = OrderDAO.findById(id);
        if (order == null) {
            throw new NoSuchElementException("The order with id=" + id + " was not found!");
        }
        return order;
    }

    public int insertOrder(Order order) {
        return OrderDAO.insert(order);
    }

    public void deleteOrder(int id) {
        OrderDAO.delete(id);
    }

    public List<Order> getAllOrders() {
        return OrderDAO.findAll();
    }
    
    
}
