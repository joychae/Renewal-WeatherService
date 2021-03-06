package com.renewal.weatherservicev2.service.raw_data.living_and_health.specific;

import com.renewal.weatherservicev2.domain.entity.common.BigRegion;
import com.renewal.weatherservicev2.domain.entity.external.living_and_health.WeedsPollenRiskIdx;
import com.renewal.weatherservicev2.domain.vo.openapi.abstr.OpenApiRequestInterface;
import com.renewal.weatherservicev2.domain.vo.openapi.request.living_and_health.WeedsPollenRiskIdxReq;
import com.renewal.weatherservicev2.domain.vo.openapi.response.living_and_health.LivingAndHealthRes;
import com.renewal.weatherservicev2.exception.NonServicePeriodException;
import com.renewal.weatherservicev2.repository.living_and_health.WeedsPollenRiskIdxRepository;
import com.renewal.weatherservicev2.service.connection.LivingAndHealthConnectionService;
import com.renewal.weatherservicev2.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeedsPollenRiskIdxService {

    private final WeedsPollenRiskIdxRepository weedsPollenRiskIdxRepository;
    private final DateTimeUtil dateTimeUtil;
    private final LivingAndHealthConnectionService connectionService;

    public void callAndSaveData(String date, BigRegion bigRegion) throws NonServicePeriodException {
        WeedsPollenRiskIdx weedsPollenRiskIdx = callData(date, bigRegion.getAdmCode());
        weedsPollenRiskIdx.joinRegion(bigRegion);
        saveData(weedsPollenRiskIdx);
    }

    public WeedsPollenRiskIdx callData(String date, String admCode) throws NonServicePeriodException {

        if(dateTimeUtil.getMonthYYYYMMDD(date) <= 7 || dateTimeUtil.getMonthYYYYMMDD(date) >= 11) {
            throw new NonServicePeriodException("꽃가루농도위험지수(잡초류) 자료제공기간인 8-10월이 아닙니다.");
        }

        OpenApiRequestInterface request = WeedsPollenRiskIdxReq.builder()
                .admCode(admCode)
                .date(date)
                .build();

        LivingAndHealthRes response = connectionService.connectAndGetParsedResponse(request);
        WeedsPollenRiskIdx data = new WeedsPollenRiskIdx();
        return data.from(response);
    }

    private void saveData(WeedsPollenRiskIdx weedsPollenRiskIdx) {
        weedsPollenRiskIdxRepository.save(weedsPollenRiskIdx);
    }

}
