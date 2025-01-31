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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    @Autowired
    private WebApplicationContext context;
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    //신규
    @Test
    void testGetSlideShowNewPasses() throws Exception {
        // 데이터베이스에 있는 슬라이드쇼 새로운 패스를 예상값으로 설정
        List<SlideShowPassDTO> expectedSlideShowNewPasses = List.of(
                new SlideShowPassDTO(87, "에치고 원데이 패스", "https://www.jreast.co.jp/niigata/echigo-1day2daypass/img/1day-logo.png"),
                new SlideShowPassDTO(18, "에치고 투데이 패스", "https://www.jreast.co.jp/niigata/echigo-1day2daypass/img/2day-logo.png"),
                new SlideShowPassDTO(21, "모노레일과 야마노테선 할인 티켓", "https://www.tokyo-monorail.co.jp/common/template/img/tp_pc_logo.png"),
                new SlideShowPassDTO(16, "도쿄 시내 패스(도쿠나이 패스)", "https://www.jreast.co.jp/multi/material/img/logo_jreast.png")
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
public void testSearchPasses() throws Exception {
    // 검색 파라미터 설정
    SearchParameters searchParams = new SearchParameters();
    searchParams.setsearchQuery("0");
    searchParams.setDepartureCity("0");
    searchParams.setArrivalCity("0");
    searchParams.setTransportType("0");
    searchParams.setCityNames("0");
    searchParams.setDuration(2);
    searchParams.setMinPrice(0);
    searchParams.setMaxPrice(0);

    // API 호출 및 응답 검증
    MvcResult mvcResult = mockMvc.perform(post("/passes/search")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(searchParams))
                    .characterEncoding("UTF-8"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();

    // 응답 본문을 JSON 문자열로 추출
    String jsonResponse = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

    // 응답 본문 출력 (디버깅을 위해)
    System.out.println("API 응답: " + jsonResponse);

    // JSON 응답을 객체로 변환
    PassSearchResultDTO[] passResponses = objectMapper.readValue(jsonResponse, PassSearchResultDTO[].class);
    assertNotNull(passResponses);
    assertTrue(passResponses.length > 0);

    // 응답 내용을 모두 출력
    for (PassSearchResultDTO pass : passResponses) {
        System.out.println("제목: " + pass.getTitle());
        // 필요한 다른 필드들도 출력할 수 있습니다.
    }
}


@Test
public void testGetPassDetail() throws Exception {
    // 테스트 데이터베이스에 이미 존재하는 Pass ID를 사용
    int existingPassId = 1; // 실제 존재하는 Pass ID로 변경해야 함

    mockMvc.perform(get("/passes/" + existingPassId))
            .andExpect(status().isOk())  // HTTP 응답 상태가 200 OK인지 확인
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // 응답 Content-Type이 application/json인지 확인
            .andExpect(jsonPath("$.passId", is(existingPassId)))  // passId가 예상값과 일치하는지 확인
            .andExpect(jsonPath("$.imageUrl", is("https://www.haneda-tokyo-access.com/en/ticket/discount/img/index_bn06.jpg"))) // imageUrl이 예상값과 일치하는지 확인
            .andExpect(jsonPath("$.length()").value(14))
            .andDo(print()); // 응답 내용을 콘솔에 출력
}

    @Test
    public void testGetBookmarkResults() throws Exception {
        // 데이터베이스에 이미 존재하는 Pass ID 목록을 사용
        List<Integer> existingPassIds = List.of(1, 2, 3, 4); // 실제 존재하는 Pass ID로 변경해야 함

        mockMvc.perform(post("/passes/bookmark")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(existingPassIds)))
                .andExpect(status().isOk())  // HTTP 응답 상태가 200 OK인지 확인
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // 응답 Content-Type이 application/json인지 확인
                .andExpect(jsonPath("$", hasSize(4)))  // JSON 배열의 크기가 4인지 확인
                .andExpect(jsonPath("$[0].passID", is(existingPassIds.get(0))))  // 첫 번째 요소의 passID가 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[1].passID", is(existingPassIds.get(1))))  // 두 번째 요소의 passID가 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[2].passID", is(existingPassIds.get(2))))  // 세 번째 요소의 passID가 예상값과 일치하는지 확인
                .andExpect(jsonPath("$[3].passID", is(existingPassIds.get(3))));  // 네 번째 요소의 passID가 예상값과 일치하는지 확인
    }
}
