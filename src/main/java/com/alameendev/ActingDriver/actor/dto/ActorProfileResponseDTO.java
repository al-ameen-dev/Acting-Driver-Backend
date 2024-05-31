package com.alameendev.ActingDriver.actor.dto;

import com.alameendev.ActingDriver.actor.entity.AvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActorProfileResponseDTO {

    private String name;
    private String phone;
    private Integer driverExperience;
    private String biography;
    private String credentials;
    private AvailabilityStatus availabilityStatus;
    private Double rating;
}
