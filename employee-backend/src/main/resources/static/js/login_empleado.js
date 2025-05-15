document.addEventListener("DOMContentLoaded", function () {
  const form = document.querySelector("form");

  form.addEventListener("submit", function (event) {
    event.preventDefault(); 

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch("/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ username, password })
    })
      .then(response => {
        if (response.ok) {
          window.location.href = "/inicio"; 
        } else if (response.status === 401) {
          alert("Credenciales incorrectas");
        } else {
          alert("Error al iniciar sesión");
        }
      })
      .catch(error => {
        console.error("Error en la petición:", error);
        alert("Error de conexión");
      });
  });
});
