# SpeedFast Semana 8

## Proyecto correspondiente a la Semana 8 de Desarrollo Orientado a Objetos II

Aplicación desarrollada con Java Swing para gestionar pedidos, repartidores y entregas mediante operaciones CRUD, utilizando MySQL y JDBC.

### Funciones
- Registrar, consultar, editar y eliminar pedidos, repartidores y entregas.
- Gestionar pedidos de comida, encomienda y express.
- Actualizar el estado de los pedidos.
- Asociar entregas con pedidos y repartidores.
- Mostrar registros mediante JTable.
- Generar identificadores automáticamente.
- Validar datos y mostrar mensajes de error.

### Base de datos
Se utiliza la base de datos `speedfast_db` con las siguientes tablas:
- pedido
- repartidor
- entrega

### Estructura
- dao: conexión y acceso a MySQL.
- interfaces: contrato CRUD utilizado por los DAO.
- model: clases de pedidos, estados, repartidores y entregas.
- ui: inicio de la aplicación.
- vista: formularios y componentes gráficos.

### Ejecución
1. Ejecutar `sql/speedfast_db.sql` en MySQL Workbench.
2. Abrir el proyecto en IntelliJ IDEA con Java 17 y cargar las dependencias Maven.
3. Crear `db.properties` junto al `pom.xml` y configurar la URL, el usuario y la contraseña de MySQL.
4. Ejecutar `ui.Main` con MySQL encendido.

### Autor
Daniel González Salinas.
