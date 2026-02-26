package com.hotel.service.entity.dto;

import lombok.Data;

@Data
public class HotelCreateDto {
    String name;
    String description;
    String brand;
    AddressDto address;
    ContactDto contacts;
    ArrivalTimeDto arrivalTime;
}
