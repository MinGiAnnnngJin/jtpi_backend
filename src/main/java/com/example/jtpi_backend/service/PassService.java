package com.example.jtpi_backend.service;

import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;

import java.util.List;

public interface PassService {
    PassDetailDTO fetchPassDetail(Integer passId);
    List<PassSearchResultDTO> fetchBookmarkResults(List<Integer> passIds);
}
