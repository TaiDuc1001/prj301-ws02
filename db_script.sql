-- Force drop the database if it exists
USE master;
GO
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'DucPT_SP25')
BEGIN
    ALTER DATABASE DucPT_SP25 SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE DucPT_SP25;
END;
GO

-- Create the database
CREATE DATABASE DucPT_SP25;
GO

-- Use the database
USE DucPT_SP25;
GO

-- Create the Users table (fixed schema)
CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- Should store hashed passwords
    first_name NVARCHAR(50),
    last_name NVARCHAR(50) NOT NULL,
    is_active BIT DEFAULT 1,
    role VARCHAR(20) DEFAULT 'user' CHECK (role IN ('user', 'admin', 'moderator')),
    isAdmin BIT DEFAULT 0 -- No trailing comma
);
GO

-- Insert sample users
INSERT INTO Users (username, password, first_name, last_name, is_active, role, isAdmin) VALUES
('admin', 'admin', 'John', 'Doe', 1, 'admin', 1),
('jane.smith', 'jane', 'Jane', 'Smith', 1, 'user', 0),
('michael_j', 'michael', 'Michael', 'Johnson', 1, 'user', 0),
('alice_w', 'alice', 'Alice', 'Williams', 1, 'user', 0),
('bob_c', 'bob', 'Bob', 'Carter', 1, 'user', 0),
('charlie_b', 'charlie', 'Charlie', 'Brown', 1, 'moderator', 0),
('admin_user_01', 'admin', 'Admin', 'User01', 1, 'admin', 1),
('admin_user_02', 'admin', 'Admin', 'User02', 1, 'admin', 1),
('mod_user_01', 'mod', 'Mod', 'User01', 1, 'moderator', 0),
('mod_user_02', 'mod', 'Mod', 'User02', 1, 'moderator', 0);
GO

-- Select all users
SELECT * FROM Users;
GO

SELECT username, password FROM users WHERE username = 'admin' AND password = 'admin'