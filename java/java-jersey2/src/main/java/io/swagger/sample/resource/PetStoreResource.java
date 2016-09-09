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

import io.swagger.annotations.*;
import io.swagger.sample.data.StoreData;
import io.swagger.sample.model.AuthenticationInfo;
import io.swagger.sample.model.Order;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@Path("/store")
@Api(value="/store" , description = "Operations about store")
@Produces({"application/json", "application/xml"})
public class PetStoreResource {
  static StoreData storeData = new StoreData();

  @GET
  @Path("/order/{orderId}")
  @ApiOperation(value = "Find purchase order by ID",
    notes = "For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions",
    response = Order.class)
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Order not found") })
  public Response getOrderById(
      @BeanParam AuthenticationInfo info,
      @ApiParam(value = "ID of order to fetch") @PathParam("orderId") Long orderId)
      throws io.swagger.sample.exception.NotFoundException {
    Order order = storeData.findOrderById(orderId);
    if (null != order) {
      return Response.ok().entity(order).build();
    } else {
      throw new io.swagger.sample.exception.NotFoundException(404, "Order not found");
    }
  }


  @POST
  @Path("/{petId}/uploadImage")
  @Consumes({MediaType.MULTIPART_FORM_DATA})
  @Produces({MediaType.APPLICATION_JSON})
  @ApiOperation(value = "uploads an image",
          response = io.swagger.sample.model.ApiResponse.class)
  public Response uploadFile(
          @ApiParam(value = "ID of pet to update", required = true) @PathParam("petId") Long petId,
          @ApiParam(value = "Additional data to pass to server") @FormDataParam("additionalMetadata") String testString,
          @ApiParam(value = "file to upload") @FormDataParam("file") InputStream inputStream,
          @ApiParam(value = "file detail") @FormDataParam("file") FormDataContentDisposition fileDetail) {
    try {
      String msg = null;
      if(inputStream != null) {
        String uploadedFileLocation = "./" + fileDetail.getFileName();
        System.out.println("uploading to " + uploadedFileLocation);
//        IOUtils.copy(inputStream, new FileOutputStream(uploadedFileLocation));
        msg = "additionalMetadata: " + testString + "\nFile uploaded to " + uploadedFileLocation + ", " + (new java.io.File(uploadedFileLocation)).length() + " bytes";
      }
      else {
        msg = "additionalMetadata: " + testString + ", no file supplied";
      }
      io.swagger.sample.model.ApiResponse output = new io.swagger.sample.model.ApiResponse(200, msg);
      return Response.status(200).entity(output).build();
    }
    catch (Exception e) {
      return Response.status(500).build();
    }
  }

  @POST
  @Path("/order")
  @ApiOperation(value = "Place an order for a pet",
    response = Order.class)
  @ApiResponses({ @ApiResponse(code = 400, message = "Invalid Order") })
  public Response placeOrder(
      @BeanParam AuthenticationInfo info,
      @ApiParam(value = "order placed for purchasing the pet", required = true) Order order) {
    storeData.placeOrder(order);
    return Response.ok().entity("").build();
  }

  @DELETE
  @Path("/order/{orderId}")
  @ApiOperation(value = "Delete purchase order by ID")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
    @ApiResponse(code = 404, message = "Order not found") })
  public Response deleteOrder(
      @BeanParam AuthenticationInfo info,
      @ApiParam(value = "ID of order to delete") @PathParam("orderId") Long orderId) {
    if (storeData.deleteOrder(orderId)) {
      return Response.ok().entity("").build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).entity("Order not found").build();
    }
  }
}
