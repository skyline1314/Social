package com.skyline.social.entity;

/**
 * Created by Administrator on 2017/9/21.
 */
public class UserEntity extends BaseEntity {
    private String user_id = "";
    private String password_md5 = "";
    private String avater_url = "";
    private String nickname = "";
    private String token = "";

    private String location = "";
    private String longitude = "";
    private String latitude = "";


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword_md5() {
        return password_md5;
    }

    public void setPassword_md5(String password_md5) {
        this.password_md5 = password_md5;
    }

    public String getAvater_url() {
        return avater_url;
    }

    public void setAvater_url(String avater_url) {
        this.avater_url = avater_url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
