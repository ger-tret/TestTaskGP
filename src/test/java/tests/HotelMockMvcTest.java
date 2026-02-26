package tests;

import com.hotel.service.HotelServiceApplication;
import com.hotel.service.entity.dto.HotelShortDto;
import com.hotel.service.service.HotelService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = HotelServiceApplication.class)
@AutoConfigureMockMvc
public class HotelMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @Test
    @DisplayName("GET /hotels должен возвращать список отелей с учетом пагинации")
    void shouldReturnHotelsList() throws Exception {
        HotelShortDto dto = new HotelShortDto();
        dto.setId(1L);
        dto.setName("Mock Hotel");
        dto.setAddress("Street 1");

        PageImpl<HotelShortDto> hotelPage = new PageImpl<>(List.of(dto));

        when(hotelService.getAllHotels(any(Pageable.class))).thenReturn(hotelPage);

        mockMvc.perform(get("/property-view/hotels")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Mock Hotel")))
                .andExpect(jsonPath("$[0].id", is(1)));
    }
}