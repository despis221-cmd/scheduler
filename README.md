# Spring Boot Scheduler

본 프로젝트는 Spring Boot와 JPA를 사용하여 구현한 일정 관리 서버이다.
3 Layer Architecture를 적용하여 일정 관리의 필수 기능인 CRUD를 구현했다.

<br>

## 개발 환경
- **Language**: Java
- **Framework**: Spring Boot
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **IDE**: IntelliJ IDEA

<br>

## 주요 기능

### 1. 일정 생성
- `ScheduleRequestDto`를 통해 제목, 내용, 작성자, 비밀번호를 입력받아 객체를 생성한다.
- 비즈니스 로직 수행 전 `ScheduleCheck` 클래스의 `checkCreate` 메서드를 호출하여 제목(30자), 내용(200자) 등의 길이 제한과 필수 값 누락 여부를 검증한다.
- 검증이 완료된 데이터를 `ScheduleRepository.save()`를 통해 데이터베이스에 저장한다.

### 2. 일정 조회
- **전체 조회**: `Stream`과 `Lambda`를 활용하여 엔티티 리스트를 DTO 리스트로 변환하여 반환한다.
               `creator` 파라미터 유무에 따라 전체 목록 또는 특정 작성자의 목록을 필터링한다.
               수정일 기준 내림차순으로 정렬한다.
- **단건 조회**: `ScheduleRepository`와 `CommentRepository`를 동시에 호출하여 선택한 일정 정보와 해당 일정에 연관된 댓글 목록을 한 번에 조회한다.

### 3. 일정 수정
- `ScheduleUpdateDto`를 사용하여 수정에 필요한 제목과 작성자 데이터만 선별적으로 입력받는다.
- `ScheduleCheck`를 통해 유효성을 검증하고, DB에 저장된 비밀번호와 요청된 비밀번호를 대조한다.
- `Dirty Checking` 기능을 활용하여 별도의 save 호출 없이 객체의 상태 변경만으로 DB 업데이트를 처리한다.

### 4. 일정 삭제
- 삭제 요청 시 비밀번호를 입력받아 `ScheduleCheck.checkPassword`를 통해 검증을 수행한다.
- 비밀번호가 일치하지 않거나 존재하지 않는 ID일 경우 `IllegalArgumentException`을 발생시켜 예외를 처리한다.

### 5. 댓글 생성
- 일정과 1:N 관계를 맺으며, `CommentService`에서 `countByScheduleId` 메서드를 활용해 한 일정당 댓글 개수를 10개로 제한하는 비즈니스 로직을 구현했다.
- 댓글 내용(100자 제한)과 작성자 정보를 검증한 후 저장한다.
- 조회 시 작성일 기준 내림차순으로 정렬된다.

<br>

## 클래스 구조

### Controller Layer
- `ScheduleController.java`: 일정의 생성, 조회, 수정, 삭제 요청을 처리한다. `ScheduleService`를 호출하여 비즈니스 로직을 수행하고 결과 DTO를 반환한다.
- `CommentController.java`: 특정 일정에 대한 댓글 작성 요청을 전담한다. URL 경로에 포함된 `scheduleId`를 파싱하여 CommentService로 전달한다.

### Service Layer
- `ScheduleService.java`: 일정 관련 핵심 비즈니스 로직을 담당한다. 수정 시 비밀번호를 확인하고 `Dirty Checking`을 통해 데이터를 갱신한다. 단건 조회 시 댓글 목록을 함께 반환한다.
- `CommentService.java`: 댓글 생성 로직을 수행한다. 저장 전 해당 일정 존재 여부, 댓글 개수가 10개를 이내를 검사하는 로직을 포함하고 있다.

### Repository Layer
- `ScheduleRepository.java`: `JpaRepository`를 상속받는다. 수정일 기준 내림차순 정렬과 작성자별 필터링 조회 메서드를 정의했다.
- `CommentRepository.java`: `JpaRepository`를 상속받는다. 특정 일정의 댓글 개수를 세는 `countByScheduleId`와 최신순으로 정렬하여 조회하는 메서드를 정의했다.

### Entity
- `Schedule.java`: DB 테이블과 매핑된다. 생성일과 수정일을 자동으로 기록하며, 수정 시 사용하는 메서드를 포함한다.
- `Comment.java`: 댓글 엔티티로 일정 ID를 포함하여 생성되며, 요청 DTO를 통해 객체를 초기화한다.

### Check
- `ScheduleCheck.java`: 제목(30자), 내용(200자) 길이 제한과 필수 값 누락 여부를 검사한다.
- `CommentCheck.java`: 댓글 내용(100자) 길이 제한과 작성자, 비밀번호 누락 여부를 검사한다.

### Exception
- `ExceptionsHandler.java`: `@RestControllerAdvice`를 통해 전역 예외 처리를 수행한다. `IllegalArgumentException` 발생 시 400 상태 코드와 에러 메시지를 반환한다.

