package com.hotel.service.entity.dto;

import lombok.Data;

@Data
public class HotelCreateDto {
    private String name;
    private String description;
    private String brand;
    private AddressDto address;
    private ContactDto contacts;
    private ArrivalTimeDto arrivalTime;
}
