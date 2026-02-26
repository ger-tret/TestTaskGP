import com.hotel.service.entity.Address;
import com.hotel.service.entity.ArrivalTime;
import com.hotel.service.entity.Contact;
import com.hotel.service.entity.Hotel;
import com.hotel.service.entity.dto.HotelShortDto;
import com.hotel.service.repository.HotelRepository;
import com.hotel.service.service.HotelService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class HotelServiceIntegrationTest {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    @DisplayName("Find hotels by city working properly")
    void shouldSearchHotelsByCityInDatabase() {
        Hotel hotel = new Hotel();
        hotel.setName("Search Test");
        hotel.setAddress(new Address(1, "Street", "Mogilev", "Belarus", "111"));
        hotel.setContact(new Contact("111", "email"));
        hotel.setArrivalTime(new ArrivalTime("14:00", "12:00"));
        hotelRepository.save(hotel);

        List<HotelShortDto> result = hotelService.searchHotels(null, null, "Mogilev", null, null);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Search Test");
    }
}