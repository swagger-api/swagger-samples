package test

import io.swagger.models.Swagger
import io.swagger.parser.SwaggerParser

import org.specs2.mutable._
import play.api.Logger

import play.api.test._
import play.api.test.Helpers._

import scala.io._
import scala.collection.JavaConverters._

/*
    TODO swagger filters e.g. Authorization filter checking API key are not currently implemented
    Therefore all api tested do not have authorization, and return the same response in any case.
    Add a Security Filter testing api keys, and define it in swagger.filter property, then change
    exptected values accordingly.
    see e.g http://stackoverflow.com/questions/22545921/swagger-how-do-i-enable-the-api-key-in-my-play-application
 */
class IntegrationSpec extends Specification {


  "Application" should {
    "have the proper resource metadata" in {
      running(TestServer(3333)) {
      	val json = Source.fromURL("http://localhost:3333/api-docs").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
      	swagger.getSwagger must_==("2.0")
        "2.0" must_==("2.0")
      }
    }

    "contain all apis defined in the routes without api key" in {
    	running(TestServer(3333)) {
      	val json = Source.fromURL("http://localhost:3333/api-docs").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
		    swagger.getPaths.size must_==(13)
		    (swagger.getPaths.keySet().asScala &
		      Set(
            "/pet",
		        "/user")
		      ).size must_==(2)
      }
    }

    "contain all operations defined in the pet resource without api key" in {
    	running(TestServer(3333)) {
      	val json = Source.fromURL("http://localhost:3333/api-docs/pet").mkString
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
      val json = Source.fromURL("http://localhost:3333/api-docs/pet").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
        (swagger.getPaths.keySet().asScala &
          Set(
            "/pet/findByTags",
            "/pet/findByStatus",
            "/pet/{id}")
          ).size must_==(3)
      }
    }

    /*
     * TODO implement authorization filters and decomment these tests methods
     */

    /*
    "no apis from store resource without valid api key" in {
      running(TestServer(3333)) {
        val json = Source.fromURL("http://localhost:3333/api-docs/store").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
        Option(swagger.getPaths.keySet().asScala) must_==(Some(List.empty))
      }
    }

    "no models from store resource without valid api key" in {
      running(TestServer(3333)) {
        val json = Source.fromURL("http://localhost:3333/api-docs/store").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
        doc.models must_==None
      }
    }
    */

    "contain apis from store resource valid api key" in {
      running(TestServer(3333)) {
        val json = Source.fromURL("http://localhost:3333/api-docs/store?api_key=special-key").mkString
        //Logger.debug("json: \n" + json)
        val swagger: Swagger = new SwaggerParser().parse(json)
        swagger.getPaths.size must_==(2)
      }
    }

    "contain correct models from store resource valid api key" in {
      running(TestServer(3333)) {
        val json = Source.fromURL("http://localhost:3333/api-docs/store?api_key=special-key").mkString
        val swagger: Swagger = new SwaggerParser().parse(json)
        swagger.getDefinitions.size must_==(5)
      }
    }

    "contain all operations defined in the pet resource with api key" in {
    	running(TestServer(3333)) {
      	val json = Source.fromURL("http://localhost:3333/api-docs/pet?api_key=special-key").mkString
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
