package com.renewal.weatherservicev2.repository.weather.daily;

import com.renewal.weatherservicev2.domain.entity.external.weather.daily.DailyWeatherDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyWeatherDescriptionRepository extends JpaRepository<DailyWeatherDescription, Long> {
}
