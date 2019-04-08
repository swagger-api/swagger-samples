package test

import io.swagger.models.Swagger
import io.swagger.parser.SwaggerParser

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

import scala.io._
import scala.collection.JavaConverters._

class IntegrationSpec extends Specification {

  "Application" should {

    "have the proper resource metadata" in {
      running(TestServer(3333)) {
      	val json = Source.fromURL("http://localhost:3333/swagger.json").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
      	swagger.getSwagger must_==("2.0")
      }
    }

    "contain all apis defined in the routes without api key" in {
    	running(TestServer(3333)) {
      	val json = Source.fromURL("http://localhost:3333/swagger.json").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
		    swagger.getPaths.size must_==(6)
        var count = 0
        swagger.getPaths.keySet.asScala.map(path => {
          if (path.startsWith("/pet") || path.startsWith("/user")) count += 1
        })
        count must_==(6)
      }
    }

    "contain all operations defined in the pet resource without api key" in {
    	running(TestServer(3333)) {
      	val json = Source.fromURL("http://localhost:3333/pet/swagger.json").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
		    (swagger.getDefinitions.keySet().asScala &
		      Set(
		      	"Category",
		        "Tag",
		        "Pet")
		      ).size must_==(3)
      }
    }

    "contain models without api key" in {
      running(TestServer(3333)) {
      val json = Source.fromURL("http://localhost:3333/pet/swagger.json").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
        (swagger.getPaths.keySet().asScala &
          Set(
            "/pet/findByTags",
            "/pet/findByStatus",
            "/pet/{id}")
          ).size must_==(3)
      }
    }

    "no apis from store resource without valid api key" in {
      running(TestServer(3333)) {
        val json = Source.fromURL("http://localhost:3333/store/swagger.json").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
        Option(swagger.getPaths.keySet().asScala) must_==(Some(Set.empty))
      }
    }

    /*
     * TODO definitions auth filters dependent on path cannot be handled with current swagger specFilter mechanism
     */

    /*
    "no models from store resource without valid api key" in {
      running(TestServer(3333)) {
        val json = Source.fromURL("http://localhost:3333/api-docs/store").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
        swagger.getDefinitions.isEmpty must beTrue
      }
    }
    */

    "contain apis from store resource valid api key" in {
      running(TestServer(3333)) {
        val json = Source.fromURL("http://localhost:3333/store/swagger.json?api_key=special-key").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
        swagger.getPaths.size must_==(3)
      }
    }

    "contain correct models from store resource valid api key" in {
      running(TestServer(3333)) {
        val json = Source.fromURL("http://localhost:3333/store/swagger.json?api_key=special-key").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
        swagger.getDefinitions.size must_==(6)
      }
    }

    "contain all operations defined in the pet resource with api key" in {
    	running(TestServer(3333)) {
      	val json = Source.fromURL("http://localhost:3333/pet/swagger.json?api_key=special-key").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
		    (swagger.getPaths.keySet().asScala &
		      Set(
		      	"/pet/findByTags",
		        "/pet/findByStatus",
		        "/pet/{id}")
		      ).size must_==(3)
      }
    }
  }
}
