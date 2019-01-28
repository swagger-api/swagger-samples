/**
 *  Copyright 2016 SmartBear Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.swagger.sample.bean;

import io.swagger.sample.data.PetData;
import io.swagger.sample.model.Pet;
import io.swagger.sample.exception.NotFoundException;
import io.swagger.sample.resource.PetResource;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.List;

public class PetResourceBean implements PetResource {
    static PetData petData = new PetData();

    @Override
    public Pet getPetById(Long petId) throws NotFoundException {
        Pet pet = petData.getPetById(petId);
        if (null != pet) {
            return pet;
        } else {
            throw new NotFoundException(404, "Pet not found");
        }
    }

    @Override
    public Response addPet(Pet pet) {
        petData.addPet(pet);
        return Response.ok().entity("SUCCESS").build();
    }

    @Override
    public Response updatePet(Pet pet) {
        petData.addPet(pet);
        return Response.ok().entity("SUCCESS").build();
    }

    @Override
    public Response findPetsByStatus(String status) {
        return Response.ok(new GenericEntity<List<Pet>>(petData.findPetByStatus(status)){}).build();
    }

    @Override
    @Deprecated
    public Response findPetsByTags(String tags) {
        return Response.ok(new GenericEntity<List<Pet>>(petData.findPetByTags(tags)){}).build();
    }
}