<br>

## 실행 방법

1. `ScheduleApplication.java` 파일을 실행하여 Spring Boot 서버를 구동한다.
2. `Postman` 등의 API 테스트 도구를 사용하여 요청을 전송한다.

요청에 필요한 URL, Method, JSON Body 형식은 하단의 API 명세를 참조한다.

<br>

## Scheduler API 명세
<br>

**Base URL**: http://localhost:8080

| 기능 | Method | URL |
| :--- | :--- | :--- |
| 일정 생성 | POST | /schedules |
| 전체 조회 | GET | /schedules |
| 선택 조회 | GET | /schedules/{id} |
| 일정 수정 | PATCH | /schedules/{id} |
| 일정 삭제 | DELETE | /schedules/{id} |
| 댓글 생성 | POST | /schedules/{id}/comments |
<br>

### 1. 일정 생성

- Method: `POST`
- URL: `/schedules`
- Content-Type: `application/json`
- Status Code: `201 Created`

#### Request Body
```json
{
    "title": "일정 제목",
    "content": "일정 내용",
    "creator": "작성자 이름",
    "password": "비밀번호"
}
```

#### Response Body
```json
{
    "id": 1,
    "title": "일정 제목",
    "content": "일정 내용",
    "creator": "작성자 이름",
    "createdAt": "0000-00-00 00:00:00",
    "modifiedAt": "0000-00-00 00:00:00"
}
```
<br>

### 2. 일정 전체 조회

-  Method: `GET`
- URL: `/schedules`
- Query Parameters: `creator`
- Content-Type: `application/json`
- Status Code: `200 OK`

#### Response Body
```json
[
  {
      "id": 1,
      "title": "일정 제목",
      "content": "일정 내용",
      "creator": "작성자 이름",
      "createdAt": "0000-00-00 00:00:00",
      "modifiedAt": "0000-00-00 00:00:00"
  }, 
  {
      "id": 2,
      "title": "일정 제목",
      "content": "일정 내용",
      "creator": "작성자2 이름",
      "createdAt": "0000-00-00 00:00:00",
      "modifiedAt": "0000-00-00 00:00:00"
  }
]
```
<br>

### 3. 일정 선택 조회

-  Method: `GET`
- URL: `/schedules/{id}`
- Path Parameters: `id`
- Content-Type: `application/json`
- Status Code: `200 OK`

#### Response Body
```json
{
    "id": 1,
    "title": "일정 제목",
    "content": "일정 내용",
    "creator": "작성자 이름",
    "createdAt": "0000-00-00 00:00:00",
    "modifiedAt": "0000-00-00 00:00:00",
    "comments": [
        {
            "id": 1,
            "content": "댓글 내용",
            "creator": "댓글 작성자",
            "createdAt": "0000-00-00 00:00:00",
            "modifiedAt": "0000-00-00 00:00:00",
            "scheduleId": 1
        }
    ]
}
```
<br>

### 4. 일정 수정 (비밀번호 일치 시 가능)

-  Method: `PATCH`
- URL: `/schedules/{id}`
- Path Parameters: `id`
- Content-Type: `application/json`
- Status Code: `200 OK`

#### Request Body
```json
{
    "title": "수정할 제목",
    "creator": "수정할 작성자",
    "password": "비밀번호"
}
```

#### Response Body
```json
{
    "title": "수정할 제목",
    "content": "기존 내용",
    "creator": "수정할 작성자",
    "createdAt": "0000-00-00 00:00:00",
    "modifiedAt": "0000-00-00 00:00:00"
}
```
<br>

### 5. 일정 삭제 (비밀번호 일치 시 가능)

-  Method: `DELETE`
- URL: `/schedules/{id}`
- Path Parameters: `id`
- Content-Type: `application/json`
- Status Code: `200 OK`

#### Request Body
```json
{
    "password": "비밀번호"
}
```

#### Response Body
```
할 일 제목이(가) 삭제되었습니다.
```
<br>

### 6. 댓글 생성

-  Method: `POST`
- URL: `/schedules/{id}/comments`
- Path Parameters: `id`
- Content-Type: `application/json`
- Status Code: `201 Created`

#### Request Body
```json
{
    "content": "댓글 내용",
    "creator": "작성자 이름",
    "password": "비밀번호"
}
```

#### Response Body
```json
{
    "id": 1,
    "content": "댓글 내용",
    "creator": "작성자 이름",
    "createdAt": "0000-00-00 00:00:00",
    "modifiedAt": "0000-00-00 00:00:00",
    "scheduleId": 1
}
```
<br><br>

## Scheduler ERD
<img width="488" height="280" alt="Image" src="https://github.com/user-attachments/assets/e88c76de-8f5b-40ef-99f4-0cae00258cb3" />
