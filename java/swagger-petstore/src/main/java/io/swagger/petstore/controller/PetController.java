/**
 * Copyright 2018 SmartBear Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.swagger.petstore.controller;

import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.petstore.data.PetData;
import io.swagger.petstore.model.Category;
import io.swagger.petstore.model.Pet;
import io.swagger.petstore.model.Tag;
import io.swagger.petstore.utils.Util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaInflectorServerCodegen", date = "2017-04-08T15:48:56.501Z")
public class PetController {

    private static PetData petData = new PetData();

    public ResponseContext findPetsByStatus(final RequestContext request, final String status) {
        if (status == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No status provided. Try again?");
        }

        final List<Pet> petByStatus = petData.findPetByStatus(status);

        if (petByStatus == null) {
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("Pets not found");
        }

        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity(petByStatus);
    }

    public ResponseContext getPetById(final RequestContext request, final Long petId) {
        if (petId == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No petId provided. Try again?");
        }

        final Pet pet = petData.getPetById(petId);

        if (pet != null) {
            return new ResponseContext()
                    .contentType(Util.getMediaType(request))
                    .entity(pet);
        }

        return new ResponseContext().status(Response.Status.NOT_FOUND).entity("Pet not found");
    }

    public ResponseContext updatePetWithForm(final RequestContext request, final Long petId, final String name, final String status) {
        if (petId == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No Pet provided. Try again?");
        }

        if (name == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No Name provided. Try again?");
        }

        final MediaType outputType = Util.getMediaType(request);
        final Pet existingPet = petData.getPetById(petId);

        if (existingPet == null) {
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("Pet not found");
        }

        petData.deletePetById(existingPet.getId());
        existingPet.setName(name);
        existingPet.setStatus(status);
        petData.addPet(existingPet);

        return new ResponseContext()
                .contentType(outputType)
                .entity(existingPet);
    }

    public ResponseContext deletePet(final RequestContext request, final String apiKey, final Long petId) {
        if (petId == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No petId provided. Try again?");
        }

        petData.deletePetById(petId);

        final MediaType outputType = Util.getMediaType(request);

        final Pet pet = petData.getPetById(petId);

        if (null == pet) {
            return new ResponseContext()
                    .contentType(outputType)
                    .entity("Pet deleted");
        } else {
            return new ResponseContext().status(Response.Status.NOT_MODIFIED).entity("Pet couldn't be deleted.");
        }

    }

    public ResponseContext uploadFile(final RequestContext request, final Long petId, final String apiKey, final File file) {
        if (petId == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No petId provided. Try again?");
        }

        if (file == null) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity("No file uploaded");
        }

        final Pet existingPet = petData.getPetById(petId);
        if (existingPet == null) {
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("Pet not found");
        }

        existingPet.getPhotoUrls().add(file.getAbsolutePath());
        petData.deletePetById(existingPet.getId());
        petData.addPet(existingPet);

        final Pet pet = petData.getPetById(petId);

        if (null != pet) {
            return new ResponseContext()
                    .contentType(Util.getMediaType(request))
                    .entity(pet);
        } else {
            return new ResponseContext().status(Response.Status.NOT_MODIFIED).entity("Pet couldn't be updated.");
        }
    }

    public ResponseContext addPet(final RequestContext request, final Pet pet) {
        if (pet == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No Pet provided. Try again?");
        }

        petData.addPet(pet);

        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity(pet);
    }

    public ResponseContext addPet(final RequestContext request, final Long id, final String name, final Category category,
                                  final List<String> urls, final List<Tag> tags, final String status) {
        final Pet pet = PetData.createPet(id, category, name, urls, tags, status);
        return addPet(request, pet);
    }

    public ResponseContext updatePet(final RequestContext request, final Pet pet) {
        if (pet == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No Pet provided. Try again?");
        }

        final Pet existingPet = petData.getPetById(pet.getId());
        if (existingPet == null) {
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("Pet not found");
        }

        petData.deletePetById(existingPet.getId());
        petData.addPet(pet);

        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity(pet);
    }

    public ResponseContext updatePet(final RequestContext request, final Long id, final String name, final Category category,
                                  final List<String> urls, final List<Tag> tags, final String status) {
        final Pet pet = PetData.createPet(id, category, name, urls, tags, status);
        return updatePet(request, pet);
    }

    public ResponseContext findPetsByTags(final RequestContext request, final List<String> tags) {
        if (tags == null || tags.size() == 0) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No tags provided. Try again?");
        }

        final List<Pet> petByTags = petData.findPetByTags(tags);

        return new ResponseContext()
                .contentType(Util.getMediaType(request))
                .entity(petByTags);
    }
    
}

