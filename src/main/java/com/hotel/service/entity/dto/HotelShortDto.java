package com.hotel.service.entity.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class HotelShortDto {
    @Id
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}
