package com.keyin.airport;

public class Airport {
    private int id;
    private String name;
    private int cityId;

    public Airport(int id, String name, int cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCityId() {
        return cityId;
    }
}