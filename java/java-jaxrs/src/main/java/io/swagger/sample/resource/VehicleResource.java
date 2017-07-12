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

// import io.swagger.annotations.Api;
import io.swagger.oas.annotations.Operation;
import io.swagger.oas.annotations.Parameter;
import io.swagger.oas.annotations.media.Content;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.responses.ApiResponse;
//import io.swagger.annotations.Authorization;
//import io.swagger.annotations.AuthorizationScope;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;

@Path("/vehicle")
// @Api(value = "/vehicle", description = "Operations about vehicles", authorizations = {
//   @Authorization(value = "vehiclestore_auth",
//   scopes = {
//     @AuthorizationScope(scope = "write:vehicle", description = "modify vehicles in your account"),
//     @AuthorizationScope(scope = "read:vehicle", description = "read your vehicles")
//   })
// }, tags = "vehicle")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class VehicleResource {
  static VehicleData vehicleData = new VehicleData();

  @GET
  @Path("/{vehicleId}")
  @Operation(
		  method = "GET",
		  summary = "Find vehicle by ID", 
		  description = "Returns a vehicle when ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid ID supplied"
						  ),
				  @ApiResponse(
						  responseCode = "404",
						  description = "Vehicle not found"
						  ),
				  @ApiResponse(
						  responseCode = "200",
						  description = "Vehicle found",
						  content = @Content(
								  mediaType = "application/json",
								  schema = @Schema(type = "array", implementation = Vehicle.class))	
						  )
				  
		  }
		  
//authorizations = @Authorization(value = "api_key")
  )

  public Response getVehicleById(
		  @Parameter(
				  name = "vehicleId",
				  description = "ID of vehicle that needs to be fetched",
				  required = true,
				  schema = @Schema(implementation = Long.class, type = "integer", minimum = "1", maximum = "10")
				  ) @PathParam("vehicleId") Long vehicleId )
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
  @Operation(
		  method = "GET",
		  summary = "Find vehicle by ID", 
		  description = "Returns a vehicle when ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid ID supplied"
						  ),
				  @ApiResponse(
						  responseCode = "404",
						  description = "Vehicle not found"
						  )
		  }
//    authorizations = @Authorization(value = "api_key")
  )
  public Response downloadFile(
		  @Parameter(
				  name = "vehicleId",
				  description = "ID of vehicle that needs to be fetched",
				  required = true,
				  schema = @Schema(implementation = Long.class, type = "integer", minimum = "1", maximum = "10")
				  ) @PathParam("vehicleId") Long vehicleId)
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
  @Operation(
		  method = "DELETE",
		  summary = "Deletes a vehicle",
		  description = "Deletes a vehicle when ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid ID supplied"
						  ), 
				  @ApiResponse(
						  responseCode = "404",
						  description = "Vehicle not found"
						  )
		  }
		  )
  public Response deleteVehicle(
    @Parameter(name ="apiKey", schema = @Schema(implementation = String.class, type = "string")) @HeaderParam("api_key") String apiKey,
    @Parameter(name = "vehicleId",  description = "Vehicle id to delete", required = true) @PathParam("vehicleId") Long vehicleId) {
      if (vehicleData.deleteVehicle(vehicleId)) {
        return Response.ok().build();
      } else {
        return Response.status(Response.Status.NOT_FOUND).build();
      }
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Operation(
		  method= "POST",
		  summary = "Add a new vehicle to the store",
		  responses = {
				  @ApiResponse( 
						  responseCode = "400",
						  description = "Invalid input",
						  content = @Content(
								  mediaType = "application/json",
								  schema = @Schema(implementation = ApiResponse.class))
						  )
		  })
  public Response addVehicle(
    	  @Parameter(name = "vehicle",
    		      description = "Vehicle object that needs to be added to the store",
    		      required = true) Vehicle vehicle) {
    Vehicle updatedVehicle = vehicleData.addVehicle(vehicle);
    return Response.ok().entity(updatedVehicle).build();
  }

  @PUT
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Operation(
		  method = "PUT",
		  summary = "Update an existing vehicle",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid ID supplied"
						  ),
				  @ApiResponse(
						  responseCode = "404",
						  description = "Vehicle not found"
						  ),
				  @ApiResponse( 
						  responseCode = "405",
						  description = "Validation exception"
						  ) 
		  })
  public Response updateVehicle(
      @Parameter(name = "vehicle", description = "Vehicle object that needs to be added to the store", required = true) Vehicle vehicle) {
    Vehicle updatedVehicle = vehicleData.addVehicle(vehicle);
    return Response.ok().entity(updatedVehicle).build();
  }

  @POST
  @Path("/{vehicleId}")
  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  @Operation(
		  method = "POST",
		  summary = "Updates a vehicle in the store with form data",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid input"
						  )
		  })
  public Response updateVehicleWithForm (
		 @Parameter(
				 name = "vehicleId",
				 description = "ID of vehicle that needs to be updated",
				 required = true,
				 schema = @Schema(implementation = Long.class)) @PathParam("vehicleId") Long vehicleId,
		 @Parameter(
				 name = "name",
				 description = "Updates name of the vehicle",
				 schema = @Schema(implementation = String.class)) @FormParam("name") String name,
		 @Parameter(
				 name = "status",
				 description = "Updates status of the vehicle",
				 schema = @Schema(implementation = String.class)) @FormParam("status") String status) {
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
