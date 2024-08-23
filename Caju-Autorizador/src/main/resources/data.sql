CREATE TABLE IF NOT EXISTS account (
    accountId INT AUTO_INCREMENT PRIMARY KEY,
    clientId INT NOT NULL,
    balanceFood double,
    balanceMeal double,
    balanceCash double
);

CREATE TABLE IF NOT EXISTS client (
    clientid INT AUTO_INCREMENT PRIMARY KEY,
    accountId INT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    mcc VARCHAR(3)

);

INSERT INTO client (CLIENT_ID,name, email,mcc) VALUES (1,'Caju', 'caju@caju.com','5811');
INSERT INTO client (CLIENT_ID, name, email,mcc) VALUES (2, 'Marcelo', 'marcelo@caju.com', '5411');

INSERT INTO account (ACCOUNT_ID, CLIENT_ID, BALANCE_FOOD, BALANCE_MEAL, BALANCE_CASH) VALUES (1,1,1000,2000,3000);
INSERT INTO account (ACCOUNT_ID, CLIENT_ID, BALANCE_FOOD, BALANCE_MEAL, BALANCE_CASH) VALUES (2,2,1000,2000,3000);