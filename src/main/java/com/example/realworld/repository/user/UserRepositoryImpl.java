package com.example.realworld.repository.user;

import com.example.realworld.domain.user.UserRepository;
import com.example.realworld.domain.user.model.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.convertedUserEntity(user);
        UserEntity savedUserEntity = jpaRepository.save(userEntity);
        return savedUserEntity.convertedUserDomainModel();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByUserAccountInfoEmbedEmail(email)
                .map(UserEntity::convertedUserDomainModel);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByUserAccountInfoEmbedEmail(email);
    }

}
