package com.github.eliux.validations;

import com.github.eliux.dm.Person;

public class Preconditions {
    public static void checkNotNull(Person person){
        if(person.getName() == null || person.getName().trim().isEmpty()){
            throw new InvalidDataException("Invalid data of Person");
        }
    }
}
