package com.github.calvincodes;

import com.google.gson.annotations.SerializedName;
import com.sun.xml.internal.ws.developer.Serialization;

public class SearchIssue {

    @SerializedName("url")
    private String url;

    @SerializedName("repository_url")
    private String repositoryUrl;

    @SerializedName("labels_url")
    private String labelsUrl;

    @SerializedName("comments_url")
    private String commentsUrl;

    @SerializedName("events_url")
    private String eventsUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("id")
    private String id;

    @SerializedName("node_id")
    private String nodeId;

    @SerializedName("number")
    private Long number;

    @SerializedName("title")
    private String title;


}
