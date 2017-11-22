/*
 *  Copyright 2016 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.swagger.samples.inflector.springboot.controllers;

import com.github.javafaker.Faker;
import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.samples.inflector.springboot.models.Category;
import io.swagger.samples.inflector.springboot.models.Pet;
import io.swagger.samples.inflector.springboot.models.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response.Status;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class SampleController {

    Faker faker = new Faker();

    public ResponseContext getPetById(RequestContext requestContext, Long id)
    {
        Pet pet = new Pet();
        pet.setId(id);
        pet.setCategory(new Category(16, faker.cat().breed()));
        pet.setName(faker.cat().name());
        pet.getTags().add(new Tag(21,faker.cat().registry()));
        pet.setStatus("active");
        return new ResponseContext().status(Status.OK)
                                    .entity(pet);
    }


    public ResponseContext addPet(RequestContext request, Pet body) {
        return new ResponseContext()
                .status(Status.OK)
                .entity(body);
    }

    public ResponseContext uploadFile(RequestContext request, Long petId, String additionalMetadata, java.io.InputStream file) {
        ByteArrayOutputStream outputStream;
        try {
            outputStream = new ByteArrayOutputStream();
            IOUtils.copy(file, outputStream);
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
