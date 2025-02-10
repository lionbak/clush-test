package com.jw.clushtest.calendar.config;

import com.jw.clushtest.calendar.dto.CalendarEventDTO;
import com.jw.clushtest.calendar.entity.CalendarEventEntity;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;


@Mapper
public interface CalendarEventMapper {
    CalendarEventMapper INSTANCE = Mappers.getMapper(CalendarEventMapper.class);

    CalendarEventDTO toDTO(CalendarEventEntity event);

    CalendarEventEntity toEntity(CalendarEventDTO dto);

}
