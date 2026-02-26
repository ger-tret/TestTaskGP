package com.hotel.service.entity.dto;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.Set;

@Data
public class HotelFullDto {
    @Id
    Long id;
    String name;
    String description;
    String brand;
    AddressDto address;
    ContactDto contact;
    ArrivalTimeDto arrivalTime;
    Set<String> amenities;

}
