INSERT INTO account (balance, name, nickname, rewards, type, customer_id)
VALUES ('SAVINGS', 'Johns Account', 'John Smith', 100, 1, 1),
       ('CHEQUE', 'CheckAcc', 50, 'Checking', 2);
INSERT INTO account_activity (account_id, activity_type, amount, timestamp)
VALUES (1, 'Deposit', 500.00, '2023-01-15 10:30:00'),
       (2, 'Withdrawal', 200.00, '2023-01-20 15:45:00');
INSERT INTO address (city, state, street_name, street_number, zip, customer_id)
VALUES ('New York', 'NY', 'Broadway', '123', '10001', 1),
       ('Los Angeles', 'CA', 'Sunset Blvd', '456', '90001', 2);
INSERT INTO bill (creation_date, nick_name, payee, payment_amount, payment_date, recurring_date, status, upcoming_payment_date, account_id)
VALUES ('2023-01-05', 'Electric Bill', 'Power Company', 120.00, '2023-01-25', 'Monthly', 'Pending', '2023-02-25', 1),
       ('2023-01-10', 'Internet Bill', 'ISP', 50.00, '2023-01-30', 'Monthly', 'Paid', '2023-02-28', 2);
INSERT INTO customer (first_name, last_name)
VALUES ('John', 'Doe'),
       ('Jane', 'Smith');
INSERT INTO deposit (amount, description, medium, payee_id, status, transaction_date, type, account_id)
VALUES (300.00, 'Bonus', 'Online', 3, 'Completed', '2023-01-12', 'Credit', 1),
       (1000.00, 'Salary', 'Direct Deposit', 4, 'Completed', '2023-01-28', 'Credit', 2);
INSERT INTO withdraw (amount, description, medium, payer_id, status, transaction_date, type, account_id)
VALUES (50.00, 'ATM Withdrawal', 'ATM', 5, 'Completed', '2023-01-18', 'Debit', 1),
       (300.00, 'Grocery Shopping', 'Online', 6, 'Completed', '2023-01-22', 'Debit', 2);
