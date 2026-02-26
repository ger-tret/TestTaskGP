package com.hotel.service.controller;


import com.hotel.service.entity.dto.HotelCreateDto;
import com.hotel.service.entity.dto.HotelFullDto;
import com.hotel.service.entity.dto.HotelShortDto;
import com.hotel.service.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
@Validated
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/hotels")
    public ResponseEntity<HotelShortDto> createHotel(@Valid @RequestBody HotelCreateDto dto) {
        HotelShortDto created = hotelService.createHotel(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/hotels/{id}/amenities")
    public ResponseEntity<HotelFullDto> updateAmenities(
            @PathVariable Long id,
            @RequestBody List<String> amenities) {

        hotelService.addAmenities(id, amenities);
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelFullDto> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }
}