CREATE TABLE citas (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    paciente_id BIGINT      NOT NULL,
    doctor_id   BIGINT      NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    fecha_hora  DATETIME    NOT NULL
    ,activa TINYINT(1) NOT NULL DEFAULT 1
);