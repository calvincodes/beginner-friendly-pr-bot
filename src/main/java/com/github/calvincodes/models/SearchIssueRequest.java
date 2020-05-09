package com.github.calvincodes.models;

public class SearchIssueRequest {

    private String sortBy = "created";
    private String order = "desc";
    // TODO: Create enum for type. Label: Good first issue.
    private String state;
    private String label;

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "SearchIssueRequest{" +
                "sortBy='" + sortBy + '\'' +
                ", order='" + order + '\'' +
                ", state='" + state + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
