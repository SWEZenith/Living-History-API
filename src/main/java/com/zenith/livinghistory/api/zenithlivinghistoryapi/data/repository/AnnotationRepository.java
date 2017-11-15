package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.data.model.AnnotationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnotationRepository extends JpaRepository<AnnotationModel, Long> {
}
