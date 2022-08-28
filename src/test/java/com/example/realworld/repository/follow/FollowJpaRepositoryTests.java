package com.example.realworld.repository.follow;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.realworld.repository.user.UserAccountInfoEmbed;
import com.example.realworld.repository.user.UserEntity;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class FollowJpaRepositoryTests {

    @Autowired
    private FollowJpaRepository sut;

    @Autowired
    private EntityManager em;

    @Test
    void 팔로우_조회하기() {
        UserEntity onwer = userEntity("owner");
        em.persist(onwer);
        injectTheFollowingUserData(onwer);
        assertThat(sut.findEntitiesByOwner(onwer)).hasSize(10);
    }

    private void injectTheFollowingUserData(UserEntity onwer) {
        for (int i = 0; i < 10; i++) {
            UserEntity userEntity = userEntity(i + "");
            em.persist(userEntity);
            FollowEntity followEntity = new FollowEntity(onwer, userEntity);
            em.persist(followEntity);
        }
    }

    private UserEntity userEntity(String email) {
        UserAccountInfoEmbed userAccountInfoEmbed = userAccountInfoEmbed(email);
        return UserEntity.builder()
                .userAccountInfoEmbed(userAccountInfoEmbed)
                .build();
    }

    private UserAccountInfoEmbed userAccountInfoEmbed(String email) {
        return UserAccountInfoEmbed.builder()
                .email(email)
                .build();
    }

}