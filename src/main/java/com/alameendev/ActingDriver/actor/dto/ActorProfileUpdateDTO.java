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
public class ActorProfileUpdateDTO {
    private String name;
    private Integer driverExperience;
    private String biography;
    private String phone;
    private String credentials;
    private AvailabilityStatus availabilityStatus;
}
