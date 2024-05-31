package com.example.jtpi_backend.service;

import com.example.jtpi_backend.domain.SearchParameters;
import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.domain.dto.SlideShowPassDTO;

import java.util.List;

public interface PassService {

    //상세정보
    PassDetailDTO fetchPassDetail(Integer passId);
    //북마크
    List<PassSearchResultDTO> fetchBookmarkResults(List<Integer> passIds);

    // 추천
    List<SlideShowPassDTO> fetchSlideShowRecommendedPasses();
    // 신규
    List<SlideShowPassDTO> fetchSlideShowNewPasses();
    //검색
    List<PassSearchResultDTO> searchPasses(SearchParameters searchParams);


}
