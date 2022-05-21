package com.example.baksombi.model;

import com.example.baksombi.helper.HttpHelper;

public class Category {
    private Integer status;
    private Integer data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Category getCategory() throws Exception{
        HttpHelper helper = HttpHelper.getInstance();
        return (Category)helper.get("/deliveryfee",Category.class,null);
    }
}
