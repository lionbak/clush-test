package com.jw.clushtest.calendar.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = true)
public class UUIDConverter implements AttributeConverter<UUID, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(UUID attribute) {
        return attribute == null ? null : UUIDConfig.toBytes(attribute);
    }

    @Override
    public UUID convertToEntityAttribute(byte[] dbData) {
        return dbData == null ? null : UUIDConfig.toUUID(dbData);
    }
}
