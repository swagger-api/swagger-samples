package io.swagger.sample.resource;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "My API", version = "1.2.3", description = "A sample API"),
        servers = @Server(url = "/openapi"))
public class Metadata {
}
