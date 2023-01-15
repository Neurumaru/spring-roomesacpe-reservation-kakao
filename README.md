# Spring Basic - 예약 관리

## 상황 설명

- 기존에는 로컬 환경에서 콘솔 애플리케이션을 이용하여 예약 정보를 관리해왔다.
- 콘솔 애플리케이션을 웹 애플리케이션으로 전환하여 집에서도 웹을 통해 예약 관리를 할 수 있도록 해야한다.

## 요구사항

- [X] 콘솔 애플리케이션에 데이터베이스를 적용한다.
  - [X] 직접 커넥션을 만들어서 데이터베이스에 접근한다.
    - [X] 예약 생성
    - [X] 예약 조회
    - [X] 예약 삭제
- [X] 웹 애플리케이션에 데이터베이스를 적용한다.
  -  [X] 스프링이 제공하는 기능을 활용하여 데이터베이스에 접근한다.
    - [X] 예약 생성
    - [X] 예약 조회
    - [X] 예약 삭제
- [ ] **테마 관리 기능 추가**
  - [X] **모든 테마의 시간표는 동일함**
  - [X] **예약과 관계있는 스케줄 혹은 테마는 수정 및 삭제가 불가능하다.**
- [ ] **요구사항에 없는 내용이 있다면 직접 요구사항을 추가해서 애플리케이션을 완성**
  - [ ] **실제 애플리케이션이라고 생각했을 때 발생할 수 있는 예외 상황을 고려하고 처리한다.**


- [X] Controller
  - [X] **Console**
    - [X] 예약 하기
    - [X] 예약 조회
    - [X] 예약 취소
    - [X] 테마 생성
    - [ ] 테마 조회
    - [X] 테마 목록 조회
    - [X] 테마 삭제
  - [X] **API**
    - [X] 예약 하기
      ```
      POST /reservations HTTP/1.1
      content-type: application/json; charset=UTF-8
      host: localhost:8080
      
      {
        "date" : "2022-08-11",
        "time" : "12:34:56",
        "name" : "사람 이름"
      }
      ```
      ```
      HTTP/1.1 201 Created
      Location: /reservations/1
      ```
    - [X] 예약 조회
      ```
      GET /reservations/1 HTTP/1.1
      ```
      ```
      HTTP/1.1 200 
      Content-Type: application/json
      
      {
        "id": 1,
        "date": "2022-08-11",
        "time": "13:00",
        "name": "name",
        "themeName": "워너고홈",영
        "themeDesc": "병맛 어드벤처 회사 코믹물",
        "themePrice": 29000
      }
      ```
    - [X] 예약 취소
      ```
      DELETE /reservations/1 HTTP/1.1
      ```
      ```
      HTTP/1.1 204 
      ```
    - [X] **테마 생성**
      ```
      POST /themes HTTP/1.1
      content-type: application/json; charset=UTF-8
      
      {
          "name": "테마이름",
          "desc": "테마설명",
          "price": 22000
      }
      
      ```
      ```
      HTTP/1.1 201 Created
      Location: /themes/1
      ```
    - [X] 테마 조회
      ```
      GET /themes/1 HTTP/1.1
      ```
      ```
      HTTP/1.1 200 
      Content-Type: application/json
      
      {
          "id": 1,
          "name": "테마이름",
          "desc": "테마설명",
          "price": 22000
      }
      ```
    - [X] **테마 목록 조회**
      ```
      GET /themes HTTP/1.1
      ```
      ```
      HTTP/1.1 200 
      Content-Type: application/json
      
      [
          {
              "id": 1,
              "name": "테마이름",
              "desc": "테마설명",
              "price": 22000
          }
      ]
      ```
    - [X] **테마 삭제**
      ```
      DELETE /themes/1 HTTP/1.1
      ```
      ```
      HTTP/1.1 204 
      ```
    - [ ] 예외처리
      - [X] 예약 생성) content-type이 application/json이 아닌 경우 값을 받지 않는다.
      - [X] 예약 생성) 예약 생성 시 날짜와 시간이 똑같은 예약이 이미 있는 경우 예약을 생성할 수 없다.
      - [X] 예약 생성) 값의 포맷이 맞지 않을 경우 생성 불가
      - [X] 예약 생성) 값이 포함되지 않았을 경우 예약 생설 불가
      - [X] 예약 생성) 존재하지 않는 테마를 지정할 경우 생성 불가
      - [X] 예약 조회) ID가 잘못된 경우 (float, string)
      - [X] 예약 삭제) ID가 잘못된 경우 (float, string)
      - [X] 테마 생성) content-type이 application/json이 아닌 경우 값을 받지 않는다.
      - [X] 테마 생성) 같은 이름의 예약은 생성 불가
      - [X] 테마 생성) 값의 포맷이 맞지 않을 경우 생성 불가
      - [X] 테마 생성) 값이 포함되지 않았을 경우 생설 불가
      - [ ] 테마 조회) ID가 잘못된 경우 (float, string)
      - [ ] 테마 삭제) ID가 잘못된 경우 (float, string)
      - [ ] 테마 삭제) 예약과 관계있는 테마 삭제 불가
