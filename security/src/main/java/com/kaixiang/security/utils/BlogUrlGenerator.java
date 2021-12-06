package com.kaixiang.security.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @Author kaixiang.tao
 * @Date 2021/12/5
 */
@Component
public class BlogUrlGenerator {

    public String generate(String url, MultiValueMap<String, String> params) {
        return UriComponentsBuilder.fromUriString(url).queryParams(params).build().toString();
    }
}
