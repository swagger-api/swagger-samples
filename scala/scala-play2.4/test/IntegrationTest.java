import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.route;
import static play.test.Helpers.running;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.junit.Test;

import play.libs.Json;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;

public class IntegrationTest {


  /**
   * Basic api docs test in Java
   *
   */
  @Test
  public void testBasicApiDocs() {

      running(fakeApplication(), new Runnable() {
          @Override
          public void run() {
              RequestBuilder request = new RequestBuilder();
              request = new RequestBuilder()
              .method(GET)
              .uri("/swagger.json");
              Result result = route(request);
              assertEquals(OK, result.status());
              JsonNode json = Json.parse(contentAsString(result));
              Swagger swagger = new SwaggerParser().read(json);
              assertEquals("2.0", swagger.getSwagger());
          }
      });
	  
  }
}