- [X] **Service**
  - [X] **기능 목록**
    - [X] 예약 생성
    - [X] 예약 조회
    - [X] 예약 삭제
    - [X] **테마 생성**
    - [X] **테마 조회**
    - [X] **테마 목록 조회**
    - [X] **테마 삭제**
  - [X] **예외 처리**
    - [X] 예약 생성) 예약 생성 시 날짜와 시간이 똑같은 예약이 이미 있는 경우 예약을 생성할 수 없다.
    - [X] 예약 생성) 값이 포함되지 않았을 경우 예약 생설 불가
    - [X] 예약 생성) 존재하지 않는 테마를 지정할 경우 생성 불가
    - [X] 예약 조회) ID가 없는 경우 조회 불가
    - [X] 예약 삭제) ID가 없는 경우 삭제 불가
    - [X] 테마 생성) 같은 이름의 예약은 생성 불가
    - [X] 테마 생성) 값이 포함되지 않았을 경우 생성 불가
    - [X] 테마 조회) ID가 없는 경우 조회 불가
    - [X] 테마 삭제) ID가 없는 경우 삭제 불가
    - [X] 테마 삭제) 예약과 관계있는 테마 삭제 불가
- [X] **DAO**
  - [X] Connection
    - [X] 예약 생성
    - [X] 예약 조회
    - [X] 예약 삭제
    - [X] **예약 존재 확인**
    - [X] **예약 아이디 존재 확인**
    - [X] **예약에서 테마 아이디 존재 확인**
    - [X] **테마 생성**
    - [X] **테마 목록 조회**
    - [X] **테마 조회**
    - [X] **테마 삭제**
    - [X] **테마 존재 확인**
    - [X] **테마 아이디 존재 확인**
  - [X] JDBC
    - [X] 예약 생성
    - [X] 예약 조회
    - [X] 예약 삭제
    - [X] **예약 존재 확인**
    - [X] **예약 아이디 존재 확인**
    - [X] **예약에서 테마 아이디 존재 확인**
    - [X] **테마 생성**
    - [X] **테마 목록 조회**
    - [X] **테마 조회**
    - [X] **테마 삭제**
    - [X] **테마 존재 확인**
    - [X] **테마 아이디 존재 확인**
  - [X] 예외처리
    - [X] 결과가 없는 경우 null 반환

## 프로그래밍 요구사항

- 기존 콘솔 애플리케이션은 그대로 잘 동작해야한다.
- 콘솔 애플리케이션과 웹 애플리케이션의 중복 코드는 허용한다. (다음 단계에서 리팩터링 예정)
- 콘솔 애플리케이션과 웹 애플리케이션에서 각각 데이터베이스에 접근하는 로직을 구현한다.
- 콘솔 애플리케이션에서 데이터베이스에 접근 시 JdbcTemplate를 사용하지 않는다. 직접 Connection을 생성하여 데이터베이스에 접근한다.
- 웹 애플리케이션에서 데이터베이스 접근 시 JdbcTemplate를 사용한다.
- 콘솔 애플리케이션과 웹 애플리케이션의 중복 코드 제거해본다.
- **테마를 관리하는 테이블을 추가한다.**
- **콘솔 애플리케이션과 웹 애플리케이션의 로직의 중복을 제거한다.**
- **디비 접근을 담당하는 객체를 별도로 만들어 사용한다.**
- **비즈니스 로직을 담당하는 객체를 별도로 만들어 사용한다.**
