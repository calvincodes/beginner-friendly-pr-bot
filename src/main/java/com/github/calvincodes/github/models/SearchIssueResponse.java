package com.github.calvincodes.github.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchIssueResponse {

    @SerializedName("total_count")
    private Long totalCount;

    @SerializedName("incomplete_results")
    private Boolean incompleteResults;

    @SerializedName("items")
    private List<SearchIssue> items;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<SearchIssue> getItems() {
        return items;
    }

    public void setItems(List<SearchIssue> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "SearchIssueResponse{" +
                "totalCount=" + totalCount +
                ", incompleteResults=" + incompleteResults +
                ", items=" + items +
                '}';
    }
}
