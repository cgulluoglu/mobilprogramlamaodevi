package com.example.mobilodev;

public class User {
    private String username, password;
    private int img;

    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }




}
