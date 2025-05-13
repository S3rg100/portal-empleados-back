-- Insertar Empleado con id fijo (aunque esté autogenerado en la entidad)
INSERT INTO empleado (id, cedula, nombre, apellido, rol)
VALUES ('1e8e4010-0000-0000-0000-000000000001', '123456789', 'Juan', 'Pérez', 'ADMIN');

-- Insertar Login usando ese id
INSERT INTO empleado_login (username, password, empleado_id)
VALUES ('juan', '$2a$10$5eB3vJ2x6ZcyLkENL/NyXut1Ed2b.GiI.CEGwH24EoKljsdZz/AuC', '1e8e4010-0000-0000-0000-000000000001');
