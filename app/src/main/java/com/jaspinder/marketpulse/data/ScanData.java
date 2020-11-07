package com.jaspinder.marketpulse.data;

import java.io.Serializable;
import java.util.List;

public class ScanData implements Serializable {

    private int id;
    private String name;
    private String tag;
    private String color;
    private List<Criteria> criteria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Criteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criteria> list) {
        this.criteria = list;
    }

    @Override
    public String toString() {
        return "ScanData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", color='" + color + '\'' +
                ", list=" + criteria.toString() +
                '}';
    }
}
