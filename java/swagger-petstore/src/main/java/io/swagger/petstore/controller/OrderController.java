package io.swagger.petstore.controller;

import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.petstore.data.OrderData;
import io.swagger.petstore.model.Order;
import io.swagger.petstore.utils.Util;

import javax.ws.rs.core.Response;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaInflectorServerCodegen", date = "2017-04-08T15:48:56.501Z")
public class OrderController {

    private static OrderData orderData = new OrderData();

    public ResponseContext getInventory(final RequestContext request) {
        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity(orderData.getCountByStatus());
    }

    public ResponseContext getOrderById(final RequestContext request, final Long orderId) {
        if (orderId == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No orderId provided. Try again?");
        }

        final Order order = orderData.getOrderById(orderId);

        if (order != null) {
            return new ResponseContext()
                    .contentType(Util.getMediaType(request))
                    .entity(order);
        }

        return new ResponseContext().status(Response.Status.NOT_FOUND).entity("Order not found");
    }

    public ResponseContext placeOrder(final RequestContext request, final Order order) {
        if (order == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No Order provided. Try again?");
        }

        orderData.addOrder(order);
        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity(order);
    }

    public ResponseContext deleteOrder(final RequestContext request, final Long orderId) {
        if (orderId == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No orderId provided. Try again?");
        }

        orderData.deleteOrderById(orderId);

        final Order order = orderData.getOrderById(orderId);

        if (null == order) {
            return new ResponseContext()
                    .contentType(Util.getMediaType(request))
                    .entity(order);
        } else {
            return new ResponseContext().status(Response.Status.NOT_MODIFIED).entity("Order couldn't be deleted.");
        }
    }
}