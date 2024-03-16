package com.example.ccnu_station.Record;

public class Item {
    private String title;
    private String avatar;
    /**
     * 图片1URL
     */
    private String image1;
    /**
     * 发布人的ID
     */
    private String poster;
    /**
     * 帖子的ID
     */
    private String postID;
    /**
     * 发布的地点
     */
    private String postLocation;
    /**
     * 文本内容
     */
    private String text;
    /**
     * 发布时间
     */
    private String time;

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public String getImage1() { return image1; }
    public void setImage1(String value) { this.image1 = value; }
    public String getPoster() { return poster; }
    public void setPoster(String value) { this.poster = value; }

    public String getPostID() { return postID; }
    public void setPostID(String value) { this.postID = value; }

    public String getPostLocation() { return postLocation; }
    public void setPostLocation(String value) { this.postLocation = value; }

    public String getText() { return text; }
    public void setText(String value) { this.text = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }
}
