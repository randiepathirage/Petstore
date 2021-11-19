package com.example.petstore.petType;

import java.util.ArrayList;
import java.util.List;


public class PetTypeData {

    public static List<PetType> types = new ArrayList<PetType>(){{
        add(new PetType(1,"dog"));
        add(new PetType(2,"cat"));
        add(new PetType(3,"fish"));
    }};

    public static List<PetType> getInstance(){
        return types;
    }
}
