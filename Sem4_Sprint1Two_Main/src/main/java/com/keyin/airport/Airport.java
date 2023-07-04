package com.keyin.airport;

public class Airport {
    private Long id;
    private String code;
    private String name;
    private int cityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCityId() {
        return cityId;
    }
}