package io.swagger.sample.controllers;

import io.swagger.inflector.models.RequestContext;
import io.swagger.inflector.models.ResponseContext;
import io.swagger.sample.models.User;

import javax.ws.rs.core.Response.Status;

public class UserController {
  public ResponseContext createUsersWithListInput(RequestContext request, User[] body) {
    int count = 0;

    if(body != null) {
      count = body.length;
    }
    System.out.println("created " + count + " users");

    return new ResponseContext()
      .status(Status.OK);
  }
}