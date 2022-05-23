package com.example.baksombi.model;

import com.example.baksombi.helper.HttpHelper;

import java.util.Arrays;
import java.util.List;

public class Category {
    private String enName;
    private String frName;
    private String imgURL;
    private String id;
    private String[] animals;

    public String getEnName() {
        return enName;
    }

    public String getFrName() {
        return frName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getAnimals() {
        return animals;
    }

    public void setAnimals(String[] animals) {
        this.animals = animals;
    }

    public Category(){}

    public List<Category> getCategories() throws Exception{
        List<Category> categories = Arrays.asList((Category[])HttpHelper.getInstance().get("/categories", Category[].class,null));
        return categories;
    }

    public Category getCategory() throws Exception{
        System.out.println(id);
        return (Category)HttpHelper.getInstance().get("/categories/"+id,Category.class, null);
    }
    public String getName(String language){
        if(language.toLowerCase().equals("fr")){
            return this.frName;
        }
        return this.enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public void setFrName(String frName) {
        this.frName = frName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
