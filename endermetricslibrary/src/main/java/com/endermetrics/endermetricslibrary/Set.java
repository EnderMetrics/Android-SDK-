package com.endermetrics.endermetricslibrary;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;




public class Set {
    private String activityToken = "";
    private float initTime = 0;
    private float endTime = 0;
    private int level = 0;
    ArrayList hits = new ArrayList();

    private float time = 0;
    private String result = "";
    Date currentDate = new Date();



    private class Hit{
        private String skillToken="";
        private String result = "";

        private Hit(String skillToken, String result){
            this.skillToken=skillToken;
            this.result=result;
        }
    }



    public String getActivityToken() {
        return activityToken;
    }

    public void setActivityToken(String activityToken) {
        this.activityToken = activityToken;
    }

    public float getInitTime() {
        return initTime;
    }

    public void setInitTime(float initTime) {
        this.initTime = initTime;
    }

    public float getEndTime() {
        return endTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList getHits() {
        return hits;
    }


    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void initSet(String activityToken, int level) {
        this.activityToken = activityToken;
        this.level = level;
        this.initTime = currentDate.getTime();
        this.hits = new ArrayList();

        Log.d("INIT SET ---> ", "");
    }

    public void end(String result){
        this.result=result;
        this.endTime=currentDate.getTime();
        this.time=endTime-initTime;
    }

    public void addHit(String skillToken, String result){
        Hit h = new Hit(skillToken,result);
        hits.add(h);
    }

    public String toJson(){
        String r = "";
        r=r+"\"hits\":[";
        for (int i=0; i<hits.size(); i++){
            Hit h = (Hit)hits.get(i);
            r=r+"{\"skill_token\":\""+h.skillToken+"\",\"result\":\""+h.result+"\"}";
            if(i<hits.size()-1){
                r=r+",";
            }

        }
        r=r+"]";
        return r;
    }
}

