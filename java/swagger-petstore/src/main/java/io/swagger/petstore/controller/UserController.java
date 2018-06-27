package io.swagger.petstore.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.petstore.data.UserData;
import io.swagger.petstore.model.User;
import io.swagger.petstore.utils.Util;
import org.apache.commons.lang.math.RandomUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaInflectorServerCodegen", date = "2017-04-08T15:48:56.501Z")
public class UserController {

    private static UserData userData = new UserData();

    public ResponseContext createUser(final RequestContext request, final JsonNode user) {
        if (user == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No User provided. Try again?");
        }

        final MediaType outputType = Util.getMediaType(request);
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final User convertedUser = objectMapper.readValue(user.toString(), User.class);
            userData.addUser(convertedUser);

            return new ResponseContext()
                    .contentType(outputType)
                    .entity(user);
        } catch (IOException e) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity(user);
        }
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
        final MediaType outputType = Util.getMediaType(request);

        return new ResponseContext()
                .contentType(outputType)
                .entity(user);
    }

    public ResponseContext createUsersWithArrayInput(final RequestContext request, final JsonNode users) {
        if (users == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No User provided. Try again?");
        }

        final MediaType outputType = Util.getMediaType(request);
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final User convertedUser = objectMapper.readValue(users.toString(), User.class);
            userData.addUser(convertedUser);

            return new ResponseContext()
                    .contentType(outputType)
                    .entity(users);
        } catch (IOException e) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity(users);
        }
    }

    public ResponseContext loginUser(final RequestContext request, final String username, final String password) {
        if (username == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No username provided. Try again?");
        }

        final MediaType outputType = Util.getMediaType(request);
        final User user = userData.findUserByName(username);

        if (user == null) {
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("User not found");
        }

        if (password.equals(user.getPassword())) {
            return new ResponseContext()
                    .contentType(outputType)
                    .entity("Logged in user session: " + RandomUtils.nextLong());
        } else {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity("Wrong Password");
        }
    }

    public ResponseContext logoutUser(final RequestContext request) {
        final MediaType outputType = Util.getMediaType(request);

        return new ResponseContext()
                .contentType(outputType)
                .entity("User logged out");

    }

    public ResponseContext deleteUser(final RequestContext request, final String username) {
        if (username == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No username provided. Try again?");
        }

        userData.deleteUser(username);

        final MediaType outputType = Util.getMediaType(request);

        final User user = userData.findUserByName(username);

        if (null == user) {
            return new ResponseContext()
                    .contentType(outputType)
                    .entity(user);
        } else {
            return new ResponseContext().status(Response.Status.NOT_MODIFIED).entity("User couldn't be deleted.");
        }
    }

    public ResponseContext updateUser(final RequestContext request, final String username, final JsonNode user) {
        if (username == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No User provided. Try again?");
        }

        final MediaType outputType = Util.getMediaType(request);
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final User userJson = objectMapper.readValue(user.toString(), User.class);
            final User existingUser = userData.findUserByName(username);

            if (existingUser == null) {
                return new ResponseContext().status(Response.Status.NOT_FOUND).entity("User not found");
            }

            userData.deleteUser(existingUser.getUsername());
            userData.addUser(userJson);

            return new ResponseContext()
                    .contentType(outputType)
                    .entity(user);
        } catch (IOException e) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity(user);
        }
    }
}

