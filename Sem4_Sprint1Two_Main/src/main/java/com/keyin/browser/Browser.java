package com.keyin.browser;

import java.time.LocalDateTime;

public class Browser {

    private String calledMethod;
    private String url;
    private LocalDateTime timestamp;

    public Browser(String calledMethod, String url, LocalDateTime timestamp) {
        this.calledMethod = calledMethod;
        this.url = url;
        this.timestamp = timestamp;
    }

    public String getCalledMethod() {
        return calledMethod;
    }

    public void setCalledMethod(String calledMethod) {
        this.calledMethod = calledMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
