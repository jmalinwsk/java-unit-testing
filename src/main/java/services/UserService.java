package services;

import database.Database;
import models.Order;
import models.User;

import java.io.IOException;
import java.util.HashMap;

public class UserService {

    public HashMap<Integer, Order> getOrdersOfUser(String databaseName, User user) throws IOException {
        if (user != null) {
            Database database = Database.deserializeDatabase(databaseName);
            HashMap<Integer, Order> orders = new HashMap<>();
            Integer id = 1;

            for (Order order : database.getOrders().values()) {
                if (order.getUser().getId().equals(user.getId())) {
                    orders.put(id, order);
                    id++;
                }
            }
            return orders;
        } else throw new IllegalArgumentException();
    }

}
