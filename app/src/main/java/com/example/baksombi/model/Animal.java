package com.example.baksombi.model;

import com.example.baksombi.helper.HttpHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Animal {
    private String id;
    private String imgURL;
    private String enName;
    private String frName;
    private String enHome;
    private String frHome;
    private String frFood;
    private String enReproduction;
    private String enFood;
    private String frReproduction;
    private String frWikipediaURL;
    private String enWikipediaURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrHome() {
        return frHome;
    }

    public void setFrHome(String frHome) {
        this.frHome = frHome;
    }

    public String getName(String lang){
        if(lang.equals("fr")){
            return frName;
        }
        return enName;
    }

    public String getHome(String lang){
        if(lang.equals("fr")){
            return frHome;
        }
        return enHome;
    }

    public String getFood(String lang){
        if(lang.equals("fr")){
            return frFood;
        }
        return enFood;
    }

    public String getReproduction(String lang){
        if(lang.equals("fr")){
            return frReproduction;
        }
        return enReproduction;
    }

    public String getWikipediaURL(String lang){
        if(lang.equals("fr")){
            return frWikipediaURL;
        }
        return enWikipediaURL;
    }


    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getFrName() {
        return frName;
    }

    public void setFrName(String frName) {
        this.frName = frName;
    }

    public String getEnHome() {
        return enHome;
    }

    public void setEnHome(String enHome) {
        this.enHome = enHome;
    }

    public String getFrFood() {
        return frFood;
    }

    public void setFrFood(String frFood) {
        this.frFood = frFood;
    }

    public String getEnReproduction() {
        return enReproduction;
    }

    public void setEnReproduction(String enReproduction) {
        this.enReproduction = enReproduction;
    }

    public String getEnFood() {
        return enFood;
    }

    public void setEnFood(String enFood) {
        this.enFood = enFood;
    }

    public String getFrReproduction() {
        return frReproduction;
    }

    public void setFrReproduction(String frReproduction) {
        this.frReproduction = frReproduction;
    }

    public String getFrWikipediaURL() {
        return frWikipediaURL;
    }

    public void setFrWikipediaURL(String frWikipediaURL) {
        this.frWikipediaURL = frWikipediaURL;
    }

    public String getEnWikipediaURL() {
        return enWikipediaURL;
    }

    public void setEnWikipediaURL(String enWikipediaURL) {
        this.enWikipediaURL = enWikipediaURL;
    }

    public List<Animal> getAnimals(String searchQuery,String language) throws Exception{
        if(!(language.equals("en") || language.equals("fr"))){
            language= "en";
        }
        Map<String,String> query = new HashMap<String,String>();
        query.put("trim","true");
        query.put("name", searchQuery);
        query.put("lang", language);
        return Arrays.asList((Animal[])HttpHelper.getInstance().get("/animals",Animal[].class, query));
    }

    public Animal getAnimal() throws Exception{
        System.out.println(this.getId());
        return (Animal)HttpHelper.getInstance().get("/animals/"+this.getId(),Animal.class, null);
    }
}
