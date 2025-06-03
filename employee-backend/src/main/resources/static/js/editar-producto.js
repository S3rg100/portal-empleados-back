document.addEventListener("DOMContentLoaded", async () => {
  const params = new URLSearchParams(window.location.search);
  const id = params.get("id");

  if (!id) {
    document.getElementById("mensaje").textContent = "❌ No se proporcionó un ID.";
    return;
  }

  try {
    const producto = await obtenerProducto(id);

    document.getElementById("id").value = producto.id;
    document.getElementById("nombre").value = producto.nombre;
    document.getElementById("precioCompra").value = producto.precioCompra;
    document.getElementById("precioVenta").value = producto.precioVenta;
    document.getElementById("existencias").value = producto.existencias;
    document.getElementById("linkImagen").value = producto.linkImagen;
  } catch (error) {
    document.getElementById("mensaje").textContent = "❌ Error al cargar el producto.";
  }
});

document.getElementById("form-editar").addEventListener("submit", async (e) => {
  e.preventDefault();

  const id = document.getElementById("id").value;

  const productoActualizado = {
    nombre: document.getElementById("nombre").value,
    precioCompra: parseFloat(document.getElementById("precioCompra").value),
    precioVenta: parseFloat(document.getElementById("precioVenta").value),
    existencias: parseInt(document.getElementById("existencias").value),
    linkImagen: document.getElementById("linkImagen").value
  };

  try {
    await editarProducto(id, productoActualizado);
    document.getElementById("mensaje").textContent = "✅ Producto actualizado correctamente.";
  } catch (error) {
    document.getElementById("mensaje").textContent = "❌ Error al actualizar producto.";
  }
});
