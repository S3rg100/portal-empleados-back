document.addEventListener("DOMContentLoaded", async () => {
  const params = new URLSearchParams(window.location.search);
  const id = params.get("id");

  if (!id) {
    document.getElementById("info-producto").innerHTML = "<p>❌ No se ha proporcionado un ID de producto.</p>";
    return;
  }

  try {
    const producto = await obtenerProducto(id);

    document.getElementById("id").textContent = producto.id;
    document.getElementById("nombre").textContent = producto.nombre;
    document.getElementById("precioCompra").textContent = producto.precioCompra;
    document.getElementById("precioVenta").textContent = producto.precioVenta;
    document.getElementById("existencias").textContent = producto.existencias;
    document.getElementById("imagen").src = producto.linkImagen || "https://via.placeholder.com/200";
  } catch (error) {
    document.getElementById("info-producto").innerHTML = "<p>❌ Error al obtener el producto.</p>";
  }
});
