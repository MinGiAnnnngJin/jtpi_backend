package com.example.jtpi_backend.unit;

import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.domain.entity.PassInformation;
import com.example.jtpi_backend.repository.PassRepository;
import com.example.jtpi_backend.repository.PassRepositorympl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PassRepositorymplTest {

    @Mock
    private PassRepository passRepository;

    @InjectMocks
    private PassRepositorympl passRepositorympl;

    @Test
    public void testFindById() {
        // Given
        PassInformation passInformation = createSamplePassInformation();
        when(passRepository.findById(anyInt())).thenReturn(Optional.of(passInformation));

        // When
        PassDetailDTO result = passRepositorympl.findById(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getPassId());
    }

    @Test
    public void testFindPassSearchResultById() {
        // Given
        PassInformation passInformation = createSamplePassInformation();
        when(passRepository.findById(anyInt())).thenReturn(Optional.of(passInformation));

        // When
        PassSearchResultDTO result = passRepositorympl.findPassSearchResultById(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getpassID());
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
