package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "content")
public class ContentModel extends BaseModel {

    @Column(name = "jsonld_content_id", unique = true)
    private String jsonLdContentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel creator;

    // OK
    @OneToMany(fetch=FetchType.LAZY, mappedBy="content")
    private List<AnnotationModel> annotations = new ArrayList<>();

    // OK
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "content")
    private List<ContentItemModel> items = new ArrayList<>();

    private String title;

    private String description;

    public List<AnnotationModel> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationModel> annotations) {
        this.annotations = annotations;
    }

    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }

    public List<ContentItemModel> getItems() {
        return items;
    }

    public void setItems(List<ContentItemModel> items) {
        this.items = items;
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
}