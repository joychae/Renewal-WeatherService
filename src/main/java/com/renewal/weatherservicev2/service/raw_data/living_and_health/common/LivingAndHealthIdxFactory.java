package com.renewal.weatherservicev2.service.raw_data.living_and_health.common;

import com.renewal.weatherservicev2.domain.entity.common.BigRegion;
import com.renewal.weatherservicev2.domain.entity.external.abstr.LivingAndHealthIdxType;
import com.renewal.weatherservicev2.exception.NonServicePeriodException;
import com.renewal.weatherservicev2.service.raw_data.living_and_health.specific.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LivingAndHealthIdxFactory {

    private final AsthmaIdxService asthmaIdxService;
    private final ColdIdxService coldIdxService;
    private final FoodPoisoningIdxService foodPoisoningIdxService;
    private final OakPollenIdxService oakPollenIdxService;
    private final PinePollenIdxService pinePollenIdxService;
    private final StrokeIdxService strokeIdxService;
    private final UvIdxService uvIdxService;
    private final WeedsPollenRiskIdxService weedsPollenRiskIdxService;

    public void callDataFromOpenApiAndSave(LivingAndHealthIdxType type, String date, BigRegion bigRegion) {
        try {
            switch (type) {
                case ASTHMA:
                    asthmaIdxService.callAndSaveData(date, bigRegion);
                    break;
                case COLD:
                    coldIdxService.callAndSaveData(date, bigRegion);
                    break;
                case FOOD_POISONING:
                    foodPoisoningIdxService.callAndSaveData(date, bigRegion);
                    break;
                case OAK_POLLEN_RISK:
                    oakPollenIdxService.callAndSaveData(date, bigRegion);
                    break;
                case PINE_POLLEN_RISK:
                    pinePollenIdxService.callAndSaveData(date, bigRegion);
                    break;
                case STROKE:
                    strokeIdxService.callAndSaveData(date, bigRegion);
                    break;
                case UV:
                    uvIdxService.callAndSaveData(date, bigRegion);
                    break;
                case WEEDS_POLLEN_RISK:
                    weedsPollenRiskIdxService.callAndSaveData(date, bigRegion);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (NonServicePeriodException e) {
            log.error(e.getMessage());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
