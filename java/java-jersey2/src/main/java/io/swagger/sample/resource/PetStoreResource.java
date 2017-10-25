/**
 *  Copyright 2016 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.swagger.sample.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.sample.data.StoreData;
import io.swagger.sample.model.AuthenticationInfo;
import io.swagger.sample.model.Order;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/store")
@Produces({"application/json", "application/xml"})
public class PetStoreResource {
  static StoreData storeData = new StoreData();

  @GET
  @Path("/order/{orderId}")
  @Operation(
          summary = "Find purchase order by ID",
          description = "For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions",
          responses = {
                  @ApiResponse(content = @Content(schema = @Schema(implementation = Order.class))),
                  @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                  @ApiResponse(responseCode = "404", description = "Order not found")
          }
  )
  public Response getOrderById(
      @BeanParam AuthenticationInfo info,
      @Parameter(description = "ID of order to fetch") @PathParam("orderId") Long orderId)
      throws io.swagger.sample.exception.NotFoundException {
    Order order = storeData.findOrderById(orderId);
    if (null != order) {
      return Response.ok().entity(order).build();
    } else {
      throw new io.swagger.sample.exception.NotFoundException(404, "Order not found");
    }
  }

  @POST
  @Path("/order")
  @Operation(
          summary = "Place an order for a pet",
          responses = {
                  @ApiResponse(content = @Content(schema = @Schema(implementation = Order.class))),
                  @ApiResponse(responseCode = "400", description = "Invalid Order")
          }
  )
  public Response placeOrder(
      @BeanParam AuthenticationInfo info,
      @Parameter(description = "order placed for purchasing the pet", required = true) Order order) {
    storeData.placeOrder(order);
    return Response.ok().entity("").build();
  }

  @DELETE
  @Path("/order/{orderId}")
  @Operation(
          summary = "Delete purchase order by ID",
          responses = {
                  @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                  @ApiResponse(responseCode = "404", description = "Order not found")
          }
  )
  public Response deleteOrder(
      @BeanParam AuthenticationInfo info,
      @Parameter(description = "ID of order to delete") @PathParam("orderId") Long orderId) {
    if (storeData.deleteOrder(orderId)) {
      return Response.ok().entity("").build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).entity("Order not found").build();
    }
  }
}
