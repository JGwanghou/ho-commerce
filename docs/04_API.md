# 이커머스 주문 시스템 API 명세서

---

# GET /users/{userId}/amount - 잔액 조회요청

- Description
> 유저 한 명이 자신의 포인트 조회를 한다.

- PathVariable
    - userId: 유저 Id

### Response

```json
[
  {
    "userId": 25,
    "point": 150
  }
]
```

### Error

| code | message       | etc |
|------|---------------|-----|
| 400  | 잘못된 유저 정보입니다. |     |     

---
# PATCH /users/{userId}/charge - 잔액 충전요청

- Description
> 유저 한 명이 포인트 충전을 한다.

- PathVariable
  - userId: 유저 Id
### RequestBody
```json
[
  {
    "point" : 1000
  }
]
```

### Response

```json
[
  {
    "userId": 25,
    "point": 1150
  }
]
```

### Error

| code | message       | etc    |
|------|---------------|--------|
| 400  | 잘못된 유저 정보입니다. | 음수, 0원 |     

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
# POST /orders/{userId} - 주문/결제

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
    "order_price": 10000,
    "products": [
      {
        "id": 101,
        "name": "양배추 샐러드",
        "order_item_price": 2000,
        "quantity": 2
      },
      {
        "id": 102,
        "name": "단호박 샐러드",
        "order_item_price": 2000,
        "quantity": 2
      },
      {
        "id": 103,
        "name": "버섯 샐러드",
        "order_item_price": 1000,
        "quantity": 6
      }
    ]
  }  
]
```

### Response

```json
[
  {
    "id": "1",
    "status": "ok"
  }
]
```

### Error

| code | message       | etc            |
|------|---------------|----------------|
| 409  | 결제 금액이 부족합니다. | 품절된 상품이 존재합니다. |     

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