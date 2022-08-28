package com.example.realworld.repository.follow;

import static com.example.realworld.web.exception.ErrorCode.NOT_FOUND_FOLLOWING_USER;
import static java.util.stream.Collectors.toSet;

import com.example.realworld.domain.follow.FollowRepository;
import com.example.realworld.domain.follow.exception.FollowException;
import com.example.realworld.domain.follow.model.Follow;
import com.example.realworld.domain.user.model.User;
import com.example.realworld.repository.user.UserEntity;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl implements FollowRepository {

    private final FollowJpaRepository jpaRepository;

    @Override
    public Long save(User owner, User followingUser) {
        UserEntity ownerUserEntity = UserEntity.convertedUserEntity(owner);
        UserEntity followingUserEntity = UserEntity.convertedUserEntity(followingUser);

        return new FollowEntity(ownerUserEntity, followingUserEntity)
                .getId();
    }

    @Override
    public void delete(User owner, User followedUser) {
        UserEntity ownerUserEntity = UserEntity.convertedUserEntity(owner);
        UserEntity followingUserEntity = UserEntity.convertedUserEntity(followedUser);

        FollowEntity followEntity = jpaRepository.findEntityByOwnerAndFollowedUser(ownerUserEntity, followingUserEntity)
                .orElseThrow(() -> new FollowException(NOT_FOUND_FOLLOWING_USER));
        jpaRepository.delete(followEntity);
    }

    public Follow findByOwner(User user) {
        List<FollowEntity> followEntities = jpaRepository.findEntitiesByOwner(UserEntity.convertedUserEntity(user));
        Set<User> followedUsers = followEntities.stream()
                .map(FollowRepositoryImpl::convertedUser)
                .collect(toSet());

        return new Follow(followedUsers);
    }

    private static User convertedUser(FollowEntity followEntity) {
        return followEntity.getFollowedUser()
                .convertedUserDomainModel();
    }

}
