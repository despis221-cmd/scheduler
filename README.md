## Scheduler

### 1. 일정 생성

- Method: `POST`
- URL: `/api/schedules`
- Content-Type: `application/json`
- Status Code: `201 Created`

#### Request Body
```json
{
    "title": "할 일 제목",
    "content": "할 일 내용",
    "creator": "작성자 이름",
    "password": "비밀번호"
}
```

#### Response Body
```json
{
    "id": 1,
    "title": "할 일 제목",
    "content": "할 일 내용",
    "creator": "작성자 이름",
    "createdAt": "0000-00-00 00:00:00",
    "modifiedAt": "0000-00-00 00:00:00"
}
```
<br>

### 2. 일정 전체 조회

-  Method: `GET`
- URL: `/api/schedules`
- Query Parameters: `creator`
- Content-Type: `application/json`
- Status Code: `200 OK`

#### Response Body
```json
[
  {
      "id": 1,
      "title": "할 일 제목",
      "content": "할 일 내용",
      "creator": "작성자 이름",
      "createdAt": "0000-00-00 00:00:00",
      "modifiedAt": "0000-00-00 00:00:00"
  }, 
  {
      "id": 1,
      "title": "할 일 제목",
      "content": "할 일 내용",
      "creator": "작성자2 이름",
      "createdAt": "0000-00-00 00:00:00",
      "modifiedAt": "0000-00-00 00:00:00"
  }
]
```
<br>

### 3. 일정 선택 조회

-  Method: `GET`
- URL: `/api/schedules/{id}`
- Path Parameters: `id`
- Content-Type: `application/json`
- Status Code: `200 OK`

#### Response Body
```json
{
    "id": 1,
    "title": "할 일 제목",
    "content": "할 일 내용",
    "creator": "작성자 이름",
    "createdAt": "0000-00-00 00:00:00",
    "modifiedAt": "0000-00-00 00:00:00"
}
```
<br>

### 4. 일정 수정 (비밀번호 일치 시 가능)

-  Method: `PATCH`
- URL: `/api/schedules/{id}`
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
- URL: `/api/schedules/{id}`
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
```json
{
    "message": "일정이 삭제되었습니다."
}
```
<br><br>

## Scheduler ERD
<img width="488" height="280" alt="Image" src="https://github.com/user-attachments/assets/e88c76de-8f5b-40ef-99f4-0cae00258cb3" />
