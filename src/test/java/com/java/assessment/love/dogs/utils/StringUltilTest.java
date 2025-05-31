package com.java.assessment.love.dogs.utils;

import com.java.assessment.love.dogs.util.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class StringUltilTest {

    @Test
    void buildUrlTest() {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("limit", 1);
        params.put("page", 0);
        params.put("order", "desc");
        String url = StringUtils.buildUrl("http://localhost/test", params);
        Assertions.assertEquals("http://localhost/test?limit=1&page=0&order=desc", url);
    }

}
