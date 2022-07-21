package com.example.apidemo.models;

import java.util.List;

public class UserResponse {

    private boolean error;

    private String msg;

    private List<UserDetails> result_all_genres;




    public void setError(boolean error){
        this.error = error;
    }
    public boolean getError(){
        return this.error;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setResult_all_genres(List<UserDetails> result_all_genres){
        this.result_all_genres = result_all_genres;
    }
    public List<UserDetails> getResult_all_genres(){
        return this.result_all_genres;
    }

}
