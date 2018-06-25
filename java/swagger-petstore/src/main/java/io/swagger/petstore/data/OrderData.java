package io.swagger.petstore.data;

import io.swagger.petstore.model.Order;
import io.swagger.petstore.model.Pet;

import java.util.*;

public class OrderData {
    private static List<Order> orders = new ArrayList<>();

    static {
        orders.add(createOrder(1, 1, 100, new Date(), "placed", true));
        orders.add(createOrder(2, 1, 50, new Date(), "approved", true));
        orders.add(createOrder(3, 1, 50, new Date(), "delivered", true));
    }

    public Order getOrderById(final long orderId) {
        for (final Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public Map<String, Integer> getCountByStatus() {

        final Map<String, Integer> countByStatus = new HashMap<>();

        for (final Order order : orders) {
            final String status = order.getStatus();
            if (countByStatus.containsKey(status)) {
                countByStatus.put(status, countByStatus.get(status) + order.getQuantity());
            } else {
                countByStatus.put(status, order.getQuantity());
            }
        }

        return countByStatus;
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
