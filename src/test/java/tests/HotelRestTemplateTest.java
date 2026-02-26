package tests;

import com.hotel.service.HotelServiceApplication;
import com.hotel.service.entity.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = HotelServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Создание отеля через POST и проверка ответа")
    void shouldCreateHotelAndCheckResponse() {
        HotelCreateDto createDto = new HotelCreateDto();
        createDto.setName("Grand Budapest");
        createDto.setBrand("Independent");

        AddressDto address = new AddressDto();
        address.setHouseNumber(1);
        address.setStreet("Alpine St");
        address.setCity("Zubrowka");
        address.setCountry("Republic of Zubrowka");
        address.setPostCode("123456");
        createDto.setAddress(address);

        ContactDto contact = new ContactDto();
        contact.setPhone("+555-01-02");
        contact.setEmail("concierge@grandbudapest.com");
        createDto.setContact(contact);

        ArrivalTimeDto arrival = new ArrivalTimeDto();
        arrival.setCheckIn("12:00");
        arrival.setCheckOut("10:00");
        createDto.setArrivalTime(arrival);

        ResponseEntity<HotelShortDto> response = restTemplate.postForEntity(
                "/property-view/hotels",
                createDto,
                HotelShortDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Grand Budapest");
        assertThat(response.getHeaders().getLocation()).isNotNull(); // Проверка Location заголовка
    }
}