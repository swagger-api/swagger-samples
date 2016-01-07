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

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/pet")
@Api(value = "/pet", description = "Operations about pets", position = 0)
@Produces("application/json")
public class PetResource {
  static PetData petData = new PetData();

  @GET
  @Path("/{petId}")
  @ApiOperation(value = "Find pet by ID", 
    notes = "Returns a pet when 0 < ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
    response = Pet.class)
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Pet not found") })
  public Response getPetById(
      @ApiParam(value = "ID of pet that needs to be fetched", allowableValues = "range[1,10]", required = true)
      @PathParam("petId") Long petId) throws NotFoundException {
    Pet pet = petData.getPetById(petId);
    if (null != pet) {
      return Response.ok().entity(pet).build();
    } else {
      throw new NotFoundException(404, "Pet not found");
    }
  }

  @GET
  @Path("/{petId}/owner")
  @ApiOperation(
    value = "Gets the owner of a pet", 
    response = OwnerResource.class)
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Pet not found") })
  public OwnerResource getOwners(@PathParam("petId") Long petId) {
    return new OwnerResource(petId);
  }
}
