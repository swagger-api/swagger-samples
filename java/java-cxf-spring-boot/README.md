# Swagger Sample App

## Overview
This is a java project to build a stand-alone server which implements the OpenAPI Spec.  You can find out 
more about both the spec and the framework at http://swagger.io.

This sample is based on CXF and Spring-Boot, and provides an example of integration of swagger into a CXF based app, with config file based configuration/initialization

### To run (with Maven)
To run the server, run this task:

```
mvn spring-boot:run
```

This will start Tomcat embedded on port 8080.

### Testing the server
Once started, you can navigate to http://localhost:8080/petstore/openapi.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.

### Using the UI
There is an HTML5-based API tool bundled in this sample--you can view it it at [localhost:8080/services/api-docs?url=/services/openapi.yaml](localhost:8080/services/api-docs?url=/services/openapi.yaml). This lets you inspect the API using an interactive UI.  You can access the source of this code from [here](https://github.com/swagger-api/swagger-ui)

### Applying an API key
The sample app has an implementation of the Swagger ApiAuthorizationFilter.  This restricts access to resources
based on api-key.  There are two keys defined in the sample app:

`default-key`

`special-key`

When no key is applied, the "default-key" is applied to all operations.  If the "special-key" is entered, a
number of other resources are shown in the UI, including sample CRUD operations.
