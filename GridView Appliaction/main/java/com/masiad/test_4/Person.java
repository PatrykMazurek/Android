package com.masiad.test_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {

    public String name;
    public String lastname;
    public int age;

    public Person(String name, String lastname, int age){
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public HashMap<String, String> ConvertToMap(){
        HashMap<String, String> tempMap = new HashMap<>();
        tempMap.put("name", this.name +  " " + this.lastname);
        tempMap.put("age", "Age " + this.age);
        return tempMap;
    }
}
