package com.zenith.livinghistory.api.zenithlivinghistoryapi.data.repository;

import com.zenith.livinghistory.api.zenithlivinghistoryapi.Example;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.Content;
import com.zenith.livinghistory.api.zenithlivinghistoryapi.dto.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest()
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void whenFindByCreator_thenReturnAnnotation() {
		User user = Example.GetUserInstance();

        mongoTemplate.save(user);

		User foundUser = userRepository.findFirstByEmail(user.getEmail());

        // Exclude date fields to prevent false positive assertion errors caused by timezone.
        Assert.assertThat(user, new ReflectionEquals(foundUser,"createdAt", "modified"));

		Assert.assertThat(user.getEmail(), new ReflectionEquals(foundUser.getEmail(),"createdAt", "modified"));

		Assert.assertThat(user.getPassword(), new ReflectionEquals(foundUser.getPassword(),"createdAt", "modified"));

		Assert.assertThat(user.getStatus(), new ReflectionEquals(foundUser.getStatus(),"createdAt", "modified"));

		Assert.assertThat(user.getUsername(), new ReflectionEquals(foundUser.getUsername(),"createdAt", "modified"));

	}
}
