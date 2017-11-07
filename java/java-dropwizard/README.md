# Swagger Sample App

## Overview
This is a java project to build a stand-alone server which implements the OpenAPI Spec.  You can find out 
more about both the spec and the framework at http://swagger.io.

This sample is based on dropwizard, and provides an example of integration of swagger into a dropwizard based app.

### To run (with Maven)
To run the server, run this task:

```
mvn package

java -jar target/swagger-java-dropwizard-sample-app-2.0.0.jar server conf/swagger-sample.yml

```

This will start Jetty embedded on port 8002.

### Testing the server

Once started, you can navigate to http://localhost:8080/openapi.json or http://localhost:8080/openapi.yaml to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.
