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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import io.swagger.sample.data.VehicleData;
import io.swagger.sample.data.VehicleData;
import io.swagger.sample.exception.NotFoundException;
import io.swagger.sample.model.Vehicle;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;

@Path("/vehicle")
@Api(value = "/vehicle", description = "Operations about vehicles", authorizations = {
  @Authorization(value = "vehiclestore_auth",
  scopes = {
    @AuthorizationScope(scope = "write:vehicle", description = "modify vehicles in your account"),
    @AuthorizationScope(scope = "read:vehicle", description = "read your vehicles")
  })
}, tags = "vehicle")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class VehicleResource {
  static VehicleData vehicleData = new VehicleData();

  @GET
  @Path("/{vehicleId}")
  @ApiOperation(value = "Find vehicle by ID", 
    notes = "Returns a vehicle when ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
    response = Vehicle.class,
    authorizations = @Authorization(value = "api_key")
  )
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Vehicle not found") })
  public Response getVehicleById(
      @ApiParam(value = "ID of vehicle that needs to be fetched", allowableValues = "range[1,10]", required = true) @PathParam("vehicleId") Long vehicleId)
      throws NotFoundException {
    Vehicle vehicle = vehicleData.getVehicleById(vehicleId);
    if (vehicle != null) {
      return Response.ok().entity(vehicle).build();
    } else {
      throw new NotFoundException(404, "Vehicle not found");
    }
  }

  @GET
  @Path("/{vehicleId}/download")
  @ApiOperation(value = "Find vehicle by ID", 
    notes = "Returns a vehicle when ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
    response = Vehicle.class,
    authorizations = @Authorization(value = "api_key")
  )
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Vehicle not found") })
  public Response downloadFile(
      @ApiParam(value = "ID of vehicle that needs to be fetched", allowableValues = "range[1,10]", required = true) @PathParam("vehicleId") Long vehicleId)
      throws NotFoundException {
      StreamingOutput stream = new StreamingOutput() {
      @Override
      public void write(OutputStream output) throws IOException {
        try {
          // TODO: write file content to output;
          output.write("hello, world".getBytes());
        } catch (Exception e) {
           e.printStackTrace();
        }
      }
    };

    return Response.ok(stream, "application/force-download")
            .header("Content-Disposition", "attachment; filename = foo.bar")
            .build();
  }

  @DELETE
  @Path("/{vehicleId}")
  @ApiOperation(value = "Deletes a vehicle")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
          @ApiResponse(code = 404, message = "Vehicle not found")})
  public Response deleteVehicle(
    @ApiParam() @HeaderParam("api_key") String apiKey,
    @ApiParam(value = "Vehicle id to delete", required = true)@PathParam("vehicleId") Long vehicleId) {
      if (vehicleData.deleteVehicle(vehicleId)) {
        return Response.ok().build();
      } else {
        return Response.status(Response.Status.NOT_FOUND).build();
      }
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Add a new vehicle to the store")
  @ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input", response = io.swagger.sample.model.ApiResponse.class) })
  public Response addVehicle(
      @ApiParam(value = "Vehicle object that needs to be added to the store", required = true) Vehicle vehicle) {
    Vehicle updatedVehicle = vehicleData.addVehicle(vehicle);
    return Response.ok().entity(updatedVehicle).build();
  }

  @PUT
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Update an existing vehicle")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Vehicle not found"),
      @ApiResponse(code = 405, message = "Validation exception") })
  public Response updateVehicle(
      @ApiParam(value = "Vehicle object that needs to be added to the store", required = true) Vehicle vehicle) {
    Vehicle updatedVehicle = vehicleData.addVehicle(vehicle);
    return Response.ok().entity(updatedVehicle).build();
  }

  @POST
  @Path("/{vehicleId}")
  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  @ApiOperation(value = "Updates a vehicle in the store with form data",
    consumes = MediaType.APPLICATION_FORM_URLENCODED)
  @ApiResponses(value = {
    @ApiResponse(code = 405, message = "Invalid input")})
  public Response updateVehicleWithForm (
   @ApiParam(value = "ID of vehicle that needs to be updated", required = true)@PathParam("vehicleId") Long vehicleId,
   @ApiParam(value = "Updated name of the vehicle", required = false)@FormParam("name") String name,
   @ApiParam(value = "Updated status of the vehicle", required = false)@FormParam("status") String status) {
    Vehicle vehicle = vehicleData.getVehicleById(vehicleId);
    if(vehicle != null) {
      if(name != null && !"".equals(name))
        vehicle.setName(name);
      vehicleData.addVehicle(vehicle);
      return Response.ok().build();
    }
    else
      return Response.status(404).build();
  }
}
