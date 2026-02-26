package com.hotel.service.service;


import com.hotel.service.entity.dto.HotelCreateDto;
import com.hotel.service.entity.dto.HotelFullDto;
import com.hotel.service.entity.dto.HotelShortDto;

import java.util.List;
import java.util.Map;

public interface HotelService {
    List<HotelShortDto> getAllHotels();
    HotelFullDto getHotelById(Long id);
    HotelShortDto createHotel(HotelCreateDto dto);
    void addAmenities(Long id, List<String> amenities);
    List<HotelShortDto> searchHotels(String name, String brand, String city, String country, String amenity);
    Map<String, Long> getHistogram(String param);
}