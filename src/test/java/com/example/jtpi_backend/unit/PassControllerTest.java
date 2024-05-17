package com.example.jtpi_backend.unit;

import com.example.jtpi_backend.controller.PassController;
import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.service.PassService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PassController.class)
public class PassControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassService passService;

    @Test
    public void testGetPassDetail() throws Exception {
        // Given
        PassDetailDTO passDetailDTO = new PassDetailDTO(
                1,
                "http://example.com/image.jpg",
                "Bus",
                "Sample Pass",
                "Seoul",
                100,
                7,
                "Sample description",
                "Reservation info",
                "Refund info"
        );

        when(passService.fetchPassDetail(anyInt())).thenReturn(passDetailDTO);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/passes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.passId").value(1))
                .andExpect(jsonPath("$.title").value("Sample Pass"))
                .andExpect(jsonPath("$.price").value(100));
    }

    @Test
    public void testGetBookmarkResults() throws Exception {
        // Given
        List<PassSearchResultDTO> searchResults = Arrays.asList(
                new PassSearchResultDTO(1, "http://example.com/image1.jpg", "Pass 1", 100),
                new PassSearchResultDTO(2, "http://example.com/image2.jpg", "Pass 2", 150)
        );

        when(passService.fetchBookmarkResults(anyList())).thenReturn(searchResults);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/passes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1, 2]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].passID").value(1))
                .andExpect(jsonPath("$[0].title").value("Pass 1"))
                .andExpect(jsonPath("$[0].price").value(100))
                .andExpect(jsonPath("$[1].passID").value(2))
                .andExpect(jsonPath("$[1].title").value("Pass 2"))
                .andExpect(jsonPath("$[1].price").value(150));
    }
}
