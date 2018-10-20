package com.example.litness.litness;

public class BarObject {
    private String barName, cover, wait, icon, litness; // add time later

    public BarObject(String barName, String cover, String wait, String icon, String litness) {
        this.barName = barName;
        this.cover = cover;
        this.wait = wait;
        this.icon = icon;
        this.litness = litness;

    }

    public String getBarName() {
        return barName;
    }

    public String getCover() {
        return cover;
    }

    public String getWait() {
        return wait;
    }

    public String getIcon() {
        return icon;
    }

    public String getLitness() {
        return litness;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public void setCover(String keyTags) {
        this.cover = cover;
    }

    public void setWait(String wait) {
        this.wait = wait;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setLitness(String litness) {
        this.litness = litness;
    }



}
