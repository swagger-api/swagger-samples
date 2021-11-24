/*
 * Copyright 2016 Jithu Menon
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.swagger.sample.resources;

import io.swagger.annotations.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Api("petstore")
@Path("/api/1.0")
@Produces(MediaType.APPLICATION_JSON)
@SwaggerDefinition(
        info = @Info(
                title="Swagger Petstore",
                version="1.0.0",
                description = "Example Pet Store app using Swagger, Jersey 1.X, and Governator"
        ),
        tags = {
            @Tag(name="petstore", description = "Petstore Operations"),
            @Tag(name="pet", description = "Everything about your Pets"),
            @Tag(name="store", description = "Access to Petstore orders"),
            @Tag(name="user", description = "Operations about users"),
        },
        produces = {MediaType.APPLICATION_JSON},
        consumes = {MediaType.APPLICATION_JSON}
)
public class RootResource {

    private final PetResource petResource;
    private final PetStoreResource petStoreResource;
    private final UserResource userResource;

    @Inject
    public RootResource(PetResource petResource,
                        PetStoreResource petStoreResource,
                        UserResource userResource) {
        this.petResource = petResource;
        this.petStoreResource = petStoreResource;
        this.userResource = userResource;
    }

    @GET
    @ApiOperation(value = "Saying hello", response = String.class)
    public String hello() {
        return String.format("{\"%s\":\"%s\"}", "hello", "world");
    }

    @Path("/pets")
    public PetResource getPetResource() {
        return petResource;
    }

    @Path("/store")
    public PetStoreResource getPetStoreResource() {
        return petStoreResource;
    }

    @Path("/user")
    public UserResource getUserResource() {
        return userResource;
    }
}
