package com.example.jtpi_backend.unit;

import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.domain.entity.PassInformation;
import com.example.jtpi_backend.service.PassServicempl;
import com.example.jtpi_backend.repository.PassRepositorympl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PassServicemplTest {

    @Mock
    private PassRepositorympl passRepositorympl;

    @InjectMocks
    private PassServicempl passServicempl;

    @Test
    public void testFetchPassDetail() {
        // Given
        PassInformation passInformation = createSamplePassInformation();

        when(passRepositorympl.findById(anyInt())).thenReturn(new PassDetailDTO(
                passInformation.getpassID(),
                passInformation.getImageURL(),
                passInformation.getTransportType(),
                passInformation.getTitle(),
                passInformation.getCityNames(),
                passInformation.getPrice(),
                passInformation.getPeriod(),
                passInformation.getDescription(),
                "Reservation info",
                "Refund info"
        ));

        // When
        PassDetailDTO result = passServicempl.fetchPassDetail(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getPassId());
        assertEquals("Sample Pass", result.getTitle());
        assertEquals(100, result.getPrice());
        assertEquals("Bus", result.getTransportType());
        assertEquals("Seoul", result.getCityNames());
        assertEquals("Sample description", result.getBenefit_information());
        assertEquals(7, result.getPeriod());
        assertEquals("http://example.com/image.jpg", result.getImageUrl());
    }

    @Test
    public void testFetchPassSearchResults() {
        // Given
        List<Integer> passIds = Arrays.asList(1, 2);
        PassInformation passInformation1 = createSamplePassInformation();
        PassInformation passInformation2 = createSamplePassInformation();
        passInformation2.setpassID(2);

        when(passRepositorympl.findPassSearchResultById(1)).thenReturn(new PassSearchResultDTO(
                passInformation1.getpassID(),
                passInformation1.getImageURL(),
                passInformation1.getTitle(),
                passInformation1.getPrice()
        ));

        when(passRepositorympl.findPassSearchResultById(2)).thenReturn(new PassSearchResultDTO(
                passInformation2.getpassID(),
                passInformation2.getImageURL(),
                passInformation2.getTitle(),
                passInformation2.getPrice()
        ));

        // When
        List<PassSearchResultDTO> results = passServicempl.fetchPassSearchResults(passIds);

        // Then
        assertNotNull(results);
        assertEquals(2, results.size());

        PassSearchResultDTO result1 = results.get(0);
        PassSearchResultDTO result2 = results.get(1);

        assertEquals(1, result1.getpassID());
        assertEquals(2, result2.getpassID());
    }

    private PassInformation createSamplePassInformation() {
        PassInformation passInformation = new PassInformation();
        passInformation.setpassID(1);
        passInformation.setTitle("Sample Pass");
        passInformation.setPrice(100);
        passInformation.setTransportType("Bus");
        passInformation.setCityNames("Seoul");
        passInformation.setDescription("Sample description");
        passInformation.setPeriod(7);
        passInformation.setImageURL("http://example.com/image.jpg");
        return passInformation;
    }
}
