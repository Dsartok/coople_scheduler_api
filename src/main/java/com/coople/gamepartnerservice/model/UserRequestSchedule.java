package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;

/**
 * A data transfer object (DTO) representing a request to set user in schedule.
 * This class is annotated with Lombok's @Getter and @Builder to automatically generate getters and a builder.
 *
 * <p>The field in this class include information id.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Builder
public class UserRequestSchedule {

    /**
     * The id of the user.
     */
    private Long id;
}
