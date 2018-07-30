## Spring Boot Jersey Inflector Sample

A simple example demonstrating how to use Swagger Inflector with Spring Boot. This project integrates both Spring Boot Actuator and the Spring Boot Jersey integration. Because Actuator is built upon SpringMVC, [Jersey and Actuator must listen on different endpoints](http://docs.spring.io/spring-boot/docs/current/reference/html/howto-actuator.html#howto-use-actuator-with-jersey). Please see the `applictaion.yaml` for how this is accomplished. 


The project uses [Gradle](https://gradle.org/) for its build system and you can build the project by running:

	./gradlew build

You can also run the app using the [Spring Boot Gradle Plugin](http://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-gradle-plugin.html) like so: 

	./gradlew bootRun

The swagger definition will be available at the following URI:

[http://localhost:8080/api/v2/swagger.json](http://localhost:8080/api/v2/swagger.json)

The Spring Boot Actuator endpoints are avilable here:

[http://localhost:8080/info](http://localhost:8080/info)
[http://localhost:8080/env](http://localhost:8080/env)

Have fun!

