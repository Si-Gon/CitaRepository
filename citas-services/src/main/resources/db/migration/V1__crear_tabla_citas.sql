CREATE TABLE citas(
    id BIGINT AUTO_INCREMENT PRIMARY KEY
    ,paciente_id BIGINT NOT NULL
    ,especialidad VARCHAR(100) NOT NULL
    ,fecha_hora VARCHAR (50) NOT NULL
);