package io.swagger.petstore.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.oas.inflector.models.RequestContext;
import io.swagger.oas.inflector.models.ResponseContext;
import io.swagger.petstore.data.PetData;
import io.swagger.petstore.model.Pet;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaInflectorServerCodegen", date = "2017-04-08T15:48:56.501Z")
public class PetController {

    private static PetData petData = new PetData();

    public ResponseContext findPetsByStatus(final RequestContext request, final String status) {
        if (status == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No status provided.  Try again?");
        }

        final List<Pet> petByStatus = petData.findPetByStatus(status);

        if (petByStatus == null) {
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("Pets not found");
        }


        final MediaType outputType = getMediaType(request);

        return new ResponseContext()
                .contentType(outputType)
                .entity(petByStatus);
    }

    public ResponseContext getPetById(final RequestContext request, final Long petId) {
        if (petId == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No petId provided.  Try again?");
        }

        if (petId == null) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity("Invalid Status");
        }

        final Pet pet = petData.getPetById(petId);

        final MediaType outputType = getMediaType(request);
        if (null != pet) {
            return new ResponseContext()
                    .contentType(outputType)
                    .entity(pet);
        } else {
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("Pet not found");
        }

    }

    public ResponseContext updatePetWithForm(final RequestContext request, final Long petId, final String name, final String status) {
        if (petId == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No Pet provided.  Try again?");
        }

        if (name == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No Name provided.  Try again?");
        }

        final MediaType outputType = getMediaType(request);
        final ObjectMapper objectMapper = new ObjectMapper();

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
                    .entity("No petId provided.  Try again?");
        }

        if (petId == null) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity("Invalid Status");
        }

        petData.deletePetById(petId);

        final MediaType outputType = getMediaType(request);

        final Pet pet = petData.getPetById(petId);

        if (null == pet) {
            return new ResponseContext()
                    .contentType(outputType)
                    .entity(pet);
        } else {
            return new ResponseContext().status(Response.Status.NOT_MODIFIED).entity("Pet couldn't be deleted.");
        }

    }

    public ResponseContext uploadFile(final RequestContext request, final Long petId, final String apiKey, final File file) {
        if (petId == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No petId provided.  Try again?");
        }

        if (petId == null) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity("Invalid Status");
        }

        final Pet existingPet = petData.getPetById(petId);
        if (existingPet == null) {
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("Pet not found");
        }

        existingPet.getPhotoUrls().add(file.getAbsolutePath());
        petData.deletePetById(existingPet.getId());
        petData.addPet(existingPet);

        final MediaType outputType = getMediaType(request);

        final Pet pet = petData.getPetById(petId);

        if (null == pet) {
            return new ResponseContext()
                    .contentType(outputType)
                    .entity(pet);
        } else {
            return new ResponseContext().status(Response.Status.NOT_MODIFIED).entity("Pet couldn't be deleted.");
        }

    }

    public ResponseContext addPet(final RequestContext request, final JsonNode pet) {
        if (pet == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No Pet provided.  Try again?");
        }

        final MediaType outputType = getMediaType(request);
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final Pet convertedPet = objectMapper.readValue(pet.toString(), Pet.class);
            petData.addPet(convertedPet);

            return new ResponseContext()
                    .contentType(outputType)
                    .entity(pet);
        } catch (IOException e) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity(pet);
        }
    }

    public ResponseContext updatePet(final RequestContext request, final JsonNode pet) {
        if (pet == null) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No Pet provided.  Try again?");
        }

        final MediaType outputType = getMediaType(request);
        final ObjectMapper objectMapper = new ObjectMapper();

        try {
            final Pet petJ = objectMapper.readValue(pet.toString(), Pet.class);
            final Pet existingPet = petData.getPetById(petJ.getId());
            if (existingPet == null) {
                return new ResponseContext().status(Response.Status.NOT_FOUND).entity("Pet not found");
            }

            petData.deletePetById(existingPet.getId());
            petData.addPet(petJ);

            return new ResponseContext()
                    .contentType(outputType)
                    .entity(pet);
        } catch (IOException e) {
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity(pet);
        }
    }

    public ResponseContext findPetsByTags(final RequestContext request, final List<String> tags) {
        if (tags == null || tags.size() == 0) {
            return new ResponseContext()
                    .status(Response.Status.BAD_REQUEST)
                    .entity("No tags provided.  Try again?");
        }

        final List<Pet> petByStatus = petData.findPetByTags(tags);
        final MediaType outputType = getMediaType(request);

        return new ResponseContext()
                .contentType(outputType)
                .entity(petByStatus);
    }

    private MediaType getMediaType(RequestContext request) {
        MediaType outputType = MediaType.APPLICATION_JSON_TYPE;

        boolean isJsonOK = false;
        boolean isYamlOK = false;

        final MediaType yamlMediaType = new MediaType("application", "yaml");

        for (final MediaType mediaType : request.getAcceptableMediaTypes()) {
            if (mediaType.equals(MediaType.APPLICATION_JSON_TYPE)) {
                isJsonOK = true;
            } else if (mediaType.equals(yamlMediaType)) {
                isYamlOK = true;
            }
        }

        if (isYamlOK && !isJsonOK) {
            outputType = yamlMediaType;
        }

        return outputType;
    }
}

