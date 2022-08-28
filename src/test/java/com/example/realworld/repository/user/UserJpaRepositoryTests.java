package com.example.realworld.repository.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserJpaRepositoryTests {

    @Autowired
    private UserJpaRepository sut;

    @Autowired
    private EntityManager em;

    @Test
    void 회원을_이메일로_조회할_때_저장되지_않는_회원의_이메일_경우_빈_옵셔널_값이_나온다() {
        final String email = "email";
        Optional<UserEntity> findUserEntityOpt = sut.findByUserAccountInfoEmbedEmail(email);
        assertTrue(findUserEntityOpt.isEmpty());
    }

    @Test
    void 회원을_이메일로_조회할_때_저장된_회원의_이메일_경우_예외_없이_잘_조회가_된다() {
        UserAccountInfoEmbed accountInfo = userAccountInfoEmbed();
        UserEntity userEntity = userEntity(accountInfo);
        em.persist(userEntity);
        Optional<UserEntity> findUserEntityOpt = sut.findByUserAccountInfoEmbedEmail(accountInfo.getEmail());
        assertTrue(findUserEntityOpt.isPresent());
    }

    @Test
    void 이메일로_존쟤_유무를_판별할_때_저장되지_않는_회원일_경우_거짓이_나온다() {
        final String email = "email";
        assertFalse(sut.existsByUserAccountInfoEmbedEmail(email));
    }

    @Test
    void 이메일로_존재_유무를_판별할_때_저장된_회원일_경우_참이_나온다() {
        UserAccountInfoEmbed accountInfo = userAccountInfoEmbed();
        UserEntity userEntity = userEntity(accountInfo);
        em.persist(userEntity);
        assertTrue(sut.existsByUserAccountInfoEmbedEmail(accountInfo.getEmail()));
    }

    private UserAccountInfoEmbed userAccountInfoEmbed() {
        return UserAccountInfoEmbed.builder()
                .email("abc@naver.com")
                .password("password")
                .username("username")
                .build();
    }

    private UserEntity userEntity(UserAccountInfoEmbed userAccountInfoEmbed) {
        return UserEntity.builder()
                .userAccountInfoEmbed(userAccountInfoEmbed)
                .image("abc@naver.com")
                .bio("I like a apple")
                .build();
    }

}