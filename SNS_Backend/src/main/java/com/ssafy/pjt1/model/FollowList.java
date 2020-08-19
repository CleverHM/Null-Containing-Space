package com.ssafy.pjt1.model;

public class FollowList {
    private int fid;
    private byte[] file;
    private String nickname;

    public FollowList(int fid, byte[] file, String nickname) {

        this.fid = fid;
        this.file = file;
        this.nickname = nickname;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    
}