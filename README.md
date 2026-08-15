# SpeedFast - Sistema de Gestión de Pedidos

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II** (Duoc UC).

## Descripción del Proyecto

SpeedFast es una empresa de reparto a domicilio que ofrece diferentes tipos de servicios. Este sistema simula la gestión y asignación de repartidores aplicando los principios fundamentales de la Programación Orientada a Objetos (POO), con un enfoque particular en **polimorfismo**, **sobrescritura (override)** y **sobrecarga (overload)** de métodos.

## Tipos de Pedidos y Lógica de Asignación

El sistema gestiona tres tipos específicos de pedidos, cada uno con criterios particulares para la asignación del repartidor:

1. **Comida (Restaurantes):** Requiere verificación de **mochila térmica**.
2. **Encomiendas (Documentos/Paquetes):** Requiere validación de **peso y embalaje**.
3. **Compras Express (Supermercado/Farmacia):** Asigna al repartidor más cercano con **disponibilidad inmediata**.

---

## Estructura de Clases

* **`Pedido`** *(Clase Base)*:
  * Contiene los atributos encapsulados: `idPedido`, `direccionEntrega` y `tipoPedido`.
  * Define el método base `asignarRepartidor()` (sin parámetros).
  * Define la sobrecarga del método `asignarRepartidor(String nombreRepartidor)`.

* **`PedidoComida`** *(Subclase)*: Sobrescribe la lógica de asignación validando mochila térmica.
* **`PedidoEncomienda`** *(Subclase)*: Sobrescribe la lógica de asignación validando peso y embalaje.
* **`PedidoExpress`** *(Subclase)*: Sobrescribe la lógica de asignación según disponibilidad inmediata y cercanía.

* **`Main`** *(Clase Principal)*: Instancia los objetos haciendo uso de polimorfismo y demuestra la ejecución de métodos sobrescritos y sobrecargados.
