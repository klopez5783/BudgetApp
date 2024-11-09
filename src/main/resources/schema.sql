--create table if not exists Budget(
--    ID int not null,
--    budget varchar(250) not null,
--    savings int not null,
--    needs int not null,
--    wants int not null,
--    PRIMARY KEY(ID)
--);
--
CREATE TABLE IF NOT EXISTS Transaction (
    ID INT NOT NULL,
    BudgetID INT NOT NULL,
    Category INT NOT NULL CHECK (Category >= 1),
    amount DECIMAL(10, 2) NOT NULL CHECK (amount >= 0.01),
    type VARCHAR(50),
    Memo VARCHAR(255),
    date DATE NOT NULL,
    account_id INT NOT NULL,  -- Add this line
    version INT,
    PRIMARY KEY (ID)
);
