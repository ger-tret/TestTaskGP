package com.hotel.service.service;

import com.hotel.service.entity.Hotel;
import com.hotel.service.entity.dto.HotelCreateDto;
import com.hotel.service.entity.dto.HotelFullDto;
import com.hotel.service.entity.dto.HotelShortDto;
import com.hotel.service.exception.ResourceNotFoundException;
import com.hotel.service.repository.HotelRepository;
import com.hotel.service.repository.QueryResult;
import com.hotel.service.service.mapper.HotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public List<HotelShortDto> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::toShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public HotelFullDto getHotelById(Long id) {
        return hotelRepository.findById(id)
                .map(hotelMapper::toFullDto)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
    }

    @Override
    @Transactional
    public HotelShortDto createHotel(HotelCreateDto dto) {
        Hotel hotel = hotelMapper.toEntity(dto);
        Hotel saved = hotelRepository.save(hotel);
        return hotelMapper.toShortDto(saved);
    }

    @Override
    @Transactional
    public void addAmenities(Long id, List<String> amenities) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        if (hotel.getAmenities() == null) {
            hotel.setAmenities(new HashSet<>());
        }
        hotel.getAmenities().addAll(amenities);
        hotelRepository.save(hotel);
    }

    @Override
    public List<HotelShortDto> searchHotels(String name, String brand, String city, String country, String amenity) {
        Specification<Hotel> spec = Specification.where(null);

        if (name != null) spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        if (brand != null) spec = spec.and((root, query, cb) -> cb.equal(root.get("brand"), brand));
        if (city != null) spec = spec.and((root, query, cb) -> cb.equal(root.get("address").get("city"), city));
        if (country != null) spec = spec.and((root, query, cb) -> cb.equal(root.get("address").get("country"), country));
        if (amenity != null) spec = spec.and((root, query, cb) -> cb.isMember(amenity, root.get("amenities")));

        return hotelRepository.findAll(spec).stream()
                .map(hotelMapper::toShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getHistogram(String param) {
        List<QueryResult> results = switch (param.toLowerCase()) {
            case "brand" -> hotelRepository.countByBrand();
            case "city" -> hotelRepository.countByCity();
            case "country" -> hotelRepository.countByCountry();
            case "amenities" -> hotelRepository.countByAmenities();
            default -> throw new IllegalArgumentException("Invalid histogram parameter: " + param);
        };

        return results.stream()
                .filter(r -> r.getLabel() != null)
                .collect(Collectors.toMap(
                        QueryResult::getLabel,
                        QueryResult::getCount
                ));
    }
}