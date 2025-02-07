package com.jw.clushtest.calendar.config;

import com.jw.clushtest.calendar.dto.CalendarEventDTO;
import com.jw.clushtest.calendar.entity.CalendarEventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CalendarEventMapper {
    CalendarEventMapper INSTANCE = Mappers.getMapper(CalendarEventMapper.class);

    @Mapping(target = "id", expression = "java(new String(event.getId(), java.nio.charset.StandardCharsets.UTF_8))")
    CalendarEventDTO toDTO(CalendarEventEntity event);

    @Mapping(target = "id", expression = "java(dto.getId().getBytes(java.nio.charset.StandardCharsets.UTF_8))")
    CalendarEventEntity toEntity(CalendarEventDTO dto);
}
