package com.hotel.service.entity.dto;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.Set;

@Data
public class HotelFullDto {
    @Id
    private Long id;
    private String name;
    private String description;
    private String brand;
    private AddressDto address;
    private ContactDto contact;
    private ArrivalTimeDto arrivalTime;
    private     Set<String> amenities;

}
