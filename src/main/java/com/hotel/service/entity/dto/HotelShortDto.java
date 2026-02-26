package com.hotel.service.entity.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class HotelShortDto {
    @Id
    Long id;
    String name;
    String description;
    String address;
    String phone;
}
