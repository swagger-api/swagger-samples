# Swagger Sample App

This is the pet store sample hosted at https://petstore.swagger.io!

## Overview
This is a java project to build a stand-alone server which implements the OpenAPI Spec.  You can find out 
more about both the spec and the framework at http://swagger.io. 

### To run in docker

```
mvn package
docker build -t petstore:dev .
docker run -d -e SWAGGER_HOST=http://localhost -e SWAGGER_BASE_PATH=/v2 -p 8080:8080 petstore:dev
```

### Testing the server
Once started, you can navigate to http://localhost:8080/v2/swagger.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.

### Using the UI
There is an HTML5-based API tool bundled in this sample--you can view it it at [http://localhost:8080](http://localhost:8080). This lets you inspect the API using an interactive UI.  You can access the source of this code from [here](https://github.com/swagger-api/swagger-ui)

### Applying an API key
The sample app has an implementation of the Swagger ApiAuthorizationFilter.  This restricts access to resources
based on api-key.  There are two keys defined in the sample app:

<li>- default-key</li>

<li>- special-key</li>

When no key is applied, the "default-key" is applied to all operations.  If the "special-key" is entered, a
number of other resources are shown in the UI, including sample CRUD operations.
