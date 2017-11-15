package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model;

import javax.persistence.*;

@Entity
@Table(name = "annotation")
public class AnnotationModel extends BaseModel {

    @JoinColumn(name = "content_id")
    @ManyToOne
    private ContentModel content;

    public ContentModel getContent() {
        return content;
    }

    public void setContent(ContentModel content) {
        this.content = content;
    }
}
