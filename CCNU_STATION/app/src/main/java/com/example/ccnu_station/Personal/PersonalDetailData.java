package com.example.ccnu_station.Personal;

import com.google.gson.annotations.SerializedName;

public class PersonalDetailData {
    private Long age = 0l;
    private String college;
    private String date;
    @SerializedName("fan_number")
    private Long fanNumber = 0l;
    @SerializedName("follower_number")
    private Long followerNumber = 0l;
    @SerializedName("friends_number")
    private Long friendsNumber = 0l;
    private String gender;
    private String grade;
    private String headimage;
    private String mbti;
    private String nickname;
    @SerializedName("post_number")
    private Long postNumber = 0l;
    private String realName;
    private String sign;
    @SerializedName("stay_date")
    private Long stayDate = 0l;
    private String stuid;
    private Boolean ynLogin;
    private Boolean ynSelf;

    public Long getAge() { return age; }
    public void setAge(Long value) { this.age = value; }

    public String getCollege() { return college; }
    public void setCollege(String value) { this.college = value; }

    public String getDate() { return date; }
    public void setDate(String value) { this.date = value; }

    public Long getFanNumber() { return fanNumber; }
    public void setFanNumber(Long value) { this.fanNumber = value; }

    public Long getFollowerNumber() { return followerNumber; }
    public void setFollowerNumber(Long value) { this.followerNumber = value; }

    public Long getFriendsNumber() { return friendsNumber; }
    public void setFriendsNumber(Long value) { this.friendsNumber = value; }

    public String getGender() { return gender; }
    public void setGender(String value) { this.gender = value; }

    public String getGrade() { return grade; }
    public void setGrade(String value) { this.grade = value; }

    public String getHeadimage() { return headimage; }
    public void setHeadimage(String value) { this.headimage = value; }

    public String getMbti() { return mbti; }
    public void setMbti(String value) { this.mbti = value; }

    public String getNickname() { return nickname; }
    public void setNickname(String value) { this.nickname = value; }

    public Long getPostNumber() { return postNumber; }
    public void setPostNumber(Long value) { this.postNumber = value; }

    public String getRealName() { return realName; }
    public void setRealName(String value) { this.realName = value; }

    public String getSign() { return sign; }
    public void setSign(String value) { this.sign = value; }

    public Long getStayDate() { return stayDate; }
    public void setStayDate(Long value) { this.stayDate = value; }

    public String getStuid() { return stuid; }
    public void setStuid(String value) { this.stuid = value; }

    public Boolean getYnLogin() { return ynLogin; }
    public void setYnLogin(Boolean value) { this.ynLogin = value; }

    public Boolean getYnSelf() { return ynSelf; }
    public void setYnSelf(Boolean value) { this.ynSelf = value; }
}
