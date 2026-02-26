package com.hotel.service.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AddressDto {
    @NotNull(message = "House number is mandatory")
    private Integer houseNumber;
    @NotBlank(message = "Street is mandatory")
    private String street;
    @NotBlank(message = "City is mandatory")
    private String city;
    @NotBlank(message = "Country is mandatory")
    private String country;
    @NotBlank(message = "Post code is mandatory")
    private String postCode;
}
