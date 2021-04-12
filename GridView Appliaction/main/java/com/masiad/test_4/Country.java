package com.masiad.test_4;

import java.util.HashMap;
import java.util.HashSet;

public class Country {

    public String name;
    public String capitol;
    public int area;
    public int flag;

    public Country(String name, String capitol, int area, int flag){
        this.name = name;
        this.capitol = capitol;
        this.area = area;
        this.flag = flag;
    }

    public HashMap<String, Object> ConverToMap(){
        HashMap<String, Object> tempMap = new HashMap<>();
        tempMap.put("name", "Name: " + this.name);
        tempMap.put("area", "Area " + String.valueOf(this.area) );
        tempMap.put("flag", this.flag);

        return tempMap;
    }

}
