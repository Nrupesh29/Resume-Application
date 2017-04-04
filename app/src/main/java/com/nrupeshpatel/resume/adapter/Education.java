package com.nrupeshpatel.resume.adapter;

public class Education {

    private String name, degree, duration, location, logo;

    public Education() {

    }

    public Education(String name, String degree, String duration, String location, String logo) {
        this.name = name;
        this.degree = degree;
        this.duration = duration;
        this.location = location;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public String getDegree() {
        return degree;
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