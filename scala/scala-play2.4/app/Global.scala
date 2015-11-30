import play.api._

import io.swagger.config._
import io.swagger.model._


object Global extends GlobalSettings {
  val info = new ApiInfo()
    .title("Swagger Sample App")
    .description("""This is a sample server Petstore server.  You can find out more about Swagger 
    at <a href="http://swagger.io">http://swagger.io</a> or on irc.freenode.net, #swagger.  For this sample,
    you can use the api key "special-key" to test the authorization filters""")
    // termsOfServiceUrl = "http://helloreverb.com/terms/",
    // contact = "apiteam@swagger.io", 
    // license = "Apache 2.0", 
    // licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.html")

  ConfigFactory.config.info = Some(info)
}