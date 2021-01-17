package com.sakshi.urlRedirectionService.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public interface UrlRedirectionService {
    void redirectUrl(String shortUrl, HttpServletResponse httpServletResponse) throws IOException;
}
