const BASE_URL = "http://localhost:8080/producto";

// Obtener todos los productos
async function obtenerProductos() {
  const res = await fetch(`${BASE_URL}/findAll`);
  return res.json();
}

// Obtener producto por ID
async function obtenerProducto(id) {
  const res = await fetch(`${BASE_URL}/find/${id}`);
  if (!res.ok) throw new Error("Producto no encontrado");
  return res.json();
}

// Crear producto
async function crearProducto(producto) {
  const res = await fetch(`${BASE_URL}/add`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(producto)
  });
  if (!res.ok) throw new Error("Error al crear producto");
  return res.json();
}

// Editar producto
async function editarProducto(id, producto) {
  const res = await fetch(`${BASE_URL}/update/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(producto)
  });
  if (!res.ok) throw new Error("Error al editar producto");
  return res.json();
}

// Eliminar producto
async function eliminarProducto(id) {
  const res = await fetch(`${BASE_URL}/delete/${id}`, {
    method: "DELETE"
  });
  if (!res.ok) throw new Error("Error al eliminar producto");
}
