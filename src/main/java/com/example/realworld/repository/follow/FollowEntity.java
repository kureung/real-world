package com.example.realworld.repository.follow;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.example.realworld.repository.BaseEntity;
import com.example.realworld.repository.user.UserEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class FollowEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    private UserEntity owner;

    @ManyToOne(fetch = LAZY)
    private UserEntity followedUser;

    public FollowEntity(UserEntity owner, UserEntity followedUser) {
        this.owner = owner;
        this.followedUser = followedUser;
    }

}
