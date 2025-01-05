create table orders (is_delete BOOLEAN DEFAULT false, total_price decimal(38,2), create_at datetime(6), id bigint not null auto_increment, update_at datetime(6), user_id bigint, status enum ('CANCELED','FAIL','PAID','PAY_FAILED','READY'), primary key (id)) engine=InnoDB;
create table orders_item (is_delete BOOLEAN DEFAULT false, order_price decimal(38,2), count bigint, create_at datetime(6), id bigint not null auto_increment, order_id bigint, product_id bigint, update_at datetime(6), primary key (id)) engine=InnoDB;
create table payment (total_price decimal(38,2), id bigint not null auto_increment, order_id bigint, user_id bigint, status varchar(255), pay_method enum ('CARD','CASH','QR_PAY','TRANSFER'), primary key (id)) engine=InnoDB;
create table product (is_delete BOOLEAN DEFAULT false, price decimal(38,2), create_at datetime(6), id bigint not null auto_increment, update_at datetime(6), name varchar(255), primary key (id)) engine=InnoDB;
create table stock (is_delete BOOLEAN DEFAULT false, create_at datetime(6), id bigint not null auto_increment, product_id bigint, quantity bigint, update_at datetime(6), primary key (id)) engine=InnoDB;
create table users (is_delete BOOLEAN DEFAULT false, point decimal(10,1) not null, create_at datetime(6), id bigint not null auto_increment, update_at datetime(6), hp_no varchar(255), name varchar(255), primary key (id)) engine=InnoDB;

SET SESSION cte_max_recursion_depth = 1500000;

# 상품 200만개 등록
INSERT INTO product (name, price, is_delete, create_at, update_at)
WITH RECURSIVE cte (n) AS
(
   SELECT 1
   UNION ALL
   SELECT n + 1 FROM cte WHERE n < 1500000 -- 생성하고 싶은 더미 데이터의 개수
)
SELECT
    CONCAT('Product-', LPAD(n, 7, '0')) AS name,
    2000 AS price,
    FALSE as is_delete,
    TIMESTAMP(DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 3650) DAY) + INTERVAL FLOOR(RAND() * 86400) SECOND) AS create_at,
    TIMESTAMP(DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 3650) DAY) + INTERVAL FLOOR(RAND() * 86400) SECOND) AS update_at
FROM cte;

# 상품재고 200만개 등록
INSERT INTO stock (product_id, quantity, create_at, update_at)
SELECT
    p.id AS product_id,
    50 as quantity,
    TIMESTAMP(DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 3650) DAY) + INTERVAL FLOOR(RAND() * 86400) SECOND) AS create_at,
    TIMESTAMP(DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 3650) DAY) + INTERVAL FLOOR(RAND() * 86400) SECOND) AS update_at
FROM product p;

# 유저 5명 등록
INSERT INTO users (name, point, hp_no, create_at, update_at)
WITH RECURSIVE cte (n) AS
(
    SELECT 1
    UNION ALL
    SELECT n + 1 FROM cte WHERE n < 5 -- 생성하고 싶은 더미 데이터의 개수
)
SELECT
    CONCAT('User-', LPAD(n, 7, '0')) AS name,
    FLOOR(1 + RAND() * 1000) AS point,
    CONCAT('010',
           '****',
           LPAD(FLOOR(RAND() * 10000), 4, '0')) as hp_no,
    TIMESTAMP(DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 3650) DAY) + INTERVAL FLOOR(RAND() * 86400) SECOND) AS create_at,
    TIMESTAMP(DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 3650) DAY) + INTERVAL FLOOR(RAND() * 86400) SECOND) AS update_at
FROM cte;
