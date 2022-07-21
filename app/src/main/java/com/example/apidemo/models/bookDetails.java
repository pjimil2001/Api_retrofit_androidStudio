package com.example.apidemo.models;

public class bookDetails {

    private int id;

    private String name;

    private String price;

    private String details;

    private String status;

    private String image;

    private String created_by;

    private String updated_by;

    private String created_at;

    private String updated_at;

    private String added_by;

    private String artist_id;


    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getPrice(){
        return this.price;
    }
    public void setDetails(String details){
        this.details = details;
    }
    public String getDetails(){
        return this.details;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return this.image;
    }
    public void setCreated_by(String created_by){
        this.created_by = created_by;
    }
    public String getCreated_by(){
        return this.created_by;
    }
    public void setUpdated_by(String updated_by){
        this.updated_by = updated_by;
    }
    public String getUpdated_by(){
        return this.updated_by;
    }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }
    public String getCreated_at(){
        return this.created_at;
    }
    public void setUpdated_at(String updated_at){
        this.updated_at = updated_at;
    }
    public String getUpdated_at(){
        return this.updated_at;
    }
    public void setAdded_by(String added_by){
        this.added_by = added_by;
    }
    public String getAdded_by(){
        return this.added_by;
    }
    public void setArtist_id(String artist_id){
        this.artist_id = artist_id;
    }
    public String getArtist_id(){
        return this.artist_id;
    }
    }
