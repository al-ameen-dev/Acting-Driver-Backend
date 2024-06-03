package com.alameendev.ActingDriver.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDTO {
    private Long carId;
    private String carNumber;
    private String model;
}
