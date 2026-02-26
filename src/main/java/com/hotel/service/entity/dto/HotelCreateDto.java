package com.hotel.service.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelCreateDto {
    @NotBlank(message = "Name is mandatory")
    @Schema(example = "DoubleTree by Hilton Minsk")
    private String name;

    private String description;

    @NotBlank(message = "Brand is mandatory")
    @Schema(example = "Hilton")
    private String brand;

    @NotNull(message = "Address is mandatory")
    @Valid
    private AddressDto address;

    @NotNull(message = "Contact are mandatory")
    @Valid
    private ContactDto contact;

    @NotNull(message = "Arrival time is mandatory")
    @Valid
    private ArrivalTimeDto arrivalTime;
}
