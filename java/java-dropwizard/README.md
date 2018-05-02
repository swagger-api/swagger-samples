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

This will start Dropwizard app on port 8080.

### Testing the server

Once started, you can navigate to http://localhost:8080/api/openapi.json or http://localhost:8080/api/openapi.yaml to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.

### Using the UI
There is an HTML5-based API tool bundled in this sample--you can view it it at [http://localhost:8080](http://localhost:8080). This lets you inspect the API using an interactive UI.  You can access the source of this code from [here](https://github.com/swagger-api/swagger-ui)
