package controllers

import models.User
import api._
import io.swagger.annotations._
import io.swagger.util.Json

import play.api.mvc._

@Api(value = "/user", description = "Operations about user")
class UserApiController extends BaseApiController {
  var userData = new UserData

  @ApiOperation(nickname = "createUser",
    value = "Create user", notes = "This can only be done by the logged in user.", httpMethod = "POST"  )
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "Created user object", required = true, dataType = "models.User", paramType = "body")))
  def createUser = Action { implicit request =>
    request.body.asJson match {
      case Some(e) => {
        val user = Json.mapper.readValue(e.toString, classOf[User])
        userData.addUser(user)
        JsonResponse(user)
      }
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "createUsersWithArrayInput",
    value = "Creates list of users with given input array", response = classOf[Void], httpMethod = "POST")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "List of user object", required = true, dataType = "array", paramType = "body")))
  def createUsersWithArrayInput = Action { implicit request =>
    request.body.asJson match {
      case Some(e) => {
        val users = Json.mapper.readValue(e.toString, classOf[Array[User]])
        users.foreach(user => userData.addUser(user))
        JsonResponse(users)
      }
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "createUsersWithListInput",
    value = "Creates list of users with given list input", response = classOf[Void], httpMethod = "POST")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "List of user object", required = true, dataType = "array", paramType = "body")))
  def createUsersWithListInput = Action { implicit request =>
    request.body.asJson match {
      case Some(e) => {
        val users = Json.mapper.readValue(e.toString, classOf[Array[User]])
        users.foreach(user => userData.addUser(user))
        JsonResponse(users)
      }
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "updateUser",
    value = "Updated user", notes = "This can only be done by the logged in user.", httpMethod = "PUT")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid username supplied"),
    new ApiResponse(code = 404, message = "User not found")))
  @ApiImplicitParams(Array(
    new ApiImplicitParam (name = "username", value = "name that need to be updated", required = true, dataType = "String", paramType = "path"),
    new ApiImplicitParam(name = "body", value = "Updated user object", required = true, dataType = "models.User", paramType = "body")))
  def updateUser(username: String) = Action { implicit request =>
    request.body.asJson match {
      case Some(e) => {
        val user = Json.mapper.readValue(e.toString, classOf[User])
        userData.addUser(user)
        JsonResponse(user)
      }
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "deleteUser",
    value = "Delete user", notes = "This can only be done by the logged in user.", httpMethod = "DELETE")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid username supplied"),
    new ApiResponse(code = 404, message = "User not found")))
  def deleteUser(
    @ApiParam(value = "The name that needs to be deleted", required = true) username: String) = Action { implicit request =>
    userData.removeUser(username)
    Ok
  }

  @ApiOperation(nickname = "getUserByName",
    value = "Get user by user name", response = classOf[models.User], httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid username supplied"),
    new ApiResponse(code = 404, message = "User not found")))
  def getUserByName(
    @ApiParam(value = "The name that needs to be fetched. Use user1 for testing. ", required = true) username: String) = Action { implicit request =>
    userData.findUserByName(username) match {
      case Some(user) => JsonResponse(user)
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "loginUser",
    value = "Logs user into the system", response = classOf[String], httpMethod = "GET")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid username and password combination")))
  def loginUser(
    @ApiParam(value = "The user name for login", required = true) username: String,
    @ApiParam(value = "The password for login in clear text", required = true) password: String) = Action { implicit request =>
    JsonResponse("logged in user session:" + System.currentTimeMillis())
  }

  @ApiOperation(nickname = "logoutUser",
    value = "Logs out current logged in user session", httpMethod = "GET")
  def logoutUser() = Action { implicit request =>
    Ok
  }
}

object UserApiController {}
