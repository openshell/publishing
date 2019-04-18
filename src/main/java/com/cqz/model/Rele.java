package com.cqz.model;

/**
 * @author openshell
 * @date 2019/4/19
 */

public class Rele {
    private int releId;
    private int releTaskId;
    private int releUserId;

    public int getReleId() {
        return releId;
    }

    public void setReleId(int releId) {
        this.releId = releId;
    }

    public int getReleTaskId() {
        return releTaskId;
    }

    public void setReleTaskId(int releTaskId) {
        this.releTaskId = releTaskId;
    }

    public int getReleUserId() {
        return releUserId;
    }

    public void setReleUserId(int releUserId) {
        this.releUserId = releUserId;
    }

    @Override
    public String toString() {
        return "Rele{" +
                "releId=" + releId +
                ", releTaskId=" + releTaskId +
                ", releUserId=" + releUserId +
                '}';
    }
}
