package com.example.jtpi_backend.controller;

import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passes")
public class PassController {

    private final PassService passService;

    @Autowired
    public PassController(PassService passService) {
        this.passService = passService;
    }

    @GetMapping("/{passId}")
    public PassDetailDTO getPassDetail(@PathVariable Integer passId) {
        return passService.fetchPassDetail(passId);
    }

    @PostMapping("/search")
    public List<PassSearchResultDTO> getPassSearchResults(@RequestBody List<Integer> passIds) {
        return passService.fetchPassSearchResults(passIds);
    }
}
