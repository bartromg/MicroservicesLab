package com.pyshankov.microservices.statisticservice.config;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Created by valeriyartemenko on 06.05.18.
 */
public class DateToLocalDateTime implements Converter<Date, LocalDateTime> {

    @Override
    public LocalDateTime convert(Date source) {
        return source == null ? null : LocalDateTime.ofInstant(source.toInstant(), ZoneOffset.UTC);
    }
}