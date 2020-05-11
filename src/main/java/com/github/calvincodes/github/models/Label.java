package com.github.calvincodes.github.models;

import com.google.gson.annotations.SerializedName;

public class Label {

    @SerializedName("id")
    private String id;

    @SerializedName("node_id")
    private String nodeId;

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    @SerializedName("color")
    private String color;

    @SerializedName("default")
    private Boolean defaultFlag;

    @SerializedName("description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id='" + id + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", defaultFlag=" + defaultFlag +
                ", description='" + description + '\'' +
                '}';
    }
}
