package com.logui.arghasen.criotam;

public class UserPOJO {

    public String username;

    public String email;

    public String password;

    public UserPOJO(){}

    public UserPOJO(String username, String email, String password){

        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String  getUsername(){ return  this.username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
