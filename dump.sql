CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
	email VARCHAR(255) UNIQUE;
);

INSERT INTO users (username, password_hash, email) 
VALUES ('testUser', '$2a$10$7QJWqw9hQo7kF63ihNOuAOBHME8W5CBzly3PZZcFXmND5H9H8hSCG', 'testUser123@gmail.com');


CREATE TABLE portefeuille (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE portefeuille_assets (
    id SERIAL PRIMARY KEY,
    portefeuille_id INT NOT NULL,
    asset_symbol VARCHAR(50) NOT NULL,  -- Par exemple "AAPL" pour Apple ou "BTC" pour Bitcoin
    quantity INT NOT NULL CHECK (quantity >= 0),  -- Vérification que la quantité ne soit pas négative
    FOREIGN KEY (portefeuille_id) REFERENCES portefeuille(id) ON DELETE CASCADE
);
