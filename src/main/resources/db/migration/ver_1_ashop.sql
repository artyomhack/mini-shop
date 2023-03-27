create table carts
(
    id      int8 not null,
    user_id int8,
    primary key (id)
);

create table carts_products
(
    cart_id    int8 not null,
    product_id int8 not null
);

create table categories
(
    id              int8   not null,
    title           varchar(255),
    amount_products numeric(19, 2),
    summa           float8 not null,
    order_id        int8,
    product_id      int8,
    primary key (id)
);

create table orders
(
    id          int8   not null,
    address     varchar(255),
    create_time timestamp,
    status      varchar(255),
    summa       float8 not null,
    update_time timestamp,
    user_id     int8,
    primary key (id)
);

create table orders_details
(
    order_id   int8 not null,
    details_id int8 not null
);

create table products
(
    id     int8   not null,
    amount int4,
    price  float8 not null,
    title  varchar(255),
    primary key (id)
);

create table products_categories
(
    product_id  int8 not null,
    category_id int8 not null
);

create table users
(
    id       int8    not null,
    archive  boolean not null,
    email    varchar(255),
    number   varchar(255),
    password varchar(255),
    role     varchar(255),
    username varchar(255),
    cart_id  int8,
    primary key (id)
);

alter table if exists orders_details
    drop constraint if exists UK_kk6y3pyhjt6kajomtjbhsoajo;