package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.request.AnnotationRequest;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "annotation")
public class AnnotationModel extends BaseModel {

    // OK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id")
    private ContentModel content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel creator;

    @Column(name = "annotation", columnDefinition = "json")
    @ColumnTransformer(read = "annotation", write = "?::json")
    @Convert(converter = MapConverter.class)
    private Map<String,Object> extraParameters;
}
