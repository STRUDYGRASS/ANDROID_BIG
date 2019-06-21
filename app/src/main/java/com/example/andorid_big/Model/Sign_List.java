package com.example.andorid_big.Model;

import java.util.Date;

public class Sign_List {
    private String name = null;
    private String date = null;

    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
    }

    public Sign_List(String name,String date){
        this.name = name;
        this.date = date;
    }
}
