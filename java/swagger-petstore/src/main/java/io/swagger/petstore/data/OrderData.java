/**
 * Copyright 2018 SmartBear Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.swagger.petstore.data;

import io.swagger.petstore.model.Order;

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

    public static Order createOrder(final long id, final long petId, final int quantity, final Date shipDate,
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
