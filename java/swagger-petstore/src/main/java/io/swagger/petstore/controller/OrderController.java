package io.swagger.petstore.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.petstore.data.OrderData;
import io.swagger.petstore.model.Order;
import io.swagger.petstore.utils.Util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaInflectorServerCodegen", date = "2017-04-08T15:48:56.501Z")
public class OrderController {

    private static OrderData orderData = new OrderData();

    public ResponseContext getInventory(final RequestContext request) {
        final MediaType outputType = Util.getMediaType(request);
        return new ResponseContext()
                .contentType(outputType)
                .entity(orderData.getCountByStatus());
    }

    public ResponseContext getOrderById(final RequestContext request, final Long orderId) {
        if (orderId == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No orderId provided. Try again?");
        }

        final Order order = orderData.getOrderById(orderId);

        final MediaType outputType = Util.getMediaType(request);

        if (order != null) {
            return new ResponseContext()
                    .contentType(outputType)
                    .entity(order);
        }

        return new ResponseContext().status(Response.Status.NOT_FOUND).entity("Order not found");
    }

    public ResponseContext placeOrder(final RequestContext request, final JsonNode order) {
        if (order == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No Order provided. Try again?");
        }

        final MediaType outputType = Util.getMediaType(request);
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final Order convertedOrder = objectMapper.readValue(order.toString(), Order.class);
            orderData.addOrder(convertedOrder);

            return new ResponseContext()
                    .contentType(outputType)
                    .entity(convertedOrder);
        } catch (IOException e) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity(order);
        }
    }

    public ResponseContext deleteOrder(final RequestContext request, final Long orderId) {
        if (orderId == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No orderId provided. Try again?");
        }

        orderData.deleteOrderById(orderId);

        final MediaType outputType = Util.getMediaType(request);

        final Order order = orderData.getOrderById(orderId);

        if (null == order) {
            return new ResponseContext()
                    .contentType(outputType)
                    .entity(order);
        } else {
            return new ResponseContext().status(Response.Status.NOT_MODIFIED).entity("Order couldn't be deleted.");
        }
    }
}