package com.renewal.weatherservicev2.service.raw_data.living_and_health.specific;

import com.renewal.weatherservicev2.domain.entity.common.BigRegion;
import com.renewal.weatherservicev2.domain.entity.external.living_and_health.OakPollenRiskIdx;
import com.renewal.weatherservicev2.domain.vo.openapi.abstr.OpenApiRequestInterface;
import com.renewal.weatherservicev2.domain.vo.openapi.request.living_and_health.OakPollenRiskIdxReq;
import com.renewal.weatherservicev2.domain.vo.openapi.response.living_and_health.LivingAndHealthRes;
import com.renewal.weatherservicev2.exception.NonServicePeriodException;
import com.renewal.weatherservicev2.repository.living_and_health.OakPollenRiskIdxRepository;
import com.renewal.weatherservicev2.service.connection.LivingAndHealthConnectionService;
import com.renewal.weatherservicev2.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OakPollenIdxService {

    private final OakPollenRiskIdxRepository oakPollenRiskIdxRepository;
    private final DateTimeUtil dateTimeUtil;
    private final LivingAndHealthConnectionService connectionService;

    public void callAndSaveData(String date, BigRegion bigRegion) throws NonServicePeriodException {
        OakPollenRiskIdx oakPollenRiskIdx = callData(date, bigRegion.getAdmCode());
        oakPollenRiskIdx.joinRegion(bigRegion);
        saveData(oakPollenRiskIdx);
    }

    private OakPollenRiskIdx callData(String date, String admCode) throws NonServicePeriodException {

        if(dateTimeUtil.getMonthYYYYMMDD(date) <= 3 || dateTimeUtil.getMonthYYYYMMDD(date) >= 7) {
            throw new NonServicePeriodException("꽃가루농도위험지수(참나무) 자료제공기간인 4-6월이 아닙니다.");
        }

        OpenApiRequestInterface request = OakPollenRiskIdxReq.builder()
                .admCode(admCode)
                .date(date)
                .build();

        LivingAndHealthRes response = connectionService.connectAndGetParsedResponse(request);
        OakPollenRiskIdx data = new OakPollenRiskIdx();
        return data.from(response);
    }

    private void saveData(OakPollenRiskIdx oakPollenRiskIdx) {
        oakPollenRiskIdxRepository.save(oakPollenRiskIdx);
    }

}
