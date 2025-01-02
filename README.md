# 🛒 **E-Commerce Backend**


## 📌 **주요 기능**

- **잔액 충전/조회 API**
  - `멱등성`을 고려한 잔액 충전 (with.Redis)
- **주문 / 결제 API**
  - `이벤트 기반` 사용자 주문처리
  - Race Condition을 예방한 재고 차감 동시성 처리
  - 포인트 차감
- **캐싱**
  - 인기 상품 등 자주 조회되는 결과를 캐싱하여 응답속도 개선
- **모니터링**
  - Prometheus와 Grafana를 활용한 시스템 지표 시각화.


## 📝 설계 문서
<details>
  <summary>마일스톤</summary>

  ## [3주차] 프로젝트 설계
  |     | 작업 | 예상 시간 (시간) |
  |-----|------|----------------|
  | 1   | **프로젝트 설계** | **40** |
  | 1.1 | 주차 별 마일스톤 설정 | 2 |
  | 1.2 | 요구사항 분석 | 8 |
  | 1.3 | 시퀀스 다이어그램 작성 | 8 |
  | 1.4 | ERD 설계 | 8 |
  | 1.5 | API 명세서 작성 | 8 |
  | 1.6 | Mock API 구현 | 8 |
  
  ## [4주차] 프로젝트 API 구현(With TDD)
  
  |  | 작업                   | 예상 시간 (시간) |
  |--|----------------------|------------|
  | 2 | **TDD로 프로젝트 API 구현** | **40**     |
  | 2.1 | [주요] 잔액 충전 / 조회 API  | -          |
  | 2.1.1 | 잔액 충전 API 개발         | 2          |
  | 2.1.2 | 잔액 조회 API 개발         | 2          |
  | 2.2 | [기본] 상품 조회 API       | -          |
  | 2.2.1 | 상품 정보조회 API 개발       | 8          |
  | 2.3 | [주요] 주문 / 결제 API     | -          |
  | 2.3.1 | 주문 API 및 포인트 결제 개발   | 16         |
  | 2.4 | [기본] 상품 조회 API       | -          |
  | 2.4.1 | 판매량 최다 상품 조회         | 3          |
  
  ## [5주차] 고도화 및 코드 리팩토링
  
  |    | 작업 | 예상 시간 (시간) |
  |----|------|------------|
  | 3  | **고도화 및 코드 리팩토링** | **40**     |
  | 3.1 | 고도화 | 20          |
  | 3.2 | 동시성 이슈 검토 | 12         |
  | 3.3 | 코드 리팩토링 | 8          |
</details>

<details>
  <summary>ERD</summary>

  ![img_1.png](docs/images/new-erd.png)
</details>

<details>
  <summary>시퀀스 다이어그램</summary>

  ### 잔액 조회
  ```mermaid
  sequenceDiagram 
    autonumber
    
    actor Client
    participant UserService
    participant UserDB
    Client->>UserService: GET /api/v1/users/{userId}
    UserService->>UserDB: 사용자 조회
    
    opt 유저 존재하지 않는 경우
        UserDB-->>UserService: UserNotFoundException
        UserService-->>Client: 404 NOT FOUND
    end
    UserDB-->>UserService: 
    UserService-->>Client: 유저정보 반환
  ```

  ### 상품 조회
  ```mermaid
  sequenceDiagram 
    autonumber
    
    actor Client
    participant ProductFacade
    participant ProductService
    participant StockService
    Client->>ProductFacade: GET /api/v1/users/{userId}
    
    
  ```

  ### 잔액 충전
  ```mermaid
  sequenceDiagram
    autonumber
    actor Client
    participant UserService
    participant UserDB
    Client->>UserService: POST /api/v1/users/{userId}/charge
    UserService->>UserDB: 사용자 조회
    
    opt 유저 존재하지 않는 경우
        UserDB-->>UserService: UserNotFoundException
        UserService-->>Client: 404 NOT FOUND
    end
    opt 충전금액: 0원 이하, 0원, 1,000,000원 이상
      UserDB-->>UserService: InvalidChargeAmountException
      UserService-->>Client: 400 BAD REQUEST
    end
    UserDB-->>UserService: 
    UserService-->>Client: 유저정보 반환(충전 후 잔액) 
  ```

  ### 주문 동작흐름
  ```mermaid
  sequenceDiagram
      autonumber
      actor Client
      participant OrderAPI
      participant StockService
      participant UserService
      participant PaymentService
      participant Redisson
      participant DB
      participant MessageQueue
      participant NotificationService
      Client->>OrderAPI: POST /api/orders
      Note over OrderAPI: 트랜잭션 시작
      OrderAPI->>DB: 주문 정보 저장(status = '대기')
      OrderAPI->>DB: 주문 상세정보 저장
      Note over StockService: 트랜잭션 시작
      loop
          StockService->>Redisson: 상품번호 기준 락 흭득
          StockService->>DB: 재고 조회
          alt 재고개수 = 0
              DB-->>StockService: 409 CONFLICT
              StockService-->>Client: 409 CONFLICT
          else 재고개수 > 0
              Redisson->>DB: 재고차감
          end
      end
      Note over StockService: 트랜잭션 종료
      UserService->>PaymentService: 보유 포인트 조회
      alt 보유포인트 < 주문금액
          PaymentService-->>Client: 포인트부족 예외
      else 보유포인트 >= 주문금액
          UserService->>PaymentService: 보유 포인트 차감
          PaymentService-->>DB: 차감 내역 저장
      end
      Note over OrderAPI: 트랜잭션 종료
      OrderAPI-->>Client: order ID : 12345
      Note over OrderAPI,NotificationService: 외부에 데이터 전송
      OrderAPI->>MessageQueue: 주문내역 전송
      MessageQueue-->>NotificationService: 주문내역
      par Notification Channels
          NotificationService->>NotificationService: 알림톡 전송
      end
  ```

