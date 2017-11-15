package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "content")
public class ContentModel extends BaseModel {

    @OneToMany(fetch=FetchType.LAZY, mappedBy="content")
    private List<AnnotationModel> annotations;

    public List<AnnotationModel> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationModel> annotations) {
        this.annotations = annotations;
    }
}