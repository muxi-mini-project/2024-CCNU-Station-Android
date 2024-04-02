package com.example.ccnu_station.Finder;

public class FindItem {
    /**
     * 线索
     */
    private String clue;
    /**
     * 截止日期
     */
    private String deadline;
    /**
     * 发布人的头像
     */
    private String headImage;
    /**
     * 物品图片
     */
    private String image;
    /**
     * 发布人的ID
     */
    private String poster;
    /**
     * 要寻找的物品
     */
    private String thing;
    /**
     * 发布时间
     */
    private String time;
    /**
     * 活动标题
     */
    private String title;
    /**
     * 寻宝活动的ID
     */
    private String treasureID;
    /**
     * 寻宝活动的地点
     */
    private Long treasurelocation;

    public String getClue() { return clue; }
    public void setClue(String value) { this.clue = value; }

    public String getDeadline() { return deadline; }
    public void setDeadline(String value) { this.deadline = value; }

    public String getHeadImage() { return headImage; }
    public void setHeadImage(String value) { this.headImage = value; }

    public String getImage() { return image; }
    public void setImage(String value) { this.image = value; }

    public String getPoster() { return poster; }
    public void setPoster(String value) { this.poster = value; }

    public String getThing() { return thing; }
    public void setThing(String value) { this.thing = value; }

    public String getTime() { return time; }
    public void setTime(String value) { this.time = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public String getTreasureID() { return treasureID; }
    public void setTreasureID(String value) { this.treasureID = value; }

    public Long getTreasurelocation() { return treasurelocation; }
    public void setTreasurelocation(Long value) { this.treasurelocation = value; }
}
