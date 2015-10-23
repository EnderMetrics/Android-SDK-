package com.endermetrics.endermetricslibrary;


/**
 * Created by Sergi on 8/10/15.
 */
public class Child {

    private String id = "";
    private String account_id = "";
    private String nick = "";
    private String birthdate = "";
    private String gender = "";
    private String custom_id = "";

    public Child(String id, String account_id, String nick, String birthdate, String gender, String custom_id){
        this.id = id;
        this.account_id= account_id;
        this.nick=nick;
        this.birthdate= birthdate;
        this.gender=gender;
        this.custom_id=custom_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCustom_id() {
        return custom_id;
    }

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id;
    }
}

