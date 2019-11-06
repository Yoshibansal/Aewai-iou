package com.example.aewai_iou;

public class Product {

    String id;
    String name;
    String about;
    String number;

    public Product(){}

    public Product(String id, String name, String about,String number) {

        this.id = id;
        this.name = name;
        this.about = about;
        this.number = number;
    }

    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public String getAbout() {

        return about;
    }

    public  String getNumber(){

        return number;

    }
}