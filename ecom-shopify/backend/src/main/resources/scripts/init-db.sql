-- Schema creation
CREATE SCHEMA java_backend;

-- Product table
CREATE TABLE java_backend.product (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(255),
                                      price DECIMAL(15, 2) NOT NULL
);

-- User table
CREATE TABLE java_backend.user (
                                   id SERIAL PRIMARY KEY,
                                   username VARCHAR(255) NOT NULL,
                                   password VARCHAR(255) NOT NULL,
                                   role VARCHAR(15) CHECK(role IN ('CUSTOMER', 'ADMIN')) NOT NULL
);
-- Order table
CREATE TABLE java_backend.order (
                                    id SERIAL PRIMARY KEY,
                                    user_id INT REFERENCES java_backend.user(id)
);

-- OrderLine table
CREATE TABLE java_backend.order_line (
                                         id SERIAL PRIMARY KEY,
                                         order_id INT REFERENCES java_backend.order(id),
                                         product_id INT REFERENCES java_backend.product(id),
                                         quantity INT NOT NULL CHECK(quantity > 0)
);
