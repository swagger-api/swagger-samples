package security

import io.swagger.model._
import io.swagger.core.filter.AbstractSpecFilter
import io.swagger.models.Operation
import io.swagger.models.parameters.Parameter
import play.api.Logger

class AuthorisationFilter extends AbstractSpecFilter {

  override def isOperationAllowed(operation: Operation, api: ApiDescription, params: java.util.Map[String, java.util.List[String]], cookies: java.util.Map[String, String], headers: java.util.Map[String, java.util.List[String]]): Boolean = {
    checkKey(params, headers) match {
      case true => true
      case false => {
        Logger("swagger").debug("authenticated: false  - method: %s - path: %s".format(api.getMethod, api.getPath))
        if(api.getMethod.toLowerCase() == "get" && api.getPath.indexOf("/admin") != -1) true
        else if(api.getMethod.toLowerCase() == "get" && api.getPath.indexOf("/pet") != -1) true
        else if(api.getMethod.toLowerCase() == "get" && api.getPath.indexOf("/user") != -1) true
        else false
      }
    }
  }

  override def isParamAllowed(parameter: Parameter, operation: Operation, api: ApiDescription, params: java.util.Map[String, java.util.List[String]], cookies: java.util.Map[String, String], headers: java.util.Map[String, java.util.List[String]]): Boolean = {
    val isAuthorized = checkKey(params, headers)
    if(parameter.getAccess == Some("internal") && !isAuthorized) false
    else true
  }

  def checkKey(params: java.util.Map[String, java.util.List[String]], headers: java.util.Map[String, java.util.List[String]]): Boolean = {
    val apiKey = params.containsKey("api_key") match {
      case true => Some(params.get("api_key").get(0))
      case _ => {
        headers.containsKey("api_key") match {
          case true => Some(headers.get("api_key").get(0))
          case _ => None
        }
      }
    }

    apiKey match {
      case Some(key) if(key == "special-key") => true
      case _ => false
    }
  }
}
