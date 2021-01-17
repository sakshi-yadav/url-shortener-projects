package com.sakshi.urlRedirectionService.launcher;

public interface ShortenUrlSQLQuery {
    String GET_URL_ENTITY_BY_CODE = "from ShortenedUrl where code=:code";
}
