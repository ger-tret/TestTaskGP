package com.hotel.service.controller;

import com.hotel.service.entity.dto.HotelCreateDto;
import com.hotel.service.entity.dto.HotelFullDto;
import com.hotel.service.entity.dto.HotelShortDto;
import com.hotel.service.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/hotels")
    @Operation(summary = "Get all hotels despite everything")
    public ResponseEntity<List<HotelShortDto>> getAllHotels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        Page<HotelShortDto> hotelPage = hotelService.getAllHotels(pageable);

        return ResponseEntity.ok(hotelPage.getContent());
    }

    @GetMapping("/hotels/{id}")
    @Operation(summary = "Get information on hotel by ID")
    public ResponseEntity<HotelFullDto> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Search among all hotels by parameter")
    public ResponseEntity<List<HotelShortDto>> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenity) {
        return ResponseEntity.ok(hotelService.searchHotels(name, brand, city, country, amenity));
    }


    @PostMapping("/hotels")
    @Operation(summary = "Create new hotel")
    public ResponseEntity<HotelShortDto> createHotel(@Valid @RequestBody HotelCreateDto dto) {
        HotelShortDto created = hotelService.createHotel(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PostMapping("/hotels/{id}/amenities")
    @Operation(summary = "Add amenities to hotel")
    public ResponseEntity<HotelFullDto> addAmenities(
            @PathVariable Long id,
            @RequestBody List<String> amenities) {

        hotelService.addAmenities(id, amenities);
        // Возвращаем обновленный ресурс для подтверждения
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping("/histogram/{param}")
    @Operation(summary = "Get histogram")
    public ResponseEntity<Map<String, Long>> getHistogram(@PathVariable String param) {
        return ResponseEntity.ok(hotelService.getHistogram(param));
    }
}