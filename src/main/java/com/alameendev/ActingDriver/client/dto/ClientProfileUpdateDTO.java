package com.alameendev.ActingDriver.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientProfileUpdateDTO {

    private String name;
    private String phone;
    private String address;
}
