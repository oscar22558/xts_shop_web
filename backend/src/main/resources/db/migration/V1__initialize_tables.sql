CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name VARCHAR(255) NOT NULL,
    parent_id BIGINT REFERENCES categories (id)
);

CREATE TABLE brands(
   id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
   created_at TIMESTAMP NOT NULL,
   updated_at TIMESTAMP NOT NULL,
   name VARCHAR(255) NOT NULL
);

CREATE TABLE items(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price FLOAT NOT NULL,
    manufacturer VARCHAR(255) NOT NULL,
    stock INTEGER NOT NULL,
    category_id BIGINT REFERENCES categories(id) NOT NULL,
    brand_id BIGINT REFERENCES brands(id) NOT NULL
);

CREATE TABLE price_histories(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    value FLOAT NOT NULL,
    item_id BIGINT REFERENCES items(id) NOT NULL
);

CREATE TABLE images(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    uri VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    extension VARCHAR(100) NOT NULL,
    item_id BIGINT REFERENCES items(id) NOT NULL
);

CREATE TABLE privileges(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE roles(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE roles_privileges(
     id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
     role_id BIGINT REFERENCES roles(id) NOT NULL,
     privilege_id BIGINT REFERENCES privileges(id) NOT NULL
);

CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    last_login_at TIMESTAMP,
    role_id BIGINT REFERENCES roles(id) NOT NULL
);

CREATE TABLE users_roles(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    user_id BIGINT REFERENCES users(id) NOT NULL,
    role_id BIGINT REFERENCES roles(id) NOT NULL
);

CREATE TABLE addresses(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    row1 VARCHAR(100) NOT NULL,
    row2 VARCHAR(100),
    area VARCHAR(100),
    district VARCHAR(100),
    city VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    user_id BIGINT REFERENCES users(id) NOT NULL
);

CREATE TABLE orders(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    recipient_last_name VARCHAR(255),
    recipient_first_name VARCHAR(255),
    recipient_email VARCHAR(255),
    recipient_phone VARCHAR(255),
    status VARCHAR(100),
    payment_intent_id VARCHAR(255) NOT NULL,
    user_id BIGINT REFERENCES users(id) NOT NULL
);

CREATE TABLE shipping_addresses(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    row1 VARCHAR(100) NOT NULL,
    row2 VARCHAR(100),
    area VARCHAR(100),
    district VARCHAR(100),
    city VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    order_id BIGINT REFERENCES orders(id) NOT NULL
);

CREATE TABLE invoices(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    items_total FLOAT NOT NULL,
    shipping_fee FLOAT NOT NULL,
    total FLOAT NOT NULL,
    order_id BIGINT REFERENCES orders(id) NOT NULL
);

CREATE TABLE ordered_items(
    id BIGSERIAL PRIMARY KEY NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    quantity INTEGER NOT NULL,
    price FLOAT NOT NULL,
    order_id BIGINT REFERENCES orders(id),
    item_id BIGINT REFERENCES items(id)
)