package com.jw.clushtest.calendar.entity;

import com.jw.clushtest.calendar.config.UUIDConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "event")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CalendarEventEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @Convert(converter = UUIDConverter.class)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;


}
