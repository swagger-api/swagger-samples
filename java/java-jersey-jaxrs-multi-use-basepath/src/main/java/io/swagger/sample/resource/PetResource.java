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
import io.swagger.annotations.ApiResponse;
import io.swagger.sample.data.PetData;
import io.swagger.sample.model.Pet;
import io.swagger.sample.exception.NotFoundException;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import org.apache.commons.io.IOUtils;

import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.slf4j.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;

@Path("/pet")
@Api(value = "/pet", authorizations = {
  @Authorization(value = "petstore_auth",
  scopes = {
    @AuthorizationScope(scope = "write:pets", description = "modify pets in your account"),
    @AuthorizationScope(scope = "read:pets", description = "read your pets")
  })
}, tags = "pet")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PetResource {
  private Logger LOGGER = LoggerFactory.getLogger(PetResource.class);
  static PetData petData = new PetData();

  @GET
  @Path("/{petId}")
  @ApiOperation(value = "Find pet by ID", 
    notes = "Returns a single pet", 
    response = Pet.class,
    authorizations = @Authorization(value = "api_key")
  )
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
    @ApiResponse(code = 404, message = "Pet not found") })
  public Response getPetById(
      @ApiParam(value = "ID of pet to return") @PathParam("petId") Long petId)
      throws NotFoundException {
    Pet pet = petData.getPetById(petId);
    if (null != pet)
      return Response.ok().entity(pet).build();
    else
      throw new NotFoundException(404, "Pet not found");
  }

  @DELETE
  @Path("/{petId}")
  @ApiOperation(value = "Deletes a pet")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
          @ApiResponse(code = 404, message = "Pet not found") })
  public Response deletePet(
    @ApiParam() @HeaderParam("api_key") String apiKey,
    @ApiParam(value = "Pet id to delete", required = true)@PathParam("petId") Long petId) {
    if (petData.deletePet(petId)) {
      return Response.ok().build();
    } else {
     return Response.status(Response.Status.NOT_FOUND).build();
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
    LOGGER.debug("testString: " + testString);
    try {
      String uploadedFileLocation = "./" + fileDetail.getFileName();
      System.out.println("uploading to " + uploadedFileLocation);
      IOUtils.copy(inputStream, new FileOutputStream(uploadedFileLocation));
      String msg = "additionalMetadata: " + testString + "\nFile uploaded to " + uploadedFileLocation + ", " + (new java.io.File(uploadedFileLocation)).length() + " bytes";
      io.swagger.sample.model.ApiResponse output = new io.swagger.sample.model.ApiResponse(200, msg);
      return Response.status(200).entity(output).build();
    }
    catch (Exception e) {
      return Response.status(500).build();
    }
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Add a new pet to the store")
  @ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input") })
  public Response addPet(
      @ApiParam(value = "Pet object that needs to be added to the store", required = true) Pet pet) {
    Pet updatedPet = petData.addPet(pet);
    return Response.ok().entity(updatedPet).build();
  }

  @PUT
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @ApiOperation(value = "Update an existing pet")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Pet not found"),
      @ApiResponse(code = 405, message = "Validation exception") })
  public Response updatePet(
      @ApiParam(value = "Pet object that needs to be added to the store", required = true) Pet pet) {
    Pet updatedPet = petData.addPet(pet);
    return Response.ok().entity(updatedPet).build();
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
    List<Pet> pets = petData.findPetByStatus(status);
    return Response.ok(pets.toArray(new Pet[pets.size()])).build();
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
    List<Pet> pets = petData.findPetByTags(tags);
    return Response.ok(pets.toArray(new Pet[pets.size()])).build();
  }

  @POST
  @Path("/{petId}")
  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  @ApiOperation(value = "Updates a pet in the store with form data",
    consumes = MediaType.APPLICATION_FORM_URLENCODED)
  @ApiResponses(value = {
    @ApiResponse(code = 405, message = "Invalid input")})
  public Response  updatePetWithForm (
   @ApiParam(value = "ID of pet that needs to be updated", required = true)@PathParam("petId") Long petId,
   @ApiParam(value = "Updated name of the pet", required = false)@FormParam("name") String name,
   @ApiParam(value = "Updated status of the pet", required = false)@FormParam("status") String status) {
    Pet pet = petData.getPetById(petId);
    if(pet != null) {
      if(name != null && !"".equals(name))
        pet.setName(name);
      if(status != null && !"".equals(status))
        pet.setStatus(status);
      petData.addPet(pet);
      return Response.ok().build();
    }
    else
      return Response.status(404).build();
  }
}
