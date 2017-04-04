package com.nrupeshpatel.resume.adapter;

public class Project {

    private String name, platform, duration, location, description, link, logo;

    public Project() {

    }

    public Project(String name, String platform, String duration, String location, String description, String link, String logo) {
        this.name = name;
        this.platform = platform;
        this.duration = duration;
        this.location = location;
        this.description = description;
        this.link = link;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDuration() {
        return duration;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getLogo() {
        return logo;
    }
}