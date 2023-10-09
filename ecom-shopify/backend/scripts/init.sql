create table if not exists java_backend.product
(
    id    bigint default nextval('java_backend.product_id_seq'::regclass) not null
    primary key,
    name  varchar(255),
    price numeric(38, 2)                                                  not null
    );

alter table java_backend.product
    owner to postgres;

create table if not exists java_backend."order"
(
    id      bigint default nextval('java_backend.order_id_seq'::regclass) not null
    primary key,
    user_id bigint
    );

alter table java_backend."order"
    owner to postgres;

create table if not exists java_backend.order_line
(
    id         bigint default nextval('java_backend.order_line_id_seq'::regclass) not null
    primary key,
    order_id   bigint
    references java_backend."order",
    product_id bigint
    references java_backend.product,
    quantity   integer                                                            not null
    constraint order_line_quantity_check
    check (quantity > 0)
    );

alter table java_backend.order_line
    owner to postgres;

create table if not exists java_backend."user"
(
    id       serial
    primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    role     varchar(15)  not null
    constraint user_role_check
    check ((role)::text = ANY ((ARRAY ['CUSTOMER'::character varying, 'ADMIN'::character varying])::text[]))
    );

alter table java_backend."user"
    owner to postgres;

