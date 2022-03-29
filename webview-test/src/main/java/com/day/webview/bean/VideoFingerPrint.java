package com.day.webview.bean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xupei
 */
public class VideoFingerPrint implements Serializable {

    private List<byte []> imageByte=new ArrayList();
    
    private String videoId;

    private Long joinTime;

    private Integer videoTimes;

    private double similar;

    private String url;

    private Integer status;

    private VideoTypeEnum typeEnum;

    public double getSimilar() {
        return similar;
    }

    public void setSimilar(double similar) {
        this.similar = similar;
    }

    public List<byte[]> getImageByte() {
        return imageByte;
    }

    public void setImageByte(List<byte[]> imageByte) {
        this.imageByte = imageByte;
        if(imageByte!=null){
            this.videoTimes=imageByte.size();
        }

    }

    public Integer getVideoTimes() {
        return imageByte.size();
    }

    public void setVideoTimes(Integer videoTimes) {
        this.videoTimes = videoTimes;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Long getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Long joinTime) {
        this.joinTime = joinTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public VideoTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(VideoTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }
}
