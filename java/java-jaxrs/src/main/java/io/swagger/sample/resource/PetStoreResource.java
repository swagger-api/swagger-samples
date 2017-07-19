/**
 *  Copyright 2015 SmartBear Software
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

import io.swagger.oas.annotations.*;
import io.swagger.oas.annotations.media.Content;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.responses.ApiResponse;
import io.swagger.sample.data.StoreData;
import io.swagger.sample.model.Order;
import io.swagger.sample.exception.NotFoundException;
import io.swagger.sample.data.PetData;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;

@Path("/store")
@Schema(name="/store")
@Produces({"application/json", "application/xml"})
public class PetStoreResource {
  static StoreData storeData = new StoreData();
  static PetData petData = new PetData();

  @GET
  @Path("/inventory")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Operation(
		  method = "get",
		  summary = "Returns pet inventories by status", 
		  description = "Returns a map of status codes to quantities", 
		  responses = {
				  @ApiResponse(
						  responseCode = "200", 
						  description = "successful operation"
						  	)
		  			}
		  )
  public java.util.Map<String, Integer> getInventory() {
    return petData.getInventoryByStatus();
  }

  @GET
  @Path("/order/{orderId}")
  @Operation(
		  method = "get",
		  summary = "Find purchase order by ID",
		  description = "For valid response try integer IDs with value between the integers of 1 and 10. Other values will generated exceptions",
		  responses = { 
				  @ApiResponse(
						  responseCode = "200", 
						  description = "successful operation",
						  content = @Content(
									schema = @Schema(implementation = Order.class)
							)),
				  @ApiResponse(
						  responseCode = "400", 
						  description = "Invalid ID supplied",
						  content = @Content(
									schema = @Schema(implementation = Order.class)
							)),
				  @ApiResponse(
						  responseCode = "404", 
						  description = "Order not found",
						  content = @Content(
									schema = @Schema(implementation = Order.class)
							))
				  })
  public Response getOrderById(
      @Parameter(
    		  name = "orderId",
    		  description = "ID of pet that needs to be fetched", 
    		  schema = @Schema(
    				  type = "long", 
    				  minimum = "1", 
    				  maximum = "10"), 
    		  required = true
    		  )
      @PathParam("orderId") Long orderId)
      throws NotFoundException {
    Order order = storeData.findOrderById(orderId);
    if (null != order) {
      return Response.ok().entity(order).build();
    } else {
      throw new NotFoundException(404, "Order not found");
    }
  }

  @POST
  @Path("/order")
  @Operation(
		  method = "post",
		  summary = "Place an order for a pet",
		  responses = {
				  @ApiResponse(
						  responseCode = "200", 
						  description = "successful operation"
						),
				  @ApiResponse(
						  responseCode = "400", 
						  description = "Invalid Order"
						  ) 
				  })
  public Order placeOrder(
      @Parameter(
    		  description = "order placed for purchasing the pet",
    		  required = true
    		  ) 
      Order order) {
    storeData.placeOrder(order);
    return storeData.placeOrder(order);
  }

  @DELETE
  @Path("/order/{orderId}")
  @Operation(
		  method = "delete",
		  summary = "Delete purchase order by ID",
		  description = "For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors",
		  responses = { 
				  @ApiResponse(
						  responseCode = "400", 
						  description = "Invalid ID supplied"
						  ),
				  @ApiResponse(
						  responseCode = "404", 
						  description = "Order not found"
						  ) 
				  })
  public Response deleteOrder(
      @Parameter(
    		  name = "orderId",
    		  description = "ID of the order that needs to be deleted",  
    		  schema = @Schema(
    				  type = "long", 
    				  minimum = "1"
    				  ), 
    		  required = true
    		  )
      @PathParam("orderId") Long orderId) {
    if (storeData.deleteOrder(orderId)) {
      return Response.ok().entity("").build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).entity("Order not found").build();
    }
  }
}
