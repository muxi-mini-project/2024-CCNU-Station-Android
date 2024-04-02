package com.example.ccnu_station.Call;

public class CallItem {
    /**
     * 活动时间
     */
    private String activityTime;
    /**
     * 发布人的头像
     */
    private String headimage;
    /**
     * 发布时间
     */
    private String postTime;
    /**
     * 发布人的ID
     */
    private String posterid;
    /**
     * 招募活动的ID
     */
    private String recruitID;
    /**
     * 活动要求
     */
    private String request;
    /**
     * 活动名称
     */
    private String title;
    /**
     * 活动地点
     */
    private String where;

    public String getActivityTime() { return activityTime; }
    public void setActivityTime(String value) { this.activityTime = value; }

    public String getHeadimage() { return headimage; }
    public void setHeadimage(String value) { this.headimage = value; }

    public String getPostTime() { return postTime; }
    public void setPostTime(String value) { this.postTime = value; }

    public String getPosterid() { return posterid; }
    public void setPosterid(String value) { this.posterid = value; }

    public String getRecruitID() { return recruitID; }
    public void setRecruitID(String value) { this.recruitID = value; }

    public String getRequest() { return request; }
    public void setRequest(String value) { this.request = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getWhere() { return where; }
    public void setWhere(String value) { this.where = value; }
}