</details>

<details>
  <summary>API 명세서</summary>

# 이커머스 주문 시스템 API 명세서

---

# GET /api/v1/users/{userId} - 잔액 조회요청

- Description
> 유저 한 명이 자신의 포인트 조회를 한다.

- PathVariable
  - userId: 유저 Id

### Response

```json
[
  {
    "userId": 25,
    "name": "김테스트",
    "hpNo": "010-1234-4112",
    "amount": "100,000"
  }
]
```

### Error

| code | message        | etc |
|------|----------------|-----|
| 404  | 존재하지 않는 유저입니다. |     |     

---
# POST /api/v1/users/{userId}/charge - 잔액 충전요청

- Description
> 유저 한 명이 포인트 충전을 한다.

- Header
  - Idempotency-Key: 멱등성 키
- PathVariable
  - userId: 유저 Id
- UserPointChargeRequest
  - chargeAmount: 충전 요청 금액
### RequestBody
```json
[
  {
    "chargeAmount" : 50000
  }
]
```

### Response

```json
[
  {
    "userId": 25,
    "name": "김테스트",
    "hpNo": "010-1234-4112",
    "amount": "150,000"
  }
]
```

### Error

| code | message                   | etc    |
|------|---------------------------|--------|
| 400  | 최소 충전 금액은 1,000원 입니다.     |  |
| 400  | 최대 충전 금액은 1,000,000원 입니다. |  |
| 400  | 멱등성 키가 누락되었습니다.           |  |
| 404  | 존재하지 않는 유저입니다.            |  |
| 409  | 이미 처리중인 요청입니다.            |  |

---

---
# GET /products/{productId} - 상품 상세조회 요청

- Description
> 상품 목록 중 하나의 상품를 조회한다.

- PathVariable
  - productId: 상품 Id

### Response

```json
[
  {
    "id": 25,
    "name": "빽다방 아메리카노",
    "price": 2500,
    "stock": 9
  }
]
```

### Error

| code | message       | etc    |
|------|---------------|--------|
| 400  | 잘못된 상품 번호입니다. |  |     

---

---
# POST /api/v1/orders - 주문/결제

- Description
> 한 명의 사용자가 여러 개 또는 하나의 상품을 주문한다.

- PathVariable
  - userId: 유저 Id
### RequestBody
```json
[
  {
    "id": 1,
    "user_id": 25,
    "products": [
      {
        "id": 101,
        "name": "양배추 샐러드",
        "quantity": 2
      },
      {
        "id": 102,
        "name": "단호박 샐러드",
        "quantity": 2
      },
      {
        "id": 103,
        "name": "버섯 샐러드",
        "quantity": 1
      }
    ],
    "paymentAmount": 10000
  }  
]
```

### Response

```json
[
  {
    "orderId": "1"
  }
]
```

### Error

| code | message           | etc            |
|------|-------------------|----------------|
| 404  | 해당 상품이 존재하지 않습니다. |  |
| 404  | 해당 유저가 존재하지 않습니다. |  |  
| 409  | 결제 금액이 부족합니다.     |  |
| 409  | 품절된 상품이 존재합니다.    |  |

---

---
# GET /products/popular - 인기상품 조회

- Description
> 최근 3일간 최다 판매량 상품 5개를 조회한다.

### Response

```json
[
  {
    "id": 101,
    "name": "양배추 샐러드",
    "sale_count": 20
  },
  {
    "id": 102,
    "name": "단호박 샐러드",
    "sale_count": 18
  },
  {
    "id": 103,
    "name": "버섯 샐러드",
    "sale_count": 10
  },
  {
    "id": 104,
    "name": "감자 샐러드",
    "sale_count": 8
  },
  {
    "id": 105,
    "name": "오이 샐러드",
    "sale_count": 6
  }
]
```

---
</details>

## 🚀 기술 스택
 - **Backend: Spring Boot, JPA, JUnit, MySQL, Redisson(Redis)**
 - **CI/CD: Github actions, Docker**

## ⚙️ **Architecture**
![img_1.png](docs/images/architecture.png)


### 6. [동시성 보고서](https://github.com/JGwanghou/hhplus-ecommerce/blob/main/docs/06_Concurrently.md)

### 7. [Index 개선 시도](https://kh-well.tistory.com/76)

