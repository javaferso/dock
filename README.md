DockPOS

DockPOS es un proyecto web desarrollado en Java utilizando JSP, diseñado para facilitar las labores de soporte a POS (Point of Sale) en el área de retail, específicamente para el departamento de TI Operaciones y Formatos. El proyecto ofrece funcionalidades clave como rescate y manejo de logs, transacciones a bases de datos, monitoreo de ventas en caja, y conexiones SSH y VNC.
Tecnologías Utilizadas

    Java 18: Lenguaje de programación principal del proyecto.
    Apache Maven: Herramienta de gestión y construcción del proyecto.
    JSP (JavaServer Pages): Tecnología utilizada para la creación de las páginas web dinámicas.
    PostgreSQL 15.6: Base de datos utilizada para la persistencia de datos.
    Arquitectura en Capas:
        Página Web: Interfaz de usuario construida con JSP.
        Servlet: Controlador que maneja las solicitudes del usuario y las respuestas.
        Controladora: Capa lógica del negocio que procesa las solicitudes.
        JPA (Java Persistence API): Capa de acceso a datos que interactúa con la base de datos.
        ControladoraPersistencia: Maneja las transacciones y operaciones de persistencia.
        EntityClass: Representaciones de las tablas de la base de datos como clases Java.
    Frameworks y Bibliotecas de JavaScript:
        jQuery UI 1.12.1
        jQuery 3.7.0
        Bootstrap 5.3.1

Funcionalidades Principales

    Rescate de Logs: Búsqueda, lectura y descarga de logs de manera eficiente.
    Transacciones a Bases de Datos: Manejo de transacciones para la gestión de datos.
    Monitoreo de Ventas en Caja: Control y monitoreo en tiempo real de las ventas realizadas.
    Conexiones SSH y VNC: Facilitación de conexiones remotas para la gestión y soporte de sistemas POS.

Estructura del Proyecto

    Página Web (JSP): Interfaz interactiva para el usuario final.
    Servlets: Controladores que gestionan las solicitudes HTTP.
    Controladora: Implementa la lógica del negocio y coordina las operaciones.
    ControladoraPersistencia: Gestión de la persistencia y transacciones con la base de datos.
    EntityClass: Clases Java que representan las entidades de la base de datos.

Objetivo del Proyecto

El objetivo principal del proyecto DockPOS es mejorar la eficiencia y efectividad del soporte técnico a los sistemas POS en el área de retail, proporcionando herramientas avanzadas para la gestión de logs, monitoreo de ventas, y manejo de conexiones remotas.
Instrucciones de Configuración

    Clonar el Repositorio:

    bash

    Copy code

    git clone git@github.com-personal:javaferso/dock.git cd vision

    Configurar la Base de Datos:
        Crear una base de datos en PostgreSQL 15.6.
        Configurar las credenciales de la base de datos en el archivo persistence.xml.

    Construir el Proyecto con Maven:

    bash

    Copy code

    mvn clean install

    Desplegar en un Servidor de Aplicaciones:
        Configurar un servidor compatible con Java 18 (por ejemplo, Apache Tomcat).
        Desplegar el archivo WAR generado en el servidor.

    Acceder a la Aplicación:
        Abrir un navegador web y acceder a la URL configurada para la aplicación.

Contribuir

Las contribuciones al proyecto son bienvenidas. Por favor, sigue las pautas de contribución y asegúrate de realizar pruebas exhaustivas antes de enviar un pull request.
