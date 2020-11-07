package com.jaspinder.marketpulse.data;

import java.io.Serializable;

public class Criteria implements Serializable {

    private String type;
    private String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
