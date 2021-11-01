package com.renewal.weatherservicev2.domain.entity.external.weather.daily;

import com.renewal.weatherservicev2.domain.entity.external.abstr.DailyWeather;
import com.renewal.weatherservicev2.domain.vo.openapi.response.weather.common.DailyWeatherJsonRes;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DailyTempMax implements DailyWeather {

    @Id
    @Column(name = "daily_temp_max_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day1;
    private String day2;
    private String day3;
    private String day4;
    private String day5;
    private String day6;
    private String day7;
}