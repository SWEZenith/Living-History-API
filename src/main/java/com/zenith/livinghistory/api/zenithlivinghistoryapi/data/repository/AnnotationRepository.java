package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Annotation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnotationRepository extends MongoRepository<Annotation, String> {
	Annotation findFirstByCreator(String creator);
	List<Annotation> findByCreator(String creator);

	@Query("{ creator: { $regex: 'http://living-history.gkc.host/api/v1/users/?0' } }")
	List<Annotation> findAnnotationsByUsername(String username);

	@Query("{$text: { $search: ?0 }}")
	List<Annotation> findAnnotationsByKeyword(String keyword);
}
