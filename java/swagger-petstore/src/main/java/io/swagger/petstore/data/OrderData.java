package io.swagger.petstore.data;

import io.swagger.petstore.model.Order;
import io.swagger.petstore.model.Pet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderData {
    private static List<Order> orders = new ArrayList<>();

    static {
        orders.add(createOrder(1, 1, 100, new Date(), "placed", true));
    }

    public Order getOrderById(final long orderId) {
        for (final Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public void addOrder(final Order order) {
        if (orders.size() > 0) {
            orders.removeIf(orderN -> orderN.getId() == order.getId());
        }
        orders.add(order);
    }

    public void deleteOrderById(final Long orderId) {
        orders.removeIf(order -> order.getId() == orderId);
    }

    private static Order createOrder(final long id, final long petId, final int quantity, final Date shipDate,
                                     final String status, final boolean complete) {
        final Order order = new Order();
        order.setId(id);
        order.setPetId(petId);
        order.setComplete(complete);
        order.setQuantity(quantity);
        order.setShipDate(shipDate);
        order.setStatus(status);
        return order;
    }
}
