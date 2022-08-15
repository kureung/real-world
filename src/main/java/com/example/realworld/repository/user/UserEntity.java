package com.example.realworld.repository.user;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.example.realworld.domain.user.model.User;
import com.example.realworld.repository.BaseEntity;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = PROTECTED)
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Embedded
    private UserAccountInfoEmbed userAccountInfoEmbed;

    private String bio;

    private String image;

    @Builder
    private UserEntity(UserAccountInfoEmbed userAccountInfoEmbed, String bio, String image) {
        this.userAccountInfoEmbed = userAccountInfoEmbed;
        this.bio = bio;
        this.image = image;
    }

    public static UserEntity convertToEntity(User user) {
        return UserEntity.builder()
                .userAccountInfoEmbed(UserAccountInfoEmbed.convertToEntity(user.accountInfo()))
                .bio(user.bio())
                .image(user.image())
                .build();
    }

    public User convertToDomainModel() {
        return User.builder()
                .userAccountInfo(userAccountInfoEmbed.convertToDomainModel())
                .bio(bio)
                .image(image)
                .build();
    }

}
