# Swagger CXF Sample App

## Overview
This is a java project to build a stand-alone server which implements the OpenAPI Spec.  You can find out 
more about both the spec and the framework at http://swagger.io.

This sample creates an `application` context through the `applicationContext.xml`, allowing the `JAXRSServerFactoryBean` 
to reflect over property packages to discover swagger-enabled resources.  
This was originally contributed by [chadhahn](https://github.com/chadhahn) and adapted 
by [rvullriede](https://github.com/rvullriede).  Thank you for your contributions!

### To run (with Maven)
To run the server, run this task:

```
mvn package tomcat6:run
```

This will start Tomcat 6 embedded on port 8002.

### Testing the server
Once started, you can navigate to http://localhost:8002/api/swagger.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.

### Using the UI
There is an HTML5-based API tool bundled in this sample--you can view it it at [http://localhost:8002](http://localhost:8002). This lets you inspect the API using an interactive UI.  You can access the source of this code from [here](https://github.com/swagger-api/swagger-ui)

### Applying an API key
The sample app has an implementation of the Swagger ApiAuthorizationFilter.  This restricts access to resources
based on api-key.  There are two keys defined in the sample app:

<li>- default-key</li>

<li>- special-key</li>

When no key is applied, the "default-key" is applied to all operations.  If the "special-key" is entered, a
number of other resources are shown in the UI, including sample CRUD operations.
