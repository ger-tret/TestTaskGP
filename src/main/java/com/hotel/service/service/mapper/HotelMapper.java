package com.hotel.service.service.mapper;

import com.hotel.service.entity.Address;
import com.hotel.service.entity.Hotel;
import com.hotel.service.entity.dto.HotelCreateDto;
import com.hotel.service.entity.dto.HotelFullDto;
import com.hotel.service.entity.dto.HotelShortDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(target = "address", source = "address", qualifiedByName = "formatAddress")
    @Mapping(target = "phone", source = "contact.phone")
    HotelShortDto toShortDto(Hotel hotel);

    HotelFullDto toFullDto(Hotel hotel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    Hotel toEntity(HotelCreateDto dto);

    @Named("formatAddress")
    default String formatAddress(Address address) {
        if (address == null) return null;
        return String.format("%d %s, %s, %s, %s",
                address.getHouseNumber(), address.getStreet(),
                address.getCity(), address.getPostCode(), address.getCountry());
    }
}
