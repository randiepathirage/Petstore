package com.example.petstore.petType;


import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/types")
@Produces("application/json")
public class PetTypeController {

    List<PetType> types = PetTypeData.getInstance();

    // get all the pet types in list....................................
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pet Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet_Type")))})
    @GET
    public Response getTypes() {
        System.out.println(types.get(1).getPetType());
        return Response.ok(types).build();
    }

    //add new pet type to the list........................................
    @APIResponses( value = {
            @APIResponse( responseCode = "200", description = "All pet Types", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON
            ) )
    } )
    @POST
    public Response addType(@RequestBody (required = true) PetType type) {
        types.add(type);
        return Response.ok(type).build();
    }

    //update  pet type ...................................................
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet type for id", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            @APIResponse(responseCode = "404", description = "Pet type Id is not found")})
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
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "pet type Id deleted"),
            @APIResponse(responseCode = "404", description = "Pet type Id is not found")})
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

        return Response.status(204, " Error can't find the pet type Id "+ petTypeId).build();
    }
}
