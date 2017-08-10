package io.swagger.sample.servlet;

import io.swagger.sample.model.SampleData;
import io.swagger.util.Json;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.oas.models.Components;
import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.Operation;
import io.swagger.oas.models.PathItem;
import io.swagger.oas.models.Paths;
import io.swagger.oas.models.info.Info;
import io.swagger.oas.models.media.Content;
import io.swagger.oas.models.media.MediaType;
import io.swagger.oas.models.media.Schema;
import io.swagger.oas.models.responses.ApiResponse;
import io.swagger.oas.models.responses.ApiResponses;
import io.swagger.oas.web.OpenAPIConfig;
//import io.swagger.oas.web.OpenAPIConfig;
import io.swagger.oas.web.OpenAPIConfigBuilder;
//@SwaggerDefinition(
//        info = @Info(
//                description = "This is a sample server",
//                version = "1.0.0",
//                title = "Swagger Sample Servlet",
//                termsOfService = "http://swagger.io/terms/",
//                contact = @Contact(name = "Sponge-Bob", email = "apiteam@swagger.io", url = "http://swagger.io"),
//                license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
//        ),
//        consumes = {"application/json", "application/xml"},
//        produces = {"application/json", "application/xml"},
//        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
//        tags = {@Tag(name = "users", description = "Operations about user")}
//)
//@Api(value = "/sample/users", description = "gets some data from a servlet")
public class SampleServlet extends HttpServlet implements OpenAPIConfigBuilder {

//    @ApiOperation(httpMethod = "GET", value = "Resource to get a user", response = SampleData.class, nickname = "getUser")
//    @ApiResponses({@ApiResponse(code = 400, message = "Invalid input", response = io.swagger.sample.model.ApiResponse
//            .class)})
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "User ID", required = true, dataType = "integer", paramType =
//                    "query"),
//            @ApiImplicitParam(name = "name", value = "User's name", required = true, dataType = "string", paramType =
//                    "query"),
//            @ApiImplicitParam(name = "email", value = "User's email", required = true, dataType = "string", paramType
//                    = "query"),
//            @ApiImplicitParam(name = "age", value = "User's age", required = true, dataType = "integer", paramType =
//                    "query"),
//            @ApiImplicitParam(name = "dateOfBirth", value = "User's date of birth, in dd-MM-yyyy format", required =
//                    true, dataType = "java.util.Date", paramType = "query")})
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result;
        try {
            final Integer id = Integer.parseInt(request.getParameter("id"));
            final String name = request.getParameter("name");
            final String email = request.getParameter("email");
            final int age = Integer.parseInt(request.getParameter("age"));
            final Date dateOfBirth = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("dateOfBirth"));
            result = Json.pretty(new SampleData(id, name, email, age, dateOfBirth));
        } catch (Exception ex) {
            result = Json.pretty(new io.swagger.sample.model.ApiResponse(400, ex.getMessage()));
        }

        response.getOutputStream().write(result.getBytes(StandardCharsets.UTF_8));
    }

	@Override
	public OpenAPIConfig build(Map<String, Object> arg0) {
		OpenAPI oai = new OpenAPI();
		
		Info info = new Info();
		info.setTitle("Swagger Sample Servlet");
		info.setVersion("1.0.0");
		oai.setInfo(info);
		
		Components components = new Components();
		Map<String, Schema> schemas = new HashMap<String, Schema>();
		List<String> required = new ArrayList<String>();
		required.add("name");
		required.add("id");
		required.add("email");
		required.add("age");
		
		Map<String, Schema> properties = new HashMap<String, Schema>();
		properties.put("name",  new Schema()
				.type("string"));
		properties.put("id", new Schema()
				.type("integer")
				.format("int32"));
		properties.put("email", new Schema()
				.type("string"));
		properties.put("age", new Schema()
				.type("integer")
				.format("int32"));
		
		schemas.put("User", new Schema()
				.type("object")
				.required(required)
				.properties(properties));
		
		components.setSchemas(schemas);
		Paths paths = new Paths();
		PathItem pathItem = new PathItem();
		
		Operation operation = new Operation();
		operation.setDescription("Resource to get a user");
		
                ApiResponses responses = new ApiResponses();
		
                ApiResponse item = new ApiResponse();
		item.setDescription("Successful");
		
                Content content = new Content();
                MediaType mediaType = new MediaType();
		
                Schema schema = new Schema();
		schema.set$ref("#/components/schemas/User");
		
                mediaType.setSchema(schema);
                content.addMediaType("application/json", mediaType);
		
                item.setContent(content);
		
		operation.setResponses(responses);

                List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(new Parameter()
				.name("name")
				.required(true)
				.in("query")
				.description("Name of the user")
				.schema(new Schema()
						.type("string")));
		parameters.add(new Parameter()
                        .name("id")
                        .required(true)
                        .in("query")
                        .description("Id of the user")
                        .schema(new Schema()
                                .type("integer")
                                .format("int32")));
		parameters.add(new Parameter()
                        .name("email")
                        .required(true)
                        .in("query")
                        .description("Email of the user")
                        .schema(new Schema()
                                .type("string")));
		parameters.add(new Parameter()
                        .name("age")
                        .required(true)
                        .in("query")
                        .description("Age of the user")
                        .schema(new Schema()
                                .type("integer")
                                .format("int32")));
		operation.setParameters(parameters);
		
                pathItem.get(operation);
		paths.addPathItem("/sample/users", pathItem);
		oai.setPaths(paths);
		
		OpenAPIConfig openAPIConfig = new OpenAPIConfig () {

			@Override
			public String getFilterClass() {
				return null;
			}

			@Override
			public Set<String> getIgnoredClasses() {	
				return null;
			}

			@Override
			public OpenAPI getOpenAPI() {
				return oai;
			}

			@Override
			public Map<String, Object> getOptions() {
				return null;
			}

			@Override
			public String getReaderClass() {
				return null;
			}

			@Override
			public Set<String> getResourceClasses() {
				return null;
			}

			@Override
			public Set<String> getResourcePackages() {
				return null;
			}

			@Override
			public String getScannerClass() {
				return null;
			}

			@Override
			public boolean isScanDisabled() {
				return false;
			}
		};
		return openAPIConfig;		
	}
}
