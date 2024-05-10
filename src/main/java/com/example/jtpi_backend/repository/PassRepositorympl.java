package com.example.jtpi_backend.repository;

import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.domain.entity.PassInformation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PassRepositorympl {

    private final PassRepository passRepository;

    public PassRepositorympl(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    public PassDetailDTO findById(Integer passId) {
        Optional<PassInformation> passInformationOpt = passRepository.findById(passId);
        if (passInformationOpt.isPresent()) {
            PassInformation passInformation = passInformationOpt.get();
            return convertToPassDetailDTO(passInformation);
        }
        return null;
    }

    public PassSearchResultDTO findPassSearchResultById(Integer passId) {
        Optional<PassInformation> passInformationOpt = passRepository.findById(passId);
        if (passInformationOpt.isPresent()) {
            PassInformation passInformation = passInformationOpt.get();
            return convertToPassSearchResultDTO(passInformation);
        }
        return null;
    }

    private PassDetailDTO convertToPassDetailDTO(PassInformation passInformation) {
        return new PassDetailDTO(
                passInformation.getpassID(),
                passInformation.getImageURL(),
                passInformation.getTransportType(),
                passInformation.getTitle(),
                passInformation.getCityNames(),
                passInformation.getPrice(),
                passInformation.getPeriod(),
                passInformation.getDescription(),
                "Reservation information here",
                "Refund information here"
        );
    }

    private PassSearchResultDTO convertToPassSearchResultDTO(PassInformation passInformation) {
        PassSearchResultDTO dto = new PassSearchResultDTO();
        dto.setpassID(passInformation.getpassID());
        dto.setImageUrl(passInformation.getImageURL());
        dto.setTitle(passInformation.getTitle());
        dto.setPrice(passInformation.getPrice());
        return dto;
    }
}
