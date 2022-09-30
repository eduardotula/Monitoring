CREATE TABLE esp32(
id SERIAL PRIMARY KEY NOT NULL,
identificador CHAR(15) NOT NULL,
nome_rua VARCHAR(500) NOT NULL,
numero VARCHAR(10) NOT NULL,
bairro VARCHAR(100) NOT NULL,
cidade VARCHAR(100) NOT NULL,
cep CHAR(8) NOT NULL,
estado VARCHAR(50) NOT NULL,
pais VARCHAR(50) NOT NULL,
latitude VARCHAR(20) NOT NULL,
longitude VARCHAR(20) NOT NULL,
altura INT NOT NULL,
criado_em DATETIME NOT NULL
);

CREATE TABLE sensor_data(
id SERIAL PRIMARY KEY NOT NULL,
erros VARCHAR(256),
co2 INT NOT NULL,
tvoc INT NOT NULL,
temperatura DECIMAL(5,2),
umidade INT NOT NULL
);

CREATE TABLE co2_data(
id SERIAL PRIMARY KEY NOT NULL,
id_esp32 INT NOT NULL,
id_sensor_data INT NOT NULL,
coleta DATETIME NOT NULL,
CONSTRAINT co2_data_sensor_data_fk FOREIGN KEY (id_sensor_data) REFERENCES sensor_data(id),
CONSTRAINT co2_data_esp32_fk FOREIGN KEY (id_esp32) REFERENCES esp32(id)
);

CREATE TABLE esp32_config_params(
id SERIAL PRIMARY KEY NOT NULL,
id_esp32 INT NOT NULL,
param VARCHAR(50) NOT NULL,
value VARCHAR(50) NOT NULL,
active BOOLEAN,
CONSTRAINT esp32_config_params_esp32_fk FOREIGN KEY (id_esp32) REFERENCES esp32(id)
);

