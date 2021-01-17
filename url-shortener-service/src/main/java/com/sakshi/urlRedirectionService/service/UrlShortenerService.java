package com.sakshi.urlRedirectionService.service;

import com.sakshi.urlRedirectionService.dto.request.UrlRequestDTO;
import com.sakshi.urlRedirectionService.dto.response.UrlResponseDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public interface UrlShortenerService {
    UrlResponseDTO createShortUrl(UrlRequestDTO urlRequestDTO);
}
