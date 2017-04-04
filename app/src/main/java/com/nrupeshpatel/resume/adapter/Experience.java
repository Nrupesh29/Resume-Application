package com.nrupeshpatel.resume.adapter;

public class Experience {

    private String name, position, duration, location, logo;

    public Experience() {

    }

    public Experience(String name, String position, String duration, String location, String logo) {
        this.name = name;
        this.position = position;
        this.duration = duration;
        this.location = location;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDuration() {
        return duration;
    }

    public String getLocation() {
        return location;
    }

    public String getLogo() {
        return logo;
    }
}