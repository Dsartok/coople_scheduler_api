package com.coople.gamepartnerservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing a user in the application.
 * This class is annotated with Lombok's @Getter and @Setter to automatically generate getters and setters.
 * It is also annotated as a JPA Entity, mapped to the "users" table.
 *
 * <p>The builder class, {@link UserEntityBuilder}, is a nested static class providing a fluent interface for
 * constructing instances of {@link UserEntity}.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * The email address of the user.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The password associated with the user's account.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The role assigned to the user.
     */
    @Column(nullable = false)
    private String role;

    /**
     * Additional information about the user.
     */
    private String extraInfo;

    @OneToMany(mappedBy = "user")
    private Set<ScheduleEntity> schedules = new HashSet<>();

    /**
     * Static builder method for creating instances of {@link UserEntity}.
     *
     * @return A new instance of {@link UserEntityBuilder}.
     */
    public static UserEntityBuilder builder() {
        return new UserEntityBuilder();
    }

    /**
     * Builder class for constructing instances of {@link UserEntity}.
     */
    public static class UserEntityBuilder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String role;
        private String extraInfo;

        private Set<ScheduleEntity> schedules = new HashSet<>();

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

        public UserEntityBuilder schedules(Set<ScheduleEntity> schedules) {
            this.schedules = schedules;
            return this;
        }

        /**
         * Builds and returns a new instance of {@link UserEntity}.
         *
         * @return A new instance of {@link UserEntity}.
         */
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
