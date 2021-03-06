package com.example.petstore.pet;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.petstore.petType.PetType;
import com.example.petstore.petType.PetTypeData;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/pets")
@Produces("application/json")
public class PetController {

    List<Pet> pets = PetData.getInstance();

    //add new pet to the list........................................
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All pets", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            ))
    })
    @POST
    public Response addPet(@RequestBody(required = true) Pet pet) {
        // Assumed that pet store gives pet id as an input when adding a new pet to the system, and it is unique to pet
        pets.add(pet);
        return Response.ok(pet).build();
    }

    //update pet....................................................
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            @APIResponse(responseCode = "404", description = "Pet Id is not found")})
    @PUT
    @Path("{petId}")
    public Response updatePet(
            @PathParam("petId") int petId,
            @RequestBody(required = true) Pet pet) {
        System.out.println(pet.getPetName());

        int pos = -1;
        for (int i = 0; i < pets.size(); i++) {

            if (pets.get(i).getPetId() == petId) {
                pos = i;
                break;
            }
        }

        System.out.println(pos);

        if (pos > -1) {
            Pet tempPet = pets.get(pos);
            if (!tempPet.getPetName().equals(pet.getPetName())) {
                pets.get(pos).setPetName(pet.getPetName());
            }
            if (pet.getPetAge() != tempPet.getPetAge()) {
                pets.get(pos).setPetAge(pet.getPetAge());
            }
            if (pet.getPetType() != tempPet.getPetType()) {
                pets.get(pos).setPetType(pet.getPetType());
            }
            return Response.ok("Successfully updated").build();
        } else {
            return Response.status(404, "Error can't find the petId").build();
        }
    }

    //delete pet....................................................
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "pet Id deleted"),
            @APIResponse(responseCode = "404", description = "Pet Id is not found")})
    @DELETE
    @Path("{petId}")
    public Response deletePet(@PathParam("petId") int petId) {
        int pos = -1;
        for (int i = 0; i < pets.size(); i++) {
            Pet pet1 = pets.get(i);
            if (pet1.getPetId().equals(petId)) {
                pos = i;
                break;
            }
        }
        if (pos > -1) {
            pets.remove(pos);
            return Response.ok("Successfully deleted petId " + petId).build();
        }

        return Response.status(404, "Cant find the petId " + petId).build();
    }

    // get all the pets in list....................................
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet")))})
    @GET
    public Response getPets() {
        return Response.ok(pets).build();
    }

//    @GET
//    @Path("{petId}")
//    public Response getPet(@PathParam("petId") int petId) {
//        if (petId < 0) {
//            return Response.status(Status.NOT_FOUND).build();
//        }
//        return Response.ok(pets.get(petId-1)).build();
//    }

    // find pets by name , type, age and id...............................
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "success", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found")})
    @GET
    @Path("/find")
    public Response getPet(
            @DefaultValue("null") @QueryParam("petName") String petName,
            @DefaultValue("null") @QueryParam("petType") String petType,
            @DefaultValue("-1") @QueryParam("petAge") int petAge,
            @DefaultValue("-1") @QueryParam("petId") int petId) {
        if (!petName.equals("null")) {

            System.out.print("petName: ");
            System.out.println(petName);
            return Response.ok(
                    pets.stream()
                            .filter(pet -> pet.getPetName().equals(petName)).collect(Collectors.toList())
            ).build();
        } else if (!petType.equals("null")) {

            System.out.print("petType: ");
            System.out.println(petType);
            return Response.ok(
                    pets.stream()
                            .filter(pet -> pet.getPetType().equals(petType)).collect(Collectors.toList())
            ).build();
        } else if (petAge > 0) {

            System.out.print("petAge: ");
            System.out.println(petAge);
            return Response.ok(
                    pets.stream()
                            .filter(pet -> pet.getPetAge().equals(petAge)).collect(Collectors.toList())
            ).build();
        } else if (petId > 0) {
            System.out.print("petId: ");
            System.out.println(petId);
            return Response.ok(
                    pets.stream().filter(pet -> pet.getPetId().equals(petId)).collect(Collectors.toList())
            ).build();
        } else {
            return null;
        }
    }
}
