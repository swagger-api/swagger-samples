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
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.responses.ApiResponse;
import io.swagger.oas.annotations.media.Content;
import io.swagger.sample.data.UserData;
import io.swagger.sample.model.User;
import io.swagger.sample.exception.ApiException;
import io.swagger.sample.exception.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/user")
@Schema(name = "/user")
@Produces({"application/json", "application/xml"})
public class UserResource {
  static UserData userData = new UserData();

  @POST
  @Operation(
		  method = "post",
		  summary = "Create user",
		  description = "This can only be done by the logged in user.",
		  responses = {
				  @ApiResponse(
						  description = "successful operation"
						  )
		  })
  public Response createUser(
      @Parameter(
    		  name = "user",
    		  description = "Created user object",
    		  schema = @Schema(implementation = User.class),
    		  required = true
    		  ) 
      User user) {
    userData.addUser(user);
    return Response.ok().entity("").build();
  }

  @POST
  @Path("/createWithArray")
  @Operation(
		  method = "post",
		  summary = "Creates list of users with given input array",
		  responses = {
				  @ApiResponse(
						  responseCode = "200",
						  description = "successful operation"
						  )
		  })
  public Response createUsersWithArrayInput(
		  @Parameter(
				  description = "List of user object", 
				  required = true
				  ) User[] users) {
      for (User user : users) {
          userData.addUser(user);
      }
      return Response.ok().entity("").build();
  }

  @POST
  @Path("/createWithList")
  @Operation(
		  method = "post",
		  summary = "Creates list of users with given input array",
		  responses = {
				  @ApiResponse(
						  responseCode = "200",
						  description = "successful operation"
						  )
		  })
  public Response createUsersWithListInput(
		  @Parameter(
				  description = "List of user object", 
				  required = true) java.util.List<User> users) {
      for (User user : users) {
          userData.addUser(user);
      }
      return Response.ok().entity("").build();
  }

  @PUT
  @Path("/{username}")
  @Operation(
		  method = "put",
		  summary = "Updated user",
		  description = "This can only be done by the logged in user.",
		  responses = {
				  @ApiResponse(
						  responseCode = "400", 
						  description = "Invalid user supplied"
						  ),
				  @ApiResponse(
						  responseCode = "404", 
						  description = "User not found"
						  )
		  })
  public Response updateUser(
      @Parameter(
    		  name = "username",
    		  description = "name that need to be deleted",
    		  schema = @Schema(type = "string"),
    		  required = true
    		  ) 
      @PathParam("username") String username,
      @Parameter(
    		  description = "Updated user object", 
    		  required = true) User user) {
    userData.addUser(user);
    return Response.ok().entity("").build();
  }

  @DELETE
  @Path("/{username}")
  @Operation(
		  method = "delete",
		  summary = "Delete user",
		  description = "This can only be done by the logged in user.",
		  responses = {
				  @ApiResponse(
						  responseCode = "400", 
						  description = "Invalid username supplied"
						  ),
				  @ApiResponse(
						  responseCode = "404", 
						  description = "User not found"
						  ) 
				  })
  public Response deleteUser(
      @Parameter(
    		  name = "username",
    		  description = "The name that needs to be deleted",
    		  schema = @Schema(type = "string"),
    		  required = true
    		  ) 
      @PathParam("username") String username) {
    if (userData.removeUser(username)) {
      return Response.ok().entity("").build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @GET
  @Path("/{username}")
  @Operation(
		  method = "get",
		  summary = "Get user by user name",
		  responses = {
				  @ApiResponse(
						  responseCode = "200", 
						  description = "successful operation",
						  content = @Content(
									schema = @Schema(implementation = User.class)
							)),
				  @ApiResponse(
						  responseCode = "400", 
						  description = "Invalid username supplied",
						  content = @Content(
									schema = @Schema(implementation = User.class)
							)),
				  @ApiResponse(
						  responseCode = "404", 
						  description = "User not found",
								  content = @Content(
											schema = @Schema(implementation = User.class)
									)
						  ) 
				  })
  public Response getUserByName(
      @Parameter(
    		  name = "username",
    		  description = "The name that needs to be fetched. Use user1 for testing.", 
    		  schema = @Schema(type = "string"),
    		  required = true
    		  ) 
      @PathParam("username") String username) throws ApiException {
    User user = userData.findUserByName(username);
    if (null != user) {
      return Response.ok().entity(user).build();
    } else {
      throw new NotFoundException(404, "User not found");
    }
  }

  @GET
  @Path("/login")
  @Operation(
		  method = "get",
		  summary = "Logs user into the system",
		  responses = {
				  
				  @ApiResponse(
						  responseCode = "200", 
						  description = "successful operation"),
				  @ApiResponse(
						  responseCode = "400", 
						  description = "Invalid username/password supplied")
				  })
  public Response loginUser(
      @Parameter(
    		  name = "username",
    		  description = "The user name for login",
    		  schema = @Schema(type = "string"),
    		  required = true
    		  ) 
      @QueryParam("username") String username,
      @Parameter(
    		  name = "password",
    		  description = "The password for login in clear text",
    		  schema = @Schema(type = "string"),
    		  required = true) 
      @QueryParam("password") String password) {
    return Response.ok()
        .entity("logged in user session:" + System.currentTimeMillis())
        .build();
  }

  @GET
  @Path("/logout")
  @Operation(
		  method = "get",
		  summary = "Logs out current logged in user session",
		  responses = {  
				  @ApiResponse(
						  description = "successful operation"
						  ) 
				  })
  public Response logoutUser() {
    return Response.ok().entity("").build();
  }
}
