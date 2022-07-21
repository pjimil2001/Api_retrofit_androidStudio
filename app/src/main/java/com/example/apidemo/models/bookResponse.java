package com.example.apidemo.models;

import java.util.List;

public class bookResponse {


    private boolean error;

    private String msg;

    private List<bookDetails> result_all_books;

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
    public void setResult_all_books(List<bookDetails> result_all_books){
        this.result_all_books = result_all_books;
    }
    public List<bookDetails> getResult_all_books(){
        return this.result_all_books;
    }
}
