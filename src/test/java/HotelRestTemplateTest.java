import com.hotel.service.entity.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HotelRestTemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Testing creating hotel through endpoint")
    void shouldCreateHotelAndCheckResponse() {
        HotelCreateDto createDto = new HotelCreateDto();
        createDto.setName("Grand Budapest");
        createDto.setAddress(new AddressDto(1, "Alpine St", "Zubrowka", "Country", "123"));
        createDto.setContact(new ContactDto("555", "hotel@grand.com"));
        createDto.setArrivalTime(new ArrivalTimeDto("12:00", "10:00"));

        ResponseEntity<HotelShortDto> response = restTemplate.postForEntity(
                "/property-view/hotels",
                createDto,
                HotelShortDto.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Grand Budapest");
        assertThat(response.getBody().getId()).isPositive();
    }
}