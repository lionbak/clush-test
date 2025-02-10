package com.jw.clushtest.calendar.config;

import com.fasterxml.uuid.Generators;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDConfig {

    public static UUID generateUUID() {
        return Generators.timeBasedReorderedGenerator().generate();
    }

    public static byte[] toBytes(UUID uuid) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byteBuffer.putLong(uuid.getMostSignificantBits());
        byteBuffer.putLong(uuid.getLeastSignificantBits());
        return byteBuffer.array();
    }

    public static UUID toUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long mostSigBits = byteBuffer.getLong();
        long leastSigBits = byteBuffer.getLong();
        return new UUID(mostSigBits, leastSigBits);
    }

    public static void main(String[] args) {
       for (int i = 0; i < 10; i++) {
        UUID uuid1 = Generators.timeBasedReorderedGenerator().generate();
        System.out.println("UUID 생성: " + uuid1);
       }
//        byte[] uuidBytes = toBytes(uuid1);
//        UUID uuid2 = toUUID(uuidBytes);
//
//        System.out.println("UUID byte[] 변환: " + Arrays.toString(uuidBytes));
//        System.out.println("byte[] UUID 변환: " + uuid2);
    }
}
