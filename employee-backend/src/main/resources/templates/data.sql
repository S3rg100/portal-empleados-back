-- Insertar Empleado con id fijo (aunque esté autogenerado en la entidad)
INSERT INTO empleado (id, nombre, apellido, rol)
VALUES ('1e8e4010-0000-0000-0000-000000000001', 'Juan', 'Pérez', 'ADMIN');

-- Insertar Login usando ese id
INSERT INTO empleado_login (username, password, empleado_id)
VALUES ('juan', '$2a$12$qWjBi19NLmgPDz2UrYnEJ.6o8AszWb.rn2jJqnkcNSGY8XojhGenK', '1e8e4010-0000-0000-0000-000000000001');

producto
producto/add
{
  "nombre": "Camisa manga larga",
  "precioCompra": 15.50,
  "precioVenta": 29.99,
  "existencias": 100,
  "linkImagen": "https://example.com/imagenes/camisa.jpg"
}