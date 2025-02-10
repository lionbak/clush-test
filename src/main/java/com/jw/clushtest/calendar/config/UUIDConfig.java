package com.jw.clushtest.calendar.config;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class UUIDConfig {

    //UUID 생성기 (하위 비트를 제일 앞으로 정렬)
    public static byte[] genrateUUID() {
        UUID uuidV1 = Generators.timeBasedGenerator().generate();
        String[] uuidArr = uuidV1.toString().split("-");
        return (uuidArr[2]+"-"+uuidArr[1]+"-"+uuidArr[0]+"-"+uuidArr[3]+"-"+uuidArr[4]).getBytes();
    }
}
