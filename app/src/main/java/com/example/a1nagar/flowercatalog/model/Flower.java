
package com.example.a1nagar.flowercatalog.model;


public class Flower{
    private String category;
    private String instructions;
    private String name;
    private String photo;
    private Number price;
    private Number productId;

    public String getCategory(){
        return this.category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public String getInstructions(){
        return this.instructions;
    }
    public void setInstructions(String instructions){
        this.instructions = instructions;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPhoto(){
        return this.photo;
    }
    public void setPhoto(String photo){
        this.photo = photo;
    }
    public Number getPrice(){
        return this.price;
    }
    public void setPrice(Number price){
        this.price = price;
    }
    public Number getProductId(){
        return this.productId;
    }
    public void setProductId(Number productId){
        this.productId = productId;
    }
}
