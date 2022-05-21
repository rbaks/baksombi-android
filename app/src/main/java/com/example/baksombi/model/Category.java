package com.example.baksombi.model;

import com.example.baksombi.helper.HttpHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Category {
    private String enName;
    private String frName;
    private String img;

    private Category(String enName, String frName, String img) {
        this.enName = enName;
        this.frName = frName;
        this.img = img;
    }

    public Category(){}
    public List<Category> getCategories(){
        List<Category> categories = new ArrayList<Category>();
        categories.add(new Category("mammals","mammifères", "https://ik.imagekit.io/etb4k5uezbw/logo__bYXsR8EH.png"));
        categories.add(new Category("birds","oiseaux", "https://ik.imagekit.io/etb4k5uezbw/logo__bYXsR8EH.png"));
        categories.add(new Category("fishes","poissons", "https://ik.imagekit.io/etb4k5uezbw/goldfish_OkZHJ8F9ov.png"));
        return categories;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
