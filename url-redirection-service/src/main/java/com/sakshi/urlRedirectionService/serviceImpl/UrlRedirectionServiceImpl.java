package com.sakshi.urlRedirectionService.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendoapp.service.DataAccessService;
import com.sakshi.urlRedirectionService.entities.ShortenedUrl;
import com.sakshi.urlRedirectionService.service.UrlRedirectionService;
import com.sakshi.urlRedirectionService.utils.UrlShortenerUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.sakshi.urlRedirectionService.launcher.ShortenUrlSQLQuery.GET_URL_ENTITY_BY_CODE;


@Service
public class UrlRedirectionServiceImpl implements UrlRedirectionService {
    @Value("${url.expiration.time}")
    private Long expirationTime;

    private final ObjectMapper mapper;
    private final DataAccessService dataAccessService;
    private final UrlShortenerUtil urlShortenerUtil;

    public UrlRedirectionServiceImpl(ObjectMapper mapper, DataAccessService dataAccessService, UrlShortenerUtil urlShortenerUtil) {
        this.dataAccessService = dataAccessService;
        this.mapper = mapper;
        this.urlShortenerUtil = urlShortenerUtil;
    }

    @Override
    public void redirectUrl(String shortUrl, HttpServletResponse httpServletResponse) throws IOException {
        HashMap<String,Object> params = new HashMap<>();
        params.put("code", shortUrl);

        List<ShortenedUrl> shortenedUrlList = (List<ShortenedUrl>)dataAccessService.read(GET_URL_ENTITY_BY_CODE,params);
        if(Objects.isNull(shortenedUrlList) || shortenedUrlList.isEmpty())
            throw new EntityNotFoundException("Url is not known");

        if (shortenedUrlList.get(0).getExpirationDate() != null && shortenedUrlList.get(0).getExpirationDate().before(new Date())){
            throw new EntityNotFoundException("Link expired!");
        }

        httpServletResponse.sendRedirect(shortenedUrlList.get(0).getUrl());
    }
}
