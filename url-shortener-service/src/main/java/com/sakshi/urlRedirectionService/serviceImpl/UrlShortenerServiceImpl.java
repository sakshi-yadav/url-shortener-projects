package com.sakshi.urlRedirectionService.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendoapp.service.DataAccessService;
import com.sakshi.urlRedirectionService.dto.request.UrlRequestDTO;
import com.sakshi.urlRedirectionService.dto.response.UrlResponseDTO;
import com.sakshi.urlRedirectionService.entities.ShortenedUrl;
import com.sakshi.urlRedirectionService.service.UrlShortenerService;
import com.sakshi.urlRedirectionService.utils.UrlShortenerUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UrlShortenerServiceImpl implements UrlShortenerService {
    @Value("${url.expiration.time}")
    private Long expirationTime;

    private final ObjectMapper mapper;
    private final DataAccessService dataAccessService;
    private final UrlShortenerUtil urlShortenerUtil;

    public UrlShortenerServiceImpl(ObjectMapper mapper, DataAccessService dataAccessService, UrlShortenerUtil urlShortenerUtil) {
        this.dataAccessService = dataAccessService;
        this.mapper = mapper;
        this.urlShortenerUtil = urlShortenerUtil;
    }

    @Override
    public UrlResponseDTO createShortUrl(UrlRequestDTO urlRequestDTO) {
        String shortUrl = urlShortenerUtil.encode(urlRequestDTO.getUrl());

        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setCode(shortUrl);
        shortenedUrl.setExpirationDate(new Date(System.currentTimeMillis() + expirationTime));
        shortenedUrl.setHits(0L);
        shortenedUrl.setUrl(urlRequestDTO.getUrl());
        dataAccessService.create(shortenedUrl);

        UrlResponseDTO urlResponseDTO = new UrlResponseDTO();
        urlResponseDTO.setShortUrl(shortUrl);
        return urlResponseDTO;
    }
}
