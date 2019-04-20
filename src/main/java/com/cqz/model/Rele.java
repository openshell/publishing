package com.cqz.model;

public class Rele {
    private Integer releId;

    private Integer releUserId;

    private Integer releTaskId;

    private String releType;

    public Integer getReleId() {
        return releId;
    }

    public void setReleId(Integer releId) {
        this.releId = releId;
    }

    public Integer getReleUserId() {
        return releUserId;
    }

    public void setReleUserId(Integer releUserId) {
        this.releUserId = releUserId;
    }

    public Integer getReleTaskId() {
        return releTaskId;
    }

    public void setReleTaskId(Integer releTaskId) {
        this.releTaskId = releTaskId;
    }

    public String getReleType() {
        return releType;
    }

    public void setReleType(String releType) {
        this.releType = releType == null ? null : releType.trim();
    }
}