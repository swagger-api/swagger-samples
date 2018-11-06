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

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.sample.data.PetData;
import io.swagger.sample.model.Pet;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@OpenAPIDefinition(
        info = @Info(title = "Petstore", version = "2.0.0", description = "This is a sample Petstore server."))
@Path("/pet")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PetResource {
  static PetData petData = new PetData();

  @GET
  @Path("/{petId}")
  @Operation(summary = "Find pet by ID",
          tags = {"pets"},
          description = "Returns a pet when 0 < ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
          responses = {
                  @ApiResponse(description = "The pet", content = @Content(
                          schema = @Schema(implementation = Pet.class)
                  )),
                  @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                  @ApiResponse(responseCode = "404", description = "Pet not found")
          })
  public Pet getPetById(
          @Parameter(
                  description = "ID of pet that needs to be fetched",
                  schema = @Schema(
                          type = "integer",
                          format = "int64",
                          description = "param ID of pet that needs to be fetched",
                          allowableValues = {"1","2","3"}
                  ),
                  required = true)
          @PathParam("petId") Long petId) throws io.swagger.sample.exception.NotFoundException {
    Pet pet = petData.getPetById(petId);
    if (null != pet) {
      return pet;
    } else {
      throw new io.swagger.sample.exception.NotFoundException(404, "Pet not found");
    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Add a new pet to the store",
          tags = {"pets"},
          responses = {
                  @ApiResponse(responseCode = "405", description = "Invalid input")
          })
  public Response addPet(
          @Parameter(description = "Pet object that needs to be added to the store", required = true) Pet pet) {
    petData.addPet(pet);
    return Response.ok().entity("SUCCESS").build();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Update an existing pet",
          tags = {"pets"},
          responses = {
                  @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
                  @ApiResponse(responseCode = "404", description = "Pet not found"),
                  @ApiResponse(responseCode = "405", description = "Validation exception") })
  public Response updatePet(
          @Parameter(description = "Pet object that needs to be added to the store", required = true) Pet pet) {
    petData.addPet(pet);
    return Response.ok().entity("SUCCESS").build();
  }

  @GET
  @Path("/findByStatus")
  @Operation(summary = "Finds Pets by status",
          tags = {"pets"},
          description = "Multiple status values can be provided with comma seperated strings",
          responses = {
                  @ApiResponse(
                          content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                  schema = @Schema(implementation = Pet.class))),
                  @ApiResponse(
                          responseCode = "400", description = "Invalid status value"
                  )}
  )
  public List<Pet> findPetsByStatus(
          @Parameter(
                  description = "Status values that need to be considered for filter",
                  required = true,
                  schema = @Schema(
                          allowableValues =  {"available","pending","sold"},
                          defaultValue = "available"
                  )
          )
          @QueryParam("status") String status,
          @BeanParam QueryResultBean qr
  ){
    return petData.findPetByStatus(status);
  }

  @GET
  @Path("/findByTags")
  @Operation(summary = "Finds Pets by tags",
          tags = {"pets"},
          description = "Muliple tags can be provided with comma seperated strings. Use tag1, tag2, tag3 for testing.",
          responses = {
                  @ApiResponse(description = "Pets matching criteria",
                          content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                  schema = @Schema(implementation = Pet.class))
                  ),
                  @ApiResponse(description = "Invalid tag value", responseCode = "400")
          })
  @Deprecated
  public List<Pet> findPetsByTags(
          @Parameter(description = "Tags to filter by", required = true) @QueryParam("tags") String tags) {
    return petData.findPetByTags(tags);
  }
}