package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.AnnotationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnotationRepository extends MongoRepository<AnnotationModel, String> {
    AnnotationModel findFirstByCreator(String creator);
}
