-- schema.sql

-- Customer table
CREATE TABLE customer
(
    customer_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    addresses JSON
);

-- Account table
CREATE TABLE account
(
    account_id  INT AUTO_INCREMENT PRIMARY KEY,
    type        VARCHAR(255),
    nickname    VARCHAR(255),
    rewards     INT,
    balance     DECIMAL(10, 2),
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

-- Bill table
CREATE TABLE bill
(
    bill_id               INT AUTO_INCREMENT PRIMARY KEY,
    status                VARCHAR(255),
    payee                 VARCHAR(255),
    nick_name             VARCHAR(255),
    creation_date         DATE,
    payment_date          DATE,
    recurring_date        VARCHAR(255),
    upcoming_payment_date DATE,
    payment_amount        DECIMAL(10, 2),
    account_id            INT,
    customer_id           INT,
    FOREIGN KEY (account_id) REFERENCES account (account_id),
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

-- Account Activity table
CREATE TABLE account_activity
(
    activity_id   INT AUTO_INCREMENT PRIMARY KEY,
    account_id    INT,
    activity_type VARCHAR(255),
    amount        DECIMAL(10, 2),
    timestamp     TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account (account_id)
);

-- Deposit table
CREATE TABLE deposit
(
    deposit_id       INT AUTO_INCREMENT PRIMARY KEY,
    type             VARCHAR(255),
    transaction_date DATE,
    status           VARCHAR(255),
    payee_id         INT,
    medium           VARCHAR(255),
    amount           DECIMAL(10, 2),
    description      VARCHAR(255),
    account_id       INT,
    FOREIGN KEY (account_id) REFERENCES account (account_id)
);

-- Withdraw table
CREATE TABLE withdraw
(
    withdraw_id      INT AUTO_INCREMENT PRIMARY KEY,
    type             VARCHAR(255),
    transaction_date DATE,
    status           VARCHAR(255),
    payer_id         INT,
    medium           VARCHAR(255),
    amount           DECIMAL(10, 2),
    description      VARCHAR(255),
    account_id       INT,
    FOREIGN KEY (account_id) REFERENCES account (account_id)
);
