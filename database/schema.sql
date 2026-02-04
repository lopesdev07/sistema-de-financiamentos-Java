DROP TABLE IF EXISTS financiamentos_imobiliarios;

CREATE TABLE financiamentos_imobiliarios (
    id_financiamento INT AUTO_INCREMENT PRIMARY KEY,
    valor_financiado DOUBLE NOT NULL,
    prazo_meses INT NOT NULL,
    taxa_juros_anual DOUBLE NOT NULL,
    tipo_amortizacao VARCHAR(20) NOT NULL,
    tipo_imovel VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'SOLICITADO',
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)



    quartos INT,
    vagas INT,
    vagas_garagem INT,
    area_terreno DOUBLE,
    andar INT,
    elevador BOOLEAN,
    valor_condominio DOUBLE,
    zoneamento VARCHAR(100)
);

CREATE TABLE users (
user_id INT AUTO_INCREMENT PRIMARY KEY,
login_cpf VARCHAR(11) UNIQUE NOT NULL,
senha_hash VARCHAR(255) NOT NULL
)

