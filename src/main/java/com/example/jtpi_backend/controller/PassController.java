package com.example.jtpi_backend.controller;
import com.example.jtpi_backend.domain.SearchParameters;
import com.example.jtpi_backend.domain.dto.PassDetailDTO;
import com.example.jtpi_backend.domain.dto.PassSearchResultDTO;
import com.example.jtpi_backend.domain.dto.SlideShowPassDTO;
import com.example.jtpi_backend.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/passes")
public class PassController {

    private final PassService passService;

    @Autowired
    public PassController(@Lazy PassService passService) {
        this.passService = passService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    //신규
    @GetMapping("/slideshow/new")
    public List<SlideShowPassDTO> getSlideShowNewPasses() {
        return passService.fetchSlideShowNewPasses();
    }
    //추천
    @GetMapping("/slideshow/recommended")
    public List<SlideShowPassDTO> getSlideShowRecommendedPasses() {
        return passService.fetchSlideShowRecommendedPasses();
    }
    //검색
    @PostMapping("/search")
    public List<PassSearchResultDTO> searchPasses(@RequestBody SearchParameters searchParams) {
        return passService.searchPasses(searchParams);
    }

    @GetMapping("/{passId}")
    public PassDetailDTO getPassDetail(@PathVariable Integer passId) {

        return passService.fetchPassDetail(passId);
    }

    @PostMapping("/bookmark")
    public List<PassSearchResultDTO> getBookmarkResults(@RequestBody List<Integer> passIds) {
        return passService.fetchBookmarkResults(passIds);
    }
}
