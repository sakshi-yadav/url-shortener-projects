package com.sakshi.urlRedirectionService.controller;

import com.sakshi.urlRedirectionService.service.UrlRedirectionService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/url/redirection")
public class UrlRedirectionController {
    private final UrlRedirectionService urlRedirectionService;

    public UrlRedirectionController(UrlRedirectionService urlRedirectionService) {
        this.urlRedirectionService = urlRedirectionService;
    }

    @GetMapping(value = "/{shortUrl}")
    public void redirectUrl(@PathVariable(name = "shortUrl") String shortUrl, HttpServletResponse httpServletResponse) throws IOException {
        urlRedirectionService.redirectUrl(shortUrl, httpServletResponse);
    }
}

