package com.deniz.appian.processmining.model;

public class ProcessEvent {

    private final String caseId;
    private final String activity;
    private final long timestamp;

    public ProcessEvent(String caseId, String activity, long timestamp) {
        this.caseId = caseId;
        this.activity = activity;
        this.timestamp = timestamp;
    }

    public String getCaseId() {
        return caseId;
    }

    public String getActivity() {
        return activity;
    }

    public long getTimestamp() {
        return timestamp;
    }
}