package com.example.ccnu_station;

import java.sql.Time;
import java.util.SplittableRandom;

public class PersonalDetailData {
    private String stuid;
    private String password;
    private String realname;
    private String nickname;
    private String grade;
    private String college;
    private String gender;
    private String headimage;
    private String age;
    private String sign;
    private String friends_number;
    private String followers_number;
    private String follower_number;
    private String post_number;
    private String date;
    private String stay_date;
    private boolean YNLogin;
    private boolean YNSelf;

    public boolean isYNLogin() {
        return YNLogin;
    }

    public boolean isYNSelf() {
        return YNSelf;
    }

    public String getGender() {
        return gender;
    }

    public String getHeadimage() {
        return headimage;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getCollege() {
        return college;
    }

    public String getSign() {
        return sign;
    }

    public String getStuid() {
        return stuid;
    }

    public String getAge() {
        return age;
    }

    public String getDate() {
        return date;
    }

    public String getFollower_number() {
        return follower_number;
    }

    public String getFollowers_number() {
        return followers_number;
    }

    public String getFriends_number() {
        return friends_number;
    }

    public String getGrade() {
        return grade;
    }

    public String getPost_number() {
        return post_number;
    }

    public String getRealname() {
        return realname;
    }

    public String getStay_date() {
        return stay_date;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setFriends_number(String friends_number) {
        this.friends_number = friends_number;
    }

    public void setFollowers_number(String followers_number) {
        this.followers_number = followers_number;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStay_date(String stay_date) {
        this.stay_date = stay_date;
    }

    public void setPost_number(String post_number) {
        this.post_number = post_number;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public void setYNLogin(boolean YNLogin) {
        this.YNLogin = YNLogin;
    }

    public void setFollower_number(String follower_number) {
        this.follower_number = follower_number;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setYNSelf(boolean YNSelf) {
        this.YNSelf = YNSelf;
    }
}
