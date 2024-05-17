package com.example.jtpi_backend.service;

import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.repository.PassRepositorympl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassServicempl implements PassService {
    private final PassRepositorympl passRepositorympl;

    @Autowired
    public PassServicempl(PassRepositorympl passRepositorympl) {
        this.passRepositorympl = passRepositorympl;
    }

    @Override
    public PassDetailDTO fetchPassDetail(Integer passId) {
        return passRepositorympl.findById(passId);
    }

    @Override
    public List<PassSearchResultDTO> fetchBookmarkResults(List<Integer> passIds) {
        return passIds.stream()
                .map(passRepositorympl::findBookmarkResultById)
                .collect(Collectors.toList());
    }
}
