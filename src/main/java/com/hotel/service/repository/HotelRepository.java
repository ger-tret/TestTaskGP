package com.hotel.service.repository;

import com.hotel.service.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
    @Query("SELECT h.brand AS label, COUNT(h) AS count FROM Hotel h GROUP BY h.brand")
    List<QueryResult> countByBrand();

    @Query("SELECT h.address.city AS label, COUNT(h) AS count FROM Hotel h GROUP BY h.address.city")
    List<QueryResult> countByCity();

    @Query("SELECT h.address.country AS label, COUNT(h) AS count FROM Hotel h GROUP BY h.address.country")
    List<QueryResult> countByCountry();

    @Query("SELECT a AS label, COUNT(h) AS count FROM Hotel h JOIN h.amenities a GROUP BY a")
    List<QueryResult> countByAmenities();
}
