package controllers

import models.Order
import api._
import io.swagger.annotations._
import io.swagger.util.Json

import play.api.mvc._

import scala.collection.JavaConverters._

@Api(value = "/store", description = "Operations about store")
class StoreApiController extends BaseApiController {
  var storeData = new StoreData

  @ApiOperation(nickname = "getOrderById",
    value = "Find purchase order by ID", notes = "For valid response try integer IDs with value <= 5. " +
    "Anything above 5 or nonintegers will generate API errors", response = classOf[models.Order], httpMethod = "GET")
  @ApiResponses(value = Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message = "Order not found")))
  def getOrderById(
    @ApiParam(value = "ID of pet that needs to be fetched", required = true) orderId: String) = Action { implicit request =>
    storeData.findOrderById(getLong(0, 10000, 0, orderId)) match {
      case Some(order) => JsonResponse(order)
      case _ => JsonResponse(new value.ApiResponse(404, "Order not found"), 404)
    }
  }

  @ApiOperation(nickname = "getOrders",
    value = "Gets orders in the system", response = classOf[models.Order], httpMethod = "GET", responseContainer = "List")
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "No Orders found")))
  def getOrders(@ApiImplicitParam(value = "Get all orders or only those which are complete", dataType = "Boolean", required = true) isComplete: Boolean) = Action { implicit request =>
    val orders: java.util.List[Order] = storeData.orders.toList.asJava
    JsonResponse(orders)
  }

  @ApiOperation(nickname = "placeOrder",
    value = "Place an order for a pet", response = classOf[Void], httpMethod = "POST")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid order")))
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "order placed for purchasing the pet", required = true, dataType = "models.Order", paramType = "body")))
  def placeOrder = Action { implicit request =>
    request.body.asJson match {
      case Some(e) => {
        val order = Json.mapper.readValue(e.toString, classOf[Order])
        storeData.placeOrder(order)
        JsonResponse(order)
      }
      case None => JsonResponse(new value.ApiResponse(400, "Invalid input"))
    }
  }

  @ApiOperation(nickname = "deleteOrder",
    value = "Delete purchase order by ID", notes = "For valid response try integer IDs with value < 1000. " +
    "Anything above 1000 or nonintegers will generate API errors", httpMethod = "DELETE")
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid ID supplied"),
    new ApiResponse(code = 404, message = "Order not found")))
  def deleteOrder(
    @ApiParam(value = "ID of the order that needs to be deleted", required = true) orderId: String) = Action {
    implicit request =>
      storeData.deleteOrder(getLong(0, 10000, 0, orderId))
      Ok
  }

    @ApiOperation(value = "get Orders with query and implicit params as Option[Int] type",
      nickname = "getOrders",
      notes = "Returns orders",
      response = classOf[models.Order],
      responseContainer = "List",
      httpMethod = "GET")
    @ApiImplicitParams(Array(
      new ApiImplicitParam(name = "limitimplicit", value = "Number of orders", required = false, dataType = "Option[Int]", paramType = "query")))
    def listOrdersOption(limit:  Option[Int]) = Action {
      request => Ok("test case")
    }
}

object StoreApiController {}
