INSERT INTO customer (first_name, last_name)
VALUES ('John', 'Smith'),
       ('Rob', 'Jones');
INSERT INTO account (type, nickname, rewards, balance, customer_id)
VALUES ('Savings', 'Johns Savings Account', 10, 1000, 1),
       ('Cheque', 'Robs Cheque Account', 20, 500, 2);
INSERT INTO account_activity (account_id, activity_type, amount, timestamp)
VALUES (1, 'Deposit', 500.00, '2023-01-15 10:30:00'),
       (2, 'Withdrawal', 200.00, '2023-01-20 15:45:00');
INSERT INTO address (city, state, street_name, street_number, zip, customer_id)
VALUES ('New York', 'NY', 'Broadway', '123', '10001', 1),
       ('Los Angeles', 'CA', 'Sunset Blvd', '456', '90001', 2);
INSERT INTO bill (status, payee, nick_name, creation_date, payment_date,
                  recurring_date, upcoming_payment_date, payment_amount, account_id)
VALUES ('Pending', 'Power Company', 'Electric Bill', '2023-01-05',
        '2023-01-25', 'Monthly', '2023-02-25', 120.00, 1),
       ('Paid', 'ISP', 'Internet Bill', '2023-01-10', '2023-01-30',
        'Monthly',
        '2023-02-28', 50.00, 2);
INSERT INTO deposit (type, transaction_date, status, payee_id, medium, amount, description, account_id)
VALUES ('DEPOSIT', '2023-01-12', 'completed', 3, 'BALANCE', 300.00, '300 dollars', 1),
       ('DEPOSIT', '2023-01-28', 'completed', 4, 'REWARDS', 1000.00, '1000 dollars', 2);
INSERT INTO withdraw (type, transaction_date, status, payer_id, medium, amount, description, account_id)
VALUES ('WITHDRAW', '2023-01-12', 'completed', 5, 'BALANCE', 10.00, '300 dollars', 1),
       ('WITHDRAW', '2023-01-28', 'completed', 6, 'REWARDS', 20.00, '1000 dollars', 2);
