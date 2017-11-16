package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model;

import javax.persistence.*;

@Entity
@Table(name = "content_item")
public class ContentItemModel extends BaseModel {

    // OK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private ContentModel content;

    private String type;

    private String actualContent;

    public ContentModel getContent() {
        return content;
    }

    public void setContent(ContentModel content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActualContent() {
        return actualContent;
    }

    public void setActualContent(String actualContent) {
        this.actualContent = actualContent;
    }
}
