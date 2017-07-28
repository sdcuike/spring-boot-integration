package com.sdcuike.spring.extend.web.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map.Entry;

/**
 * Utility class for handling pagination.
 * <p>
 * <p>
 * Pagination uses the same principles as the <a href="https://developer.github.com/v3/#pagination">Github API</a>,
 * and follow <a href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Link header)</a>.
 */
public final class PaginationUtil {
    
    public static void setPaginationHttpHeaders(final Page<?> page,
                                                final HttpServletRequest request,
                                                final HttpServletResponse response) throws URISyntaxException {
        
        String contextPath = request.getContextPath();
        String baseUrl = request.getRequestURI() + "?" + (request.getQueryString() != null ? request.getQueryString() : "");
        if (!contextPath.equals("".trim())) {
            baseUrl = baseUrl.substring(contextPath.length());
        }
        HttpHeaders httpHeaders = generatePaginationHttpHeaders(page, baseUrl);
        
        for (Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String name = entry.getKey();
            for (String value : entry.getValue()) {
                response.addHeader(name, value);
            }
        }
    }
    
    public static HttpHeaders generatePaginationHttpHeaders(Page page, String baseUrl)
            throws URISyntaxException {
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + Long.toString(page.getTotalElements()));
        String link = "";
        if ((page.getNumber() + 1) < page.getTotalPages()) {
            link = "<" + generateUri(baseUrl, page.getNumber() + 1, page.getSize()) + ">; rel=\"next\",";
        }
        // prev link
        if ((page.getNumber()) > 0) {
            link += "<" + generateUri(baseUrl, page.getNumber() - 1, page.getSize()) + ">; rel=\"prev\",";
        }
        // last and first link
        int lastPage = 0;
        if (page.getTotalPages() > 0) {
            lastPage = page.getTotalPages() - 1;
        }
        link += "<" + generateUri(baseUrl, lastPage, page.getSize()) + ">; rel=\"last\",";
        link += "<" + generateUri(baseUrl, 0, page.getSize()) + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }
    
    private static String generateUri(String baseUrl, int page, int size) throws URISyntaxException {
        
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromUriString(baseUrl);
        if (baseUrl.contains("page=")) {
            uriComponentsBuilder = uriComponentsBuilder.replaceQueryParam("page", page);
        } else {
            uriComponentsBuilder = uriComponentsBuilder.queryParam("page", page);
        }
        
        if (baseUrl.contains("size=")) {
            uriComponentsBuilder = uriComponentsBuilder.replaceQueryParam("size", size);
        } else {
            uriComponentsBuilder = uriComponentsBuilder.queryParam("size", size);
        }
        
        return uriComponentsBuilder.toUriString();
    }
    
    
    private PaginationUtil() {
        throw new UnsupportedOperationException();
    }
}
