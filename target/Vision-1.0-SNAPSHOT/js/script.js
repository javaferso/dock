/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
/* global localData, xhr */

// Función para redirigir a la página dashboard.jsp
/*function goToDashboard() {
    var selectedIP = '<%= ip %>';
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert("Volviendo a dashboard!"); 
            window.location.href = "dashboard.jsp";
            
        }
   };
    xhr.open("POST", "exitMonitor.jsp", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("selectedIP=<%= ip %>" + encodeURIComponent(selectedIP)); // Pasar la IP seleccionada como parámetro
} 
*/
var dashboardCalled = false;

function goToDashboard() {
   if (dashboardCalled) {
       return;
   }

   var selectedIP = '<%= ip %>';
   var xhr = new XMLHttpRequest();
   xhr.onreadystatechange = function() {
       if (xhr.readyState === 4 && xhr.status === 200) {
           alert("Volviendo a dashboard!");
           window.location.href = "dashboard.jsp";
           dashboardCalled = true;
       }
   };
   xhr.open("POST", "exitMonitor.jsp", true);
   xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   xhr.send("selectedIP=<%= ip %>" + encodeURIComponent(selectedIP)); // Pasar la IP seleccionada como parámetro
}

// Función para salir del monitor y ejecutar la lógica de exitMonitor.jsp
function exitMonitor() {
    var xhr = new XMLHttpRequest();
    var selectedIP = '<%= ip %>';
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            //Mostrar ventana emergente con el mensaje
            alert("Saliendo de dockpos!");
            // Redirigir a index.jsp después de 3 segundos
            setTimeout(function() {
                window.location.href = "index.jsp";
            }, 2000);
        }
    };
    xhr.open("POST", "exitMonitor.jsp", true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("selectedHost=" + encodeURIComponent(selectedIP)); // Pasar la IP seleccionada como parámetro
}
function verBoleta() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                console.log("Boleta:", response.boleta);
            } else {
                console.log("Error al obtener la boleta");
            }
        }
    };
    xhr.open("GET", "verBoleta.jsp", true);
    xhr.send();
}
function mostrarBoleta(boleta) {
    var boletaContainer = document.getElementById('boletaContainer');
    boletaContainer.innerHTML = boleta;
}
function listarUsuarios() {
    console.log("listarUsuarios called");
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'SVUsuarios?action=list', true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('main-content').innerHTML = xhr.responseText;
        }
    };
    xhr.send();
}








