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
//import io.swagger.annotations.Api;
import io.swagger.oas.annotations.Operation;
import io.swagger.oas.annotations.Parameter;
import io.swagger.oas.annotations.callbacks.Callback;
import io.swagger.oas.annotations.info.Info;
import io.swagger.oas.annotations.info.License;
import io.swagger.oas.annotations.info.Contact;
import io.swagger.oas.annotations.media.Content;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.media.ExampleObject;
//import io.swagger.oas.annotations.parameters.Parameters;
import io.swagger.oas.annotations.responses.ApiResponse;

import io.swagger.sample.data.PetData;
import io.swagger.sample.model.Pet;
import io.swagger.sample.exception.NotFoundException;

import java.io.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.*;

@Path("/pet")

@Api(value = "/pet", description = "Operations about pets", tags = "pet")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Info(
		title = "Pets Operations",
		version = "1.0",
		description = "Operations about pets",
		license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
		contact = @Contact(name = "", url = "", email = "")
)
public class PetResource {
	
  static PetData petData = new PetData();

  @GET
  @Path("/{petId}")
  @Operation(
		  method = "GET",
		  summary = "Find pet by ID",
		  description = "Returns a pet when ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid ID supplied"
				  ),
				  @ApiResponse(
						  responseCode = "404",
						  description = "Pet not found"
				  ),
				  @ApiResponse(
						  responseCode = "200",
						  content = @Content(
						  mediaType = "application/json",
						  schema = @Schema(type = "array", implementation = Pet.class))	
			      )
		  }
  )
   public Response getPetById(
		   @Parameter(
				   name = "petId",
				   description = "ID of pet that needs to be fetched",
				   required = true,
				   schema = @Schema(implementation = Long.class, maximum = "10", minimum = "1")) @PathParam("petId") Long petId)
      throws NotFoundException {
    Pet pet = petData.getPetById(petId);
    if (pet != null) {
      return Response.ok().entity(pet).build();
    } else {
      throw new NotFoundException(404, "Pet not found");
    }
  }

  @GET
  @Path("/{petId}/download")
  @Operation(
		  method = "GET",
		  summary = "Find pet by ID and download it", 
		  description = "Downloads a pet when ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid ID supplied"
				  ),
				  @ApiResponse(
						  responseCode = "404",
						  description = "Pet not found"
				  )
		  }
  )
  public Response downloadFile(
      @Parameter(
    		  name = "petId",
    		  description = "ID of pet that needs to be fetched",
    		  required = true,
    		  schema = @Schema(implementation = Long.class, maximum = "10", minimum = "1")
      ) @PathParam("petId") Long petId)
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
  @Path("/{petId}")
  @Operation(
		  method = "DELETE",
		  summary = "Deletes a pet by ID",
		  description = "Deletes a pet when ID <= 10.  ID > 10 or nonintegers will simulate API error conditions",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid ID supplied"
				  ),
				  @ApiResponse(
						  responseCode = "404",
						  description = "Pet not found"
				  )
		  }
  )
  public Response deletePet(
	   		  @Parameter(
					  name = "apiKey",
					  description = "authentication key to access this method",
					  schema = @Schema(type = "String", implementation = String.class)
			  ) @HeaderParam("api_key") String apiKey,
			  @Parameter(
					  name = "petId",
		    		  description = "ID of pet that needs to be fetched",
		    		  required = true,
		    		  schema = @Schema(implementation = Long.class, maximum = "10", minimum = "1")
			  ) @PathParam("petId") Long petId) {
      if (petData.deletePet(petId)) {
        return Response.ok().build();
      } else {
        return Response.status(Response.Status.NOT_FOUND).build();
      }
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Operation(
		  method = "POST",
		  summary = "Add pet to store",
		  description = "Add a new pet to the store",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid input",
						  content = @Content(
								  mediaType = "application/json",
								  schema = @Schema(implementation = ApiResponse.class))
				  )
		  }
  )
  public Response addPet(
		  @Parameter(
	    		  name ="pet",
	    		  description = "Pet to add",
	    		  required = true,
	    		  schema = @Schema(implementation = Pet.class)) Pet pet) {
    Pet updatedPet = petData.addPet(pet);
    return Response.ok().entity(updatedPet).build();
  }

  @PUT
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Operation(
		  method = "PUT",
		  summary = "Update an existing pet",
		  description = "Update an existing pet with the given new attributes",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid ID supplied"
				  ),
				  @ApiResponse(
						  responseCode = "404",
						  description = "Pet not found"
				  ),
				  @ApiResponse(
						  responseCode = "405",
						  description = "Validation exception"
			      )
		  })
   public Response updatePet(
      @Parameter(
    		  name ="pet",
    		  description = "Pet to update with",
    		  required = true,
    		  schema = @Schema(implementation = Pet.class)) Pet pet) {
    Pet updatedPet = petData.addPet(pet);
    return Response.ok().entity(updatedPet).build();
  }

  @GET
  @Path("/findByStatus")
  @Operation(
		  
		  method = "GET",
		  summary = "Finds Pets by status",
		  description = "Find all the Pets with the given status; Multiple status values can be provided with comma seperated strings",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid status value"
				  ),
				  @ApiResponse(
						  responseCode = "200",
						  content = @Content(
								  mediaType = "application/json",
								  schema = @Schema(type = "list", implementation = Pet.class))	
					      )
		  })

  public Response findPetsByStatus(
      @Parameter(
    		  name = "status",
    		  description = "Status values that need to be considered for filter",
    		  required = true,
    		  schema = @Schema(implementation = String.class),
    		  content = {
    				  @Content(
    						  examples = {
    								  @ExampleObject(
    										  name = "Available",
    			    						  value = "available",
    			    						  summary = "Retrieves all the pets that are available"),
    								  @ExampleObject(
    										  name = "Pending",
    										  value = "pending",
    										  summary = "Retrieves all the pets that are pending to be sold"),
    								  @ExampleObject(
    										  name = "Sold",
    										  value = "sold",
    										  summary = "Retrieves all the pets that are sold"),  
    						  }
    						  )
    		  },
    		  allowEmptyValue = true) String statttus) {
    return Response.ok(petData.findPetByStatus(statttus)).build();
  }

  @GET
  @Path("/findByTags")

  @Callback(operation = 
  @Operation(
		  method = "GET",
		  summary = "Finds Pets by tags",
		  description = "Find Pets by tags; Muliple tags can be provided with comma seperated strings. Use tag1, tag2, tag3 for testing.",
		  responses = {
				  @ApiResponse(
						  responseCode = "400",
						  description = "Invalid tag value"
				  ),
				  @ApiResponse(
						  responseCode = "200",
						  content = @Content(
								  mediaType = "application/json",
								  schema = @Schema(type = "list", implementation = Pet.class))	
					      )
		  }))
  @Deprecated
  public Response findPetsByTags(
    @HeaderParam("api_key") String api_key,
      @Parameter(name = "tags", description = "Tags to filter by", required = true) @QueryParam("tags") String tags) {
    return Response.ok(petData.findPetByTags(tags)).build();
  }

  @POST
  @Path("/{petId}")
  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  @Operation(
		  method = "POST",
		  summary = "Updates a pet in the store with form data",
		  responses = {
				  @ApiResponse(
						  responseCode = "405",
						  description = "Validation exception"
			      )
		  })
  public Response updatePetWithForm (
   @Parameter(name = "petId", description = "ID of pet that needs to be updated", required = true) @PathParam("petId") Long petId,
   @Parameter(name = "name", description = "Updated name of the pet") @FormParam("name") String name,
   @Parameter(name = "status", description = "Updated status of the pet") @FormParam("status") String status) {
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
