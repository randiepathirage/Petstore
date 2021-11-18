package com.example.petstore.petType;

import java.util.ArrayList;
import java.util.List;


public class PetTypeData {

    public static List<PetType> types = new ArrayList<PetType>(){{
        add(new PetType(1,"Dog"));
        add(new PetType(2,"Cat"));
        add(new PetType(3,"Fish"));
    }};

    public static List<PetType> getInstance(){
        return types;
    }
}
