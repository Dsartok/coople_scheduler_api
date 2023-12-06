package com.coople.gamepartnerservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private String extraInfo;

    // Static builder method for registrationService
    public static UserEntityBuilder builder() {
        return new UserEntityBuilder();
    }

    // Builder class for UserEntity
    public static class UserEntityBuilder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String role;
        private String extraInfo;

        UserEntityBuilder() {
        }

        public UserEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserEntityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserEntityBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserEntityBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserEntityBuilder role(String role) {
            this.role = role;
            return this;
        }

        public UserEntityBuilder extraInfo(String extraInfo) {
            this.extraInfo = extraInfo;
            return this;
        }

        public UserEntity build() {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(id);
            userEntity.setName(name);
            userEntity.setEmail(email);
            userEntity.setPassword(password);
            userEntity.setRole(role);
            userEntity.setExtraInfo(extraInfo);
            return userEntity;
        }
    }
}
