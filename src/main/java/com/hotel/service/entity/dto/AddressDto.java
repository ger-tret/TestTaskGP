package com.hotel.service.entity.dto;

import lombok.Data;

@Data
public class AddressDto {
    Integer houseNumber;
    String street;
    String city;
    String country;
    String postCode;
}
