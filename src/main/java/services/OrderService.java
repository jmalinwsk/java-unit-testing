package services;

import database.Database;
import models.Order;

import java.io.IOException;

public class OrderService {
    /** Validation of order.
     * @param order variable of validating order
     * @return true if validation is valid
     */
    public boolean orderValidation(Order order) {
        if(order.getUser() != null)
            if(order.getReservation() != null)
                return true;
            else throw new NullPointerException();
        else throw new NullPointerException();
    }

    /** Adding order to the database.
     * @param database variable of database
     * @param order new order
     * @throws IOException when database is null
     * @throws IllegalArgumentException when validation of order is wrong
     */
    public void addOrderToDatabase(Database database, Order order) throws IOException {
        if (orderValidation(order)) {
            if (database != null) {
                Integer id = database.getNextOrderId();
                order.setId(id);

                database.getOrders().put(id, order);
            }
            else throw new IOException();
        }
        else throw new IllegalArgumentException();
    }
}
