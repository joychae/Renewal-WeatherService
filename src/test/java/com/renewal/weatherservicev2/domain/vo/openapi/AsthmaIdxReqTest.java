package com.renewal.weatherservicev2.domain.vo.openapi;

import com.renewal.weatherservicev2.domain.vo.openapi.request.living_and_health.AsthmaIdxReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AsthmaIdxReqTest {

    @Test
    @DisplayName("URL 생성 확인")
    void makeUrl() {

        AsthmaIdxReq request = AsthmaIdxReq.builder()
                .admCode("1100000000")
                .date("20210922")
                .build();

        String result = request.makeUrl().toString();
        String expected = "http://apis.data.go.kr/1360000/HealthWthrIdxServiceV2/getAsthmaIdxV2?serviceKey=zhvzvF5vNC7ufu7H%2BQnPJtEQbF2QdNZ0qdvZWLeR%2BnL0UwxwnCgrkmxKB9oqCXVSJp95YTliRHwzxvGdrvjetg%3D%3D&areaNo=1100000000&time=2021092206&dataType=json";
        assertEquals(expected, result);
    }
}