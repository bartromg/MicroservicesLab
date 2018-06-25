package com.pyshankov.microservices.statisticservice.config;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Created by valeriyartemenko on 06.05.18.
 */
public class LocalDateTimeToDate implements Converter<LocalDateTime, Date> {

    @Override
    public Date convert(LocalDateTime source) {
        return source == null ? null : Date.from(source.toInstant(ZoneOffset.UTC));
    }
}
