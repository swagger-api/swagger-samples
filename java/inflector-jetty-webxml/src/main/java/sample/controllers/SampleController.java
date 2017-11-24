package sample.controllers;

import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import sample.models.Pet;

import javax.ws.rs.core.Response.Status;

public class SampleController {
    public ResponseContext addPet(RequestContext request, Pet body) {

      
        return new ResponseContext()
                .status(Status.OK)
                .entity(body);
    }
}
