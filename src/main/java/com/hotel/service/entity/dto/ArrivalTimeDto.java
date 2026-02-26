package com.hotel.service.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArrivalTimeDto {
    @NotBlank(message = "Check-in time is mandatory")
    private String checkIn;
    private String checkOut;
}
