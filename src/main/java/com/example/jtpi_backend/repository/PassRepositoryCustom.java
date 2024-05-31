package com.example.jtpi_backend.repository;

import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.domain.entity.PassInformation;

import java.util.List;

public interface PassRepositoryCustom {
    List<PassInformation> searchPassesByCriteria(
            String searchquery,
            String departureCity,
            String arrivalCity,
            String transportType,
            String cityNames,
            Integer duration,
            Integer minPrice,
            Integer maxPrice
    );
    // 신규 슬라이드용
    List<PassInformation> findSlideShowNewPasses();

    // 추천 슬라이드용
    List<PassInformation> findSlideShowRecommendedPasses();

    PassDetailDTO findPassDetailById(Integer passId);

    PassSearchResultDTO findBookmarkResultById(Integer passId);


}
