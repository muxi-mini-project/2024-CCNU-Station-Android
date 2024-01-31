package com.example.ccnu_station;

import java.sql.Time;
import java.util.SplittableRandom;

public class PersonalDetailData {
    private String stuid;
    private String password;
    private String realName;
    private String nickname;
    private boolean YNself;
    private int grade;
    private String college;
    private String gender;
    private String headimage;
    private int age;
    private String sign;
    private int friends_number;
    private int followers_number;
    private boolean YNLogin;
    private Time date;
    private int stay_date;

    public void setStay_date(int stay_date) {
        this.stay_date = stay_date;
    }

    public int getStay_date() {
        return stay_date;
    }

    public int getAge() {
        return age;
    }

    public int getFollowers_number() {
        return followers_number;
    }

    public int getFriends_number() {
        return friends_number;
    }

    public int getGrade() {
        return grade;
    }

    public String getCollege() {
        return college;
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

    public String getRealName() {
        return realName;
    }

    public String getSign() {
        return sign;
    }

    public String getStuid() {
        return stuid;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setFollowers_number(int followers_number) {
        this.followers_number = followers_number;
    }

    public void setFriends_number(int friends_number) {
        this.friends_number = friends_number;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public void setYNLogin(boolean YNLogin) {
        this.YNLogin = YNLogin;
    }

    public Time getDate() {
        return date;
    }

    public void setDate(Time date) {
        this.date = date;
    }

    public boolean isYNLogin() {
        return YNLogin;
    }

    public boolean isYNself() {
        return YNself;
    }

    public void setYNself(boolean YNself) {
        this.YNself = YNself;
    }
}
