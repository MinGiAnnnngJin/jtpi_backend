package com.example.jtpi_backend;
import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.domain.dto.SlideShowPassDTO;
import com.example.jtpi_backend.domain.SearchParameters;
import com.example.jtpi_backend.service.PassService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PassControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PassService passService;

    private PassDetailDTO mockPassDetail;
   //신규
    @Test
    void testGetSlideShowNewPasses() throws Exception {
        // 데이터베이스에 있는 슬라이드쇼 새로운 패스를 예상값으로 설정
        List<SlideShowPassDTO> expectedSlideShowNewPasses = List.of(
                new SlideShowPassDTO(10, "Shiz", "http://example.com/image10.jpg"),
                new SlideShowPassDTO(9, "Jenn", "http://example.com/image9.jpg"),
                new SlideShowPassDTO(8, "Lisa", "http://example.com/image8.jpg"),
                new SlideShowPassDTO(7, "Quick", "http://example.com/image7.jpg")
        );

        mockMvc.perform(get("/passes/slideshow/new"))
                .andExpect(status().isOk())  // HTTP 응답 상태가 200 OK인지 확인
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // 응답 Content-Type이 application/json인지 확인
                .andExpect(jsonPath("$", hasSize(4)))  // JSON 배열의 크기가 4인지 확인
                .andExpect(jsonPath("$[0].id", is(expectedSlideShowNewPasses.get(0).getId())))  // 첫 번째 요소의 id가 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[0].title", is(expectedSlideShowNewPasses.get(0).getTitle())))  // 첫 번째 요소의 title이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[0].imageUrl", is(expectedSlideShowNewPasses.get(0).getImageUrl())))  // 첫 번째 요소의 imageUrl이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[1].id", is(expectedSlideShowNewPasses.get(1).getId())))  // 두 번째 요소의 id가 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[1].title", is(expectedSlideShowNewPasses.get(1).getTitle())))  // 두 번째 요소의 title이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[1].imageUrl", is(expectedSlideShowNewPasses.get(1).getImageUrl())))  // 두 번째 요소의 imageUrl이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[2].id", is(expectedSlideShowNewPasses.get(2).getId())))  // 세 번째 요소의 id가 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[2].title", is(expectedSlideShowNewPasses.get(2).getTitle())))  // 세 번째 요소의 title이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[2].imageUrl", is(expectedSlideShowNewPasses.get(2).getImageUrl())))  // 세 번째 요소의 imageUrl이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[3].id", is(expectedSlideShowNewPasses.get(3).getId())))  // 네 번째 요소의 id가 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[3].title", is(expectedSlideShowNewPasses.get(3).getTitle())))  // 네 번째 요소의 title이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[3].imageUrl", is(expectedSlideShowNewPasses.get(3).getImageUrl())));  // 네 번째 요소의 imageUrl이 예상값과 일치하는지 확인
    }

    //추천
    @Test
    void testGetSlideShowRecommendedPasses() throws Exception {
        mockMvc.perform(get("/passes/slideshow/recommended"))
                .andExpect(status().isOk())  // HTTP 응답 상태가 200 OK인지 확인
                .andExpect((ResultMatcher) jsonPath("$", hasSize(4)))  // JSON 배열의 크기가 4인지 확인
                .andExpect((ResultMatcher) jsonPath("$[0].id", notNullValue()))  // 첫 번째 요소에 title 속성이 존재하는지 확인
                .andExpect((ResultMatcher) jsonPath("$[0].title", notNullValue()))  // 첫 번째 요소에 description 속성이 존재하는지 확인
                .andExpect((ResultMatcher) jsonPath("$[0].imageUrl", notNullValue()));  // 첫 번째 요소에 imageUrl 속성이 존재하는지 확인
    }

// 검색
    @Test
    void testSearchPasses() throws Exception {
        SearchParameters searchParams = new SearchParameters();
        searchParams.setQuery("Tokyo");
        searchParams.setDepartureCity("Test Departure");
        searchParams.setArrivalCity("Test Arrival");
        searchParams.setTransportType("Bus");
        searchParams.setCityNames("도쿄");
        searchParams.setDuration(7);
        searchParams.setQuantityAdults(2);
        searchParams.setQuantityChildren(1);

        // 데이터베이스에 있는 검색 결과를 예상값으로 설정
        List<PassSearchResultDTO> expectedSearchResults = List.of(
                new PassSearchResultDTO(1, "Tokyo", "http://example.com/image1.jpg", "Tokyo", 100),
                new PassSearchResultDTO(2, "Kyoto", "http://example.com/image2.jpg", "Kyoto", 150),
                new PassSearchResultDTO(5, "Simpl", "http://example.com/image5.jpg", "City,도쿄", 70),
                new PassSearchResultDTO(7, "Quick", "http://example.com/image7.jpg", "dlas", 60),
                new PassSearchResultDTO(8, "Lisa", "http://example.com/image8.jpg", "dsajf", 110),
                new PassSearchResultDTO(9, "Jenn", "http://example.com/image9.jpg", "dd", 120),
                new PassSearchResultDTO(10, "Shiz", "http://example.com/image10.jpg", "Shizuoka", 90)
        );

        // API 호출 및 응답 검증
        mockMvc.perform(post("/passes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchParams)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(expectedSearchResults)));
    }

    @BeforeEach
    public void setup() {
        mockPassDetail = new PassDetailDTO(
                1,
                "http://example.com/image.jpg",
                "Bus",
                "Test Title",
                "City Names",
                100,
                30,
                "Test Product Description",
                "Test Benefit Information",
                "Test Reservation Information",
                "Test Refund Information"
        );
    }

    @Test
    public void testGetPassDetail() throws Exception {
        when(passService.fetchPassDetail(anyInt())).thenReturn(mockPassDetail);

        mockMvc.perform(get("/passes/1"))
                .andExpect(status().isOk())  // HTTP 응답 상태가 200 OK인지 확인
                .andExpect(content().contentType("application/json"))  // 응답 Content-Type이 application/json인지 확인
                .andExpect(jsonPath("$.passId", is(mockPassDetail.getPassId())))  // passId가 예상값과 일치하는지 확인
                .andExpect(jsonPath("$.imageUrl", is(mockPassDetail.getImageUrl())))  // imageUrl이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$.transportType", is(mockPassDetail.getTransportType())))  // transportType이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$.title", is(mockPassDetail.getTitle())))  // title이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$.cityNames", is(mockPassDetail.getCityNames())))  // cityNames이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$.price", is(mockPassDetail.getPrice())))  // price가 예상값과 일치하는지 확인
                .andExpect(jsonPath("$.period", is(mockPassDetail.getPeriod())))  // period가 예상값과 일치하는지 확인
                .andExpect(jsonPath("$.productDescription", is(mockPassDetail.getProductDescription())))  // productDescription이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$.benefit_information", is(mockPassDetail.getBenefit_information())))  // benefit_information이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$.reservation_information", is(mockPassDetail.getReservation_information())))  // reservation_information이 예상값과 일치하는지 확인
                .andExpect(jsonPath("$.refund_information", is(mockPassDetail.getRefund_information())));  // refund_information이 예상값과 일치하는지 확인
    }
}
