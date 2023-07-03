package com.keyin.airport;

public class Airport {
    private Long id;
    private String name;
    private int cityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getCityId() {
        return cityId;
    }
}