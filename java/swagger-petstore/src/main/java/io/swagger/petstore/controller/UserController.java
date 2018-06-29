package io.swagger.petstore.controller;

import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.petstore.data.UserData;
import io.swagger.petstore.model.User;
import io.swagger.petstore.utils.Util;
import org.apache.commons.lang.math.RandomUtils;

import javax.ws.rs.core.Response;

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
        if (username == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No username provided. Try again?");
        }

        final User user = userData.findUserByName(username);

        if (user == null) {
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("User not found");
        }

        if (password.equals(user.getPassword())) {
            return new ResponseContext()
                    .contentType(Util.getMediaType(request))
                    .entity("Logged in user session: " + RandomUtils.nextLong());
        } else {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity("Wrong Password");
        }
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
}

