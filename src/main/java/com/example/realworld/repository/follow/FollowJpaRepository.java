package com.example.realworld.repository.follow;

import com.example.realworld.repository.user.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<FollowEntity, Long> {

    List<FollowEntity> findEntitiesByOwner(UserEntity userEntity);

    Optional<FollowEntity> findEntityByOwnerAndFollowedUser(UserEntity owner, UserEntity followedUser);

}
