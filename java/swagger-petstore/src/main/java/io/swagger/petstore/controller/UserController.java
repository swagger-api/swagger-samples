/**
 * Copyright 2018 SmartBear Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.swagger.petstore.controller;

import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.petstore.data.UserData;
import io.swagger.petstore.model.User;
import io.swagger.petstore.utils.Util;
import org.apache.commons.lang.math.RandomUtils;

import javax.ws.rs.core.Response;
import java.util.Date;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaInflectorServerCodegen", date = "2017-04-08T15:48:56.501Z")
public class UserController {

    private static UserData userData = new UserData();

    public ResponseContext createUser(final RequestContext request, final User user) {
        if (user == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No User provided. Try again?");
        }

        userData.addUser(user);
        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity(user);
    }

    public ResponseContext createUser(final RequestContext request, final Long id, final String username,
                                      final String firstName, final String lastName, final String email,
                                      final String password, final String phone, final Integer userStatus) {
        final User user = UserData.createUser(id, username, firstName, lastName, email, phone, userStatus);
        return createUser(request, user);
    }

    public ResponseContext getUserByName(final RequestContext request, final String username) {
        if (username == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No username provided. Try again?");
        }

        final User user = userData.findUserByName(username);
        if (user == null) {
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("User not found");
        }

        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity(user);
    }

    public ResponseContext createUsersWithArrayInput(final RequestContext request, final User[] users) {
        if (users == null || users.length == 0) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No User provided. Try again?");
        }

        for (final User user : users) {
            userData.addUser(user);
        }

        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity(users);
    }

    public ResponseContext createUsersWithListInput(final RequestContext request, final User[] users) {
        if (users == null || users.length == 0) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No User provided. Try again?");
        }

        for (final User user : users) {
            userData.addUser(user);
        }

        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity(users);
    }

    public ResponseContext loginUser(final RequestContext request, final String username, final String password) {
        Date date = new Date(System.currentTimeMillis() + 3600000);
        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .header("X-Rate-Limit", String.valueOf(5000))
                .header("X-Expires-After", date.toString())
                .entity("Logged in user session: " + RandomUtils.nextLong());
    }

    public ResponseContext logoutUser(final RequestContext request) {
        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity("User logged out");

    }

    public ResponseContext deleteUser(final RequestContext request, final String username) {
        if (username == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No username provided. Try again?");
        }

        userData.deleteUser(username);

        final User user = userData.findUserByName(username);

        if (null == user) {
            return new ResponseContext()
                    .contentType(Util.getMediaType(request))
                    .entity(user);
        } else {
            return new ResponseContext().status(Response.Status.NOT_MODIFIED).entity("User couldn't be deleted.");
        }
    }

    public ResponseContext updateUser(final RequestContext request, final String username, final User user) {
        if (username == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No Username provided. Try again?");
        }

        if (user == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No User provided. Try again?");
        }

        final User existingUser = userData.findUserByName(username);

        if (existingUser == null) {
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("User not found");
        }

        userData.deleteUser(existingUser.getUsername());
        userData.addUser(user);

        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity(user);
    }

    public ResponseContext updateUser(final RequestContext request, final String updatedUser, final Long id, final String username,
                                      final String firstName, final String lastName, final String email,
                                      final String password, final String phone, final Integer userStatus) {
        final User user = UserData.createUser(id, username, firstName, lastName, email, phone, userStatus);
        return updateUser(request, updatedUser, user);
    }
}

