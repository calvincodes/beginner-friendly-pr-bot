package com.github.calvincodes.github.models;

import com.google.gson.annotations.SerializedName;

public class Milestone {

    @SerializedName("url")
    private String url;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("labels_url")
    private String labelsUrl;

    @SerializedName("id")
    private String id;

    @SerializedName("node_id")
    private String nodeId;

    @SerializedName("number")
    private Long number;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("creator")
    private User creator;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Milestone{" +
                "url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", labelsUrl='" + labelsUrl + '\'' +
                ", id='" + id + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", number=" + number +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creator=" + creator +
                '}';
    }
}
