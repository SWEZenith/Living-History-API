package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {

    @Query("{story_items:{$elemMatch:{_id: ?0}}}")
    Content findContentByStoryItemId(String storyItemId);

    @Query("{ creator: { $regex: 'http://living-history.gkc.host/api/v1/users/?0' } }")
    List<Content> findContentsByUsername(String username);

    List<Content> findContentsByCreator(String creator);
}
