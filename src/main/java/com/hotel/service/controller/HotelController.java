package com.hotel.service.controller;


import com.hotel.service.entity.dto.HotelCreateDto;
import com.hotel.service.entity.dto.HotelFullDto;
import com.hotel.service.entity.dto.HotelShortDto;
import com.hotel.service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/hotels")
    public List<HotelShortDto> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotels/{id}")
    public HotelFullDto getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("/search")
    public List<HotelShortDto> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String amenity) {
        return hotelService.searchHotels(name, brand, city, country, amenity);
    }

    @PostMapping("/hotels")
    public HotelShortDto createHotel(@RequestBody HotelCreateDto dto) {
        return hotelService.createHotel(dto);
    }

    @PostMapping("/hotels/{id}/amenities")
    public void addAmenities(@PathVariable Long id, @RequestBody List<String> amenities) {
        hotelService.addAmenities(id, amenities);
    }

    @GetMapping("/histogram/{param}")
    public Map<String, Long> getHistogram(@PathVariable String param) {
        return hotelService.getHistogram(param);
    }
}