package com.example.petstore.pet;

import java.util.ArrayList;
import java.util.List;

public class PetData {

    public static List<Pet> pets = new ArrayList<Pet>(){{
        add(new Pet(1,"1","boola",5));
        add(new Pet(2,"1","sudda",4));
        add(new Pet(3,"1","boola",5));
    }};

    public static List<Pet> getInstance(){
        return pets;
    }
}
