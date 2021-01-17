package com.sakshi.urlRedirectionService.controller;

import com.lendoapp.response.ApiResponse;
import com.sakshi.urlRedirectionService.constants.CommonConstants;
import com.sakshi.urlRedirectionService.dto.request.UrlRequestDTO;
import com.sakshi.urlRedirectionService.dto.response.UrlResponseDTO;
import com.sakshi.urlRedirectionService.service.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/url/shortener")
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public ApiResponse redirectUrl(@RequestBody UrlRequestDTO urlRequestDTO) throws IOException {
        return new ApiResponse(HttpStatus.OK, true,urlShortenerService.createShortUrl(urlRequestDTO),CommonConstants.REQUEST_COMPLEDTED_SUCCESSFULLY);
    }
}

