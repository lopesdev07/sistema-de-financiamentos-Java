USE financing_project;

CREATE TABLE users (
user_id INT auto_increment PRIMARY KEY,
login_cpf VARCHAR(11) UNIQUE NOT NULL,
password_hash VARCHAR(255) NOT NULL);

CREATE TABLE realestate_financing (
financing_id INT AUTO_INCREMENT PRIMARY KEY,
financed_amount DECIMAL(15,2) NOT NULL,
loan_term_months INT NOT NULL,
annual_interest_rate DECIMAL(15,2) NOT NULL,
amortization_type VARCHAR(20) NOT NULL,
property_type VARCHAR(20) NOT NULL,
financing_status VARCHAR(20) NOT NULL DEFAULT 'REQUESTED',
user_id INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(user_id),

bedrooms INT,
parking_spaces INT,
land_area DOUBLE,
floor INT,
elevator BOOLEAN,
condominium_fee DECIMAL(15,2),
zoning VARCHAR(100));

CREATE TABLE vehicle_financing (
financing_id INT AUTO_INCREMENT PRIMARY KEY,
financed_amount DECIMAL(15,2) NOT NULL,
loan_term_months INT NOT NULL,
annual_interest_rate DECIMAL(15,2) NOT NULL,
amortization_type VARCHAR(20) NOT NULL,
vehicle_type VARCHAR(20) NOT NULL,
vehicle_condition VARCHAR(20) NOT NULL,
financing_status VARCHAR(20) NOT NULL DEFAULT 'REQUESTED',
user_id INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(user_id),

vehicle_value DECIMAL(15,2),
down_payment DECIMAL(15,2),
brand VARCHAR(100),
model VARCHAR(100),
manufacture_year INT,
mileage INT);