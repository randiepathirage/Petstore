package com.example.petstore.petType;

import com.example.petstore.pet.Pet;
import com.example.petstore.pet.PetData;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/types")
@Produces("application/json")
public class PetTypeController {

    List<PetType> types = PetTypeData.getInstance();

    // get all the pet types in list....................................
    @GET
    public Response getTypes() {
        System.out.println(types.get(1).getPetType());
        return Response.ok(types).build();
    }

    //add new pet type to the list........................................
    @POST
    public Response addType(@RequestBody (required = true) PetType type) {
        types.add(type);
        return Response.ok(type).build();
    }

    //update  pet type ...................................................
    @PUT
    @Path("{typeId}")
    public Response updateType(
            @PathParam("typeId") int petTypeId,
            @RequestBody(required = true) PetType type)
    {

        int pos = -1;
        for(int i=0; i<types.size() ;i++){
            PetType type1 = types.get(i);
            if(type1.getPetTypeId().equals(petTypeId)){
                pos = i;
                break;
            }
        }

        System.out.println(pos);

        if(pos>-1) {
            types.get(pos).setPetType(type.getPetType());
            return Response.ok("Successfuly updated").build();
        }else {
            return Response.status(204, " Error can't find the pet type Id").build();
        }
    }

    //delete pet type....................................................
    @DELETE
    @Path("{typeId}")
    public Response getPets( @PathParam("typeId") int petTypeId) {
        int pos = -1;
        for(int i=0; i<types.size() ;i++){
            PetType type1 = types.get(i);
            if(type1.getPetTypeId().equals(petTypeId)){
                pos = i;
                break;
            }
        }
        if(pos>-1) {
            types.remove(pos);
            return Response.ok("Successfuly deleted pet type "+ petTypeId).build();
        }

        return Response.ok("Cant find the pet type Id "+petTypeId).build();
    }
}
