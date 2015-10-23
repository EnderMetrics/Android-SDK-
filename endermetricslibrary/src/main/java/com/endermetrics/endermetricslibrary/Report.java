package com.endermetrics.endermetricslibrary;

import java.util.ArrayList;

public class Report {

    private String token;
    private String name;
    private String last_level;
    private String max_level;
    private String avg_time_session;
    private String success_ratio;
    private String total_time;
    ArrayList<Skill> skills = new ArrayList();


    public Report(){

    }
    public Report(String token, String name, String last_level, String max_level, String avg_time_session, String success_ratio, String total_time, ArrayList skills) {
        this.token = token;
        this.name = name;
        this.last_level = last_level;
        this.max_level = max_level;
        this.avg_time_session = avg_time_session;
        this.success_ratio = success_ratio;
        this.total_time = total_time;
        this.skills = skills;
    }

    public void addSkill(String token, String name, float ratio, int total_results){
        Skill s = new Skill(token, name, ratio, total_results);
        skills.add(s);
    }
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

    public String getLast_level() {
        return last_level;
    }

    public void setLast_level(String last_level) {
        this.last_level = last_level;
    }

    public String getMax_level() {
        return max_level;
    }

    public void setMax_level(String max_level) {
        this.max_level = max_level;
    }

    public String getAvg_time_session() {
        return avg_time_session;
    }

    public void setAvg_time_session(String avg_time_session) {
        this.avg_time_session = avg_time_session;
    }

    public String getSuccess_ratio() {
        return success_ratio;
    }

    public void setSuccess_ratio(String succes_ratio) {
        this.success_ratio = succes_ratio;
    }

    public String getTotal_time() {
        return total_time;
    }

    public void setTotal_time(String total_time) {
        this.total_time = total_time;
    }
}

