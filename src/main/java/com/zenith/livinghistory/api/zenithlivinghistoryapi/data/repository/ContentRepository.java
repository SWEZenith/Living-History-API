package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {

}
