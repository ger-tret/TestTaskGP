package com.hotel.service.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    @NotBlank(message = "Phone is mandatory")
    private String phone;
    @NotBlank @Email(message = "Invalid email format")
    private String email;
}