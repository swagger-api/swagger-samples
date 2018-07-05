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

package io.swagger.petstore.controller;

import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.petstore.data.OrderData;
import io.swagger.petstore.model.Order;
import io.swagger.petstore.utils.Util;
import org.joda.time.DateTime;

import javax.ws.rs.core.Response;
import java.util.Date;

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

    public ResponseContext placeOrder(final RequestContext request, final Long id, final Long petId, final Integer quantity, final DateTime shipDate,
                                      final String status, final Boolean complete) {
        final Order order = OrderData.createOrder(id, petId, quantity, new Date(), status, complete);
        return placeOrder(request,order);
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