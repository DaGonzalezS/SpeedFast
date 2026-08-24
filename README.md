# SpeedFast - Sistema de Gestión de Pedidos

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II** de Duoc UC.

## Descripción del Proyecto

SpeedFast es una empresa de reparto a domicilio que ofrece diferentes tipos de servicios. En esta segunda etapa del proyecto se implementa una estructura basada en **abstracción, herencia y sobrescritura de métodos**, permitiendo representar distintos tipos de pedidos con comportamientos específicos.

El sistema utiliza una clase abstracta llamada `Pedido`, que contiene los atributos y comportamientos comunes de todos los pedidos, mientras que las subclases implementan su propia lógica para calcular el tiempo estimado de entrega.

---

## Tipos de Pedidos y Tiempo de Entrega

El sistema gestiona tres tipos de pedidos, cada uno con una lógica diferente para calcular su tiempo estimado de entrega:

1. **PedidoComida**
   - Tiempo base de **15 minutos**.
   - Se agregan **2 minutos por cada kilómetro** de distancia.

2. **PedidoEncomienda**
   - Tiempo base de **20 minutos**.
   - Se agregan **1.5 minutos por cada kilómetro** de distancia.
   - El resultado se ajusta a un valor entero.

3. **PedidoExpress**
   - Tiempo base de **10 minutos**.
   - Si la distancia es superior a **5 kilómetros**, se agregan **5 minutos adicionales**.

---

## Estructura de Clases

- **`Pedido`** *(Clase Abstracta)*:
  - Contiene los atributos encapsulados:
    - `idPedido`
    - `direccionEntrega`
    - `distanciaKm`
  - Implementa el método `mostrarResumen()`.
  - Declara el método abstracto `calcularTiempoEntrega()`.

- **`PedidoComida`** *(Subclase)*:
  - Hereda de `Pedido`.
  - Sobrescribe `calcularTiempoEntrega()`.
  - Calcula el tiempo utilizando la fórmula:
    `15 + (2 × distanciaKm)`.

- **`PedidoEncomienda`** *(Subclase)*:
  - Hereda de `Pedido`.
  - Sobrescribe `calcularTiempoEntrega()`.
  - Calcula el tiempo utilizando la fórmula:
    `20 + (1.5 × distanciaKm)`.

- **`PedidoExpress`** *(Subclase)*:
  - Hereda de `Pedido`.
  - Sobrescribe `calcularTiempoEntrega()`.
  - Retorna 10 minutos para distancias de hasta 5 km y 15 minutos cuando la distancia supera los 5 km.

- **`Main`** *(Clase Principal)*:
  - Instancia un objeto de cada tipo de pedido.
  - Ejecuta el método `mostrarResumen()`.
  - Ejecuta `calcularTiempoEntrega()`.
  - Muestra en consola los tiempos estimados de entrega de cada pedido.

---

## Conceptos de Programación Orientada a Objetos Aplicados

Durante el desarrollo del proyecto se aplican los siguientes conceptos:

- **Abstracción:** mediante la clase abstracta `Pedido`.
- **Encapsulamiento:** mediante atributos privados y métodos de acceso.
- **Herencia:** las clases `PedidoComida`, `PedidoEncomienda` y `PedidoExpress` heredan de `Pedido`.
- **Sobrescritura (`@Override`):** cada subclase implementa su propia versión de `calcularTiempoEntrega()`.
- **Polimorfismo:** los diferentes tipos de pedidos pueden ser tratados mediante referencias de tipo `Pedido`.
- **Reutilización de código:** los atributos y comportamientos comunes se concentran en la clase padre.

---

## Ejemplo de Ejecución

```text
PedidoComida #1
Dirección: Av. Italia 456
Distancia: 4.0 km
Tiempo estimado de entrega: 23 minutos

PedidoEncomienda #2
Dirección: Av. Independencia 123
Distancia: 6.0 km
Tiempo estimado de entrega: 29 minutos

PedidoExpress #3
Dirección: Av. Apoquindo 1500
Distancia: 7.0 km
Tiempo estimado de entrega: 15 minutos
```

---

## Tecnologías Utilizadas

- Java
- IntelliJ IDEA
- Git
- GitHub

---

## Autor

Daniel González

Proyecto académico desarrollado para **Desarrollo Orientado a Objetos II - Duoc UC**.
