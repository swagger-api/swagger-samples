package io.swagger.sample.resource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;

@Path("/Tokens")
public class TokenResource{
    @Resource
    protected WebServiceContext webServiceContext;


    @GET
    @Path("/ForgotPassword/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPassword(@PathParam("userId") String userId, @Context HttpServletRequest request) throws Exception {
        //impl
        return null;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response getToken(@FormParam("userid") String userId, @FormParam("password") String password, @Context HttpServletRequest request) throws Exception{
        //impl
        return null;
    }
}