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
import io.swagger.sample.data.PetData;
import io.swagger.sample.model.Pet;
import io.swagger.sample.exception.NotFoundException;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import java.util.List;

@Path("/pet")
@Api(value = "/pet", description = "Operations about pets")
@Produces({"application/json", "application/xml"})
public class PetResource {
  static PetData petData = new PetData();

  @GET
  @Path("/{petId}")
  @ApiOperation(value = "Find pet by ID", 
    notes = "Returns a pet when ID <= 10.  ID > 10 or non-integers will simulate API error conditions"
  )
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Pet not found") })
  public Pet getPetById(
      @ApiParam(value = "ID of pet that needs to be fetched", allowableValues = "range[1,10]", required = true)
      @PathParam("petId") Long petId) throws NotFoundException {
    Pet pet = petData.getPetById(petId);
    if (null != pet) {
      return pet;
    } else {
      throw new NotFoundException(404, "Pet not found");
    }
  }

  @POST
  @ApiOperation(value = "Add a new pet to the store")
  @ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
  public Response addPet(
      @ApiParam(value = "Pet object that needs to be added to the store", required = true) Pet pet) {
    petData.addPet(pet);
    return Response.ok().entity("SUCCESS").build();
  }

  @PUT
  @ApiOperation(value = "Update an existing pet")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Pet not found"),
      @ApiResponse(code = 405, message = "Validation exception") })
  public Response updatePet(
      @ApiParam(value = "Pet object that needs to be added to the store", required = true) Pet pet) {
    petData.addPet(pet);
    return Response.ok().entity("SUCCESS").build();
  }

  @GET
  @Path("/findByStatus")
  @ApiOperation(value = "Finds Pets by status", 
    notes = "Multiple status values can be provided with comma separated strings",
    response = Pet.class, 
    responseContainer = "List")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid status value") })
  public Response findPetsByStatus(
      @ApiParam(value = "Status values that need to be considered for filter", required = true, defaultValue = "available", allowableValues = "available,pending,sold", allowMultiple = true) @QueryParam("status") String status) {
    return Response.ok(new GenericEntity<List<Pet>>(petData.findPetByStatus(status)){}).build();
  }

  @GET
  @Path("/findByTags")
  @ApiOperation(value = "Finds Pets by tags",
    notes = "Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.",
    response = Pet.class, 
    responseContainer = "List")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid tag value") })
  @Deprecated
  public Response findPetsByTags(
      @ApiParam(value = "Tags to filter by", required = true, allowMultiple = true) @QueryParam("tags") String tags) {
    return Response.ok(new GenericEntity<List<Pet>>(petData.findPetByTags(tags)){}).build();
  }
}
