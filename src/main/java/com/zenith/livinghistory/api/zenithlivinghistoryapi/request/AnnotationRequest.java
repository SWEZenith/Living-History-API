package com.zenith.livinghistory.api.zenithlivinghistoryapi.request;

public class AnnotationRequest {
    private String id;
    private String context;
    private String type;
    private AnnotationBodyRequest body;

    public AnnotationBodyRequest getBody() {
        return body;
    }

    public void setBody(AnnotationBodyRequest body) {
        this.body = body;
    }

    private String target;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
