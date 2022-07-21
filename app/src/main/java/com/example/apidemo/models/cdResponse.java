package com.example.apidemo.models;

import java.util.List;

public class cdResponse {


    private boolean error;

    private String msg;

    private List<cdDetails> result_all_cd;

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
    public void setResult_all_cd(List<cdDetails> result_all_cd){
        this.result_all_cd = result_all_cd;
    }
    public List<cdDetails> getResult_all_cd(){
        return this.result_all_cd;
    }
}
