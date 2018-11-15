package com.example.litness.litness;
//Bryan Test
public class Bar {
    private String barName, cover, wait, events, litness; // add time later

    public Bar(String barName, String cover, String wait, String events, String litness) {
        this.barName = barName;
        this.cover = cover;
        this.wait = wait;
        this.events = events;
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

    public String getEvents() {
        return events;
    }

    public String getLitness() {
        return litness;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setWait(String wait) {
        this.wait = wait;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    public void setLitness(String litness) {
        this.litness = litness;
    }



}
