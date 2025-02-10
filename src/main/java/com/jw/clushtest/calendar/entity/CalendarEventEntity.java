package com.jw.clushtest.calendar.entity;

import com.jw.clushtest.calendar.config.UUIDConfig;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CalendarEventEntity {

    @Id
    @Builder.Default
    private byte[] id = UUIDConfig.genrateUUID();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

}
