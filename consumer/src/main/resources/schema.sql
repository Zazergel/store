CREATE TABLE IF NOT EXISTS CATEGORY
(
    id Integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS PRODUCT
(
    id Integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100) NOT NULL,
    price Integer,
    category_id Integer,
    FOREIGN KEY (category_id) REFERENCES CATEGORY (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS COMMENT
(
    id Integer GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    text VARCHAR(500) NOT NULL,
    created date CHECK (created  <= CURRENT_DATE),
    product_id Integer,
    FOREIGN KEY (product_id) REFERENCES PRODUCT (id)
);