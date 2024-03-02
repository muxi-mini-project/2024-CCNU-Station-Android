package com.example.ccnu_station.Reuse;

public class Data {
    private int code;
    private String msg;
    private Content data;
    public int getCode() {
        return code;
    }
    public String getMassage() {
        return msg;
    }
    public Content getData() {
        return data;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setData(Content data) {
        this.data = data;
    }
    public void setMassage(String massage) {
        this.msg = massage;
    }
    private class Content{
        private boolean YNLogin;
        private Note notes;
        public boolean isYNLogin() {
            return YNLogin;
        }
        public void setYNLogin(boolean YNLogin) {
            this.YNLogin = YNLogin;
        }
        public Note getNotes() {
            return notes;
        }
        public void setNotes(Note notes) {
            this.notes = notes;
        }
    }
    private class Note{
        private String postID;
        private String postLocation;
        private String poster;
        private String text;
        private String time;

        public String getText() {
            return text;
        }
        public String getPoster() {
            return poster;
        }
        public String getPostID() {
            return postID;
        }
        public String getPostLocation() {
            return postLocation;
        }
        public String getTime() {
            return time;
        }
        public void setText(String text) {
            this.text = text;
        }
        public void setPoster(String poster) {
            this.poster = poster;
        }
        public void setPostID(String postID) {
            this.postID = postID;
        }
        public void setPostLocation(String postLocation) {
            this.postLocation = postLocation;
        }
        public void setTime(String time) {
            this.time = time;
        }
    }
}
