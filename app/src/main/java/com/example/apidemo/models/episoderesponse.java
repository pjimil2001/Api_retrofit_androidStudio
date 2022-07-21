package com.example.apidemo.models;

import java.util.List;

public class episoderesponse {

    private boolean error;

    private String msg;

    private List<episodedetails> result_all_episode;

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
    public void setResult_all_episode(List<episodedetails> result_all_episode){
        this.result_all_episode = result_all_episode;
    }
    public List<episodedetails> getResult_all_episode(){
        return this.result_all_episode;
}
}
