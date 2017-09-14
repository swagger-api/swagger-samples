package io.swagger.sample.controllers;

import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.sample.models.Pet;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.core.Response.Status;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SampleController {
    public ResponseContext addPet(RequestContext request, Pet body) {

      
        return new ResponseContext()
                .status(Status.OK)
                .entity(body);
    }
}
