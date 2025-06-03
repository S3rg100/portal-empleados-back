document.addEventListener("DOMContentLoaded", async () => {
  const tabla = document.getElementById("tabla-productos");
  const productos = await obtenerProductos();

  productos.forEach(producto => {
    const fila = document.createElement("tr");

    fila.innerHTML = `
      <td>${producto.id}</td>
      <td>${producto.nombre}</td>
      <td>${producto.precioCompra}</td>
      <td>${producto.precioVenta}</td>
      <td>${producto.existencias}</td>
      <td>
        <button onclick="editar('${producto.id}')">✏️</button>
        <button onclick="eliminar('${producto.id}')">🗑️</button>
      </td>
    `;

    tabla.appendChild(fila);
  });
});

function editar(id) {
  window.location.href = `editar-producto.html?id=${id}`;
}

async function eliminar(id) {
  if (confirm("¿Estás seguro de eliminar este producto?")) {
    await eliminarProducto(id);
    location.reload(); // Recarga la tabla
  }
}
