package com.endermetrics.endermetricslibrary;

public class Skill {
    private String token;
    private String name;
    private float ratio;
    private int total_results;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public Skill(String token,String name,float ratio, int total_Results){
        this.token=token;
        this.name=name;
        this.ratio=ratio;
        this.total_results=total_Results;
    }
}

