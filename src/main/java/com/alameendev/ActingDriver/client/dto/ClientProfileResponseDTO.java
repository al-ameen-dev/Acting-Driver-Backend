package com.alameendev.ActingDriver.client.dto;


import com.alameendev.ActingDriver.client.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientProfileResponseDTO {

    private Long clientId;
    private String name;
    private String phone;
    private String address;
    private List<Car> cars;
}
