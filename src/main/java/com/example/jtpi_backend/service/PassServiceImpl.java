package com.example.jtpi_backend.service;

import com.example.jtpi_backend.domain.SearchParameters;
import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.domain.dto.SlideShowPassDTO;
import com.example.jtpi_backend.domain.entity.PassInformation;
import com.example.jtpi_backend.repository.PassRepository;
import com.example.jtpi_backend.repository.PassRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassServiceImpl implements PassService {
 /*   private final PassRepositoryImpl passRepositoryImpl;*/
    private final PassRepository passRepository;

    @Autowired
    public PassServiceImpl(@Lazy PassRepository passRepository) {
    /*    this.passRepositoryImpl = passRepositoryImpl;*/
        this.passRepository = passRepository;
    }
    //상세정보
    @Override
    public PassDetailDTO fetchPassDetail(Integer passId) {
        return passRepository.findPassDetailById(passId);
    }

    //북마크
    @Override
    public List<PassSearchResultDTO> fetchBookmarkResults(List<Integer> passIds) {
        return passIds.stream()
                .map(passRepository::findBookmarkResultById)
                .collect(Collectors.toList());
    }

    //신규
    public List<SlideShowPassDTO> fetchSlideShowRecommendedPasses() {
        return passRepository.findSlideShowRecommendedPasses().stream()
                .map(this::convertToSlideShowPassDTO)
                .limit(4)
                .collect(Collectors.toList());
    }
    //추천
    public List<SlideShowPassDTO> fetchSlideShowNewPasses() {
        return passRepository.findSlideShowNewPasses().stream()
                .map(this::convertToSlideShowPassDTO)
                .limit(4)
                .collect(Collectors.toList());
    }
    //검색
    public List<PassSearchResultDTO> searchPasses(SearchParameters searchParams) {
        return passRepository.searchPassesByCriteria(
                        searchParams.getsearchQuery(),
                        searchParams.getDepartureCity(),
                        searchParams.getArrivalCity(),
                        searchParams.getTransportType(),
                        searchParams.getCityNames(),
                        searchParams.getDuration(),
                        searchParams.getMinPrice(),
                        searchParams.getMaxPrice()
                ).stream()
                .map(this::convertToPassSearchResultDTO)
                .collect(Collectors.toList());
    }

    private SlideShowPassDTO convertToSlideShowPassDTO(PassInformation data) {
        SlideShowPassDTO dto = new SlideShowPassDTO();
        dto.setId(data.getpassID());
        dto.setTitle(data.getTitle());
        dto.setImageUrl(data.getImageURL());
        return dto;
    }
    private PassSearchResultDTO convertToPassSearchResultDTO(PassInformation data) {
        PassSearchResultDTO dto = new PassSearchResultDTO();
        dto.setpassID(data.getpassID());
        dto.setImageUrl(data.getImageURL());
        dto.setTitle(data.getTitle());
        dto.setCityNames(data.getCityNames());
        dto.setPrice(data.getPrice());
        return dto;
    }
}
