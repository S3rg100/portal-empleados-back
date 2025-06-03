document.getElementById("form-login").addEventListener("submit", async function (e) {
  e.preventDefault();

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  try {
    const response = await fetch("http://localhost:8080/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: new URLSearchParams({ username, password }),
    });

    if (response.ok) {
      // Si usas sesiones y cookies, el navegador las maneja automáticamente.
      // Redireccionamos al dashboard (pantalla de bienvenida).
      window.location.href = "html/index.html";
    } else {
      document.getElementById("mensaje").textContent = "❌ Usuario o contraseña incorrectos.";
    }
  } catch (error) {
    console.error(error);
    document.getElementById("mensaje").textContent = "❌ Error de conexión al servidor.";
  }
});
