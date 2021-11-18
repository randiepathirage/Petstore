package com.example.petstore.petType;

import com.example.petstore.pet.Pet;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Pet_Type")
public class PetType {

        @Schema(required = true, description = "Pet Type id")
        @JsonProperty("pet_type_id")
        private Integer petTypeId;

        @Schema(required = true, description = "Pet type")
        @JsonProperty("pet_type")
        private String petType;

    public PetType(int petTypeId, String petType){
        this.petTypeId=petTypeId;
        this.petType=petType;
    }

    public PetType(){

    }

    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }
}
