document.getElementById("form-crear").addEventListener("submit", async function(e) {
  e.preventDefault();

  const producto = {
    nombre: document.getElementById("nombre").value,
    precioCompra: parseFloat(document.getElementById("precioCompra").value),
    precioVenta: parseFloat(document.getElementById("precioVenta").value),
    existencias: parseInt(document.getElementById("existencias").value),
    linkImagen: document.getElementById("linkImagen").value
  };

  try {
    await crearProducto(producto);
    document.getElementById("mensaje").textContent = "✅ Producto agregado exitosamente.";
    document.getElementById("form-crear").reset();
  } catch (error) {
    document.getElementById("mensaje").textContent = "❌ Error al agregar producto.";
  }
});
