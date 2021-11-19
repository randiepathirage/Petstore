package com.example.petstore.pet;

import java.util.ArrayList;
import java.util.List;

public class PetData {

    public static List<Pet> pets = new ArrayList<Pet>(){{
        add(new Pet(1,"dog","boola",5));
        add(new Pet(2,"cat","sudda",4));
        add(new Pet(3,"dog","boola",2));
    }};

    public static List<Pet> getInstance(){
        return pets;
    }
}
