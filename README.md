## APP 설명
회사나 학교, 동아리 등에서 사용할 수 있는 캘린더 서비스를 구상하였습니다.

## 소스 빌드 및 실행 방법
git clone
```
git clone https://github.com/lionbak/clush-test.git
```
MySQL에 Schema 추가 (clush_test_calendar)

application.yml 수정
```
spring:
  application:
    name: "clush-test"

  datasource:
    url: jdbc:mysql://localhost:4306/clush_test_calendar?characterEncoding=UTF-8&serverTimezone=UTC
    username: root
    password: 패스워드 기입
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update //create시 테이블 자동 생성
    database: mysql
    database-platform: org.hibernate.dialect.MySQLDialect
    generate-ddl: false
    show-sql: true
    properties:
      hibernate.hibernate.format_sql: true

server:
  port: 8093
```
gradle build
```
./gradlew build
```
실행
```
./gradlew bootRun
IDE에서 ClushTestApplication RUN
```


## 주력으로 사용한 라이브러리
- mapstruct : Entity ↔ DTO 변환을 자동화하기 위해 사용하였습니다. 수동으로 변환 메서드를 작성하는 불필요한 코드를 줄이고, 유지보수를 용이하게 하기 위해 적용하였습니다.
- lombok : 생성자, 메서드에 대한 반복적 코드를 간결하게 하고, 유지보수를 용이하게 하기 위해 적용하였습니다.
- java-uuid-generator : Timestemp기반의 UUIDv6를 사용하기 위해 사용하였습니다. 무작위의 `UUID.randomUUID` 방식보다 정렬이 가능하여 순차적인 UUID를 적용합니다.
- zxing : QR코드 공유 API에서 QR코드 이미지를 생성하기 위해 적용하였습니다.

## API명세
## 캘린더
Base URL
```
http://localhost:8093/api/calendar
```
| 설명 | 메서드 | Endpoint |
| --- | --- | --- |
| 모든 이벤트 조회 | GET | /api/calendar | 
| 단일 이벤트 조회 | GET | /api/calendar/{id} |
| 이벤트 추가 | POST | /api/calendar |
| 이벤트 수정 | PUT | /api/calendar/{id} |
| 이벤트 삭제 | DELETE | /api/calendar/{id} |

## QR코드
| 설명 | 메서드 | Endpoint |
| --- | --- | --- |
| QR코드 생성 | GET | /api/share/qr |
