package com.alameendev.ActingDriver.client.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientProfileResponseDTO {

    private Long clientId;
    private String name;
    private String phone;
    private String address;
}
