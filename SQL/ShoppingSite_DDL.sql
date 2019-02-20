use ShoppingSite;
CREATE TABLE CUSTOMER (
    CUSTOMER_ID INT PRIMARY KEY AUTO_INCREMENT,
    USER_NAME VARCHAR(50) UNIQUE NOT NULL,
    PASSWORD VARCHAR(128) NOT NULL,
    FULL_NAME VARCHAR(50) NOT NULL,
    ADDRESS VARCHAR(100) NOT NULL,
    CONTACT BIGINT NOT NULL,
    BIRTH_DATE DATE NOT NULL
)auto_increment=83836700;
CREATE TABLE CATEGORY (
    CATEGORY_ID INT PRIMARY KEY AUTO_INCREMENT,
    CATEGORY_NAME VARCHAR(50) NOT NULL,
    CATEGORY_DESCRIPTION VARCHAR(1000) NOT NULL
)auto_increment=83838400;
CREATE TABLE PRODUCT (
    PRODUCT_ID INT PRIMARY KEY AUTO_INCREMENT,
    PRODUCT_NAME VARCHAR(100) NOT NULL,
    PRODUCT_DESCRIPTION VARCHAR(1000) NOT NULL,
    PRICE FLOAT NOT NULL,
    STOCK INT NOT NULL,
    CATEGORY_ID INT REFERENCES CATEGORY (CATEGORY_ID)
)auto_increment=83838000;
CREATE TABLE CUSTOMER_ORDER (
    ORDER_ID INT PRIMARY KEY AUTO_INCREMENT,
    CUSTOMER_ID INT REFERENCES CUSTOMER (CUSTOMER_ID),
    ORDER_TIME DATETIME NOT NULL,
    ORDER_STATUS ENUM('Pending','Confirmed') NOT NULL
)  AUTO_INCREMENT=83837900;
CREATE TABLE PRODUCT_ORDER (
    ORDER_ID INT REFERENCES CUSTOMER_ORDER (ORDER_ID),
    PRODUCT_ID INT REFERENCES PRODUCT (PRODUCT_ID),
    QUANTITY INT NOT NULL
);
