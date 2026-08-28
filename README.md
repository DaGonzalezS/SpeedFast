# SpeedFast - Semana 3

Proyecto correspondiente a la **Semana 3** de la asignatura **Desarrollo Orientado a Objetos II**.

En esta actividad se amplió el sistema de entregas **SpeedFast**, incorporando conceptos de:

- Herencia
- Polimorfismo
- Abstracción
- Interfaces
- Sobrecarga y sobrescritura de métodos

---

## Funcionalidades implementadas

El sistema permite gestionar distintos tipos de pedidos:

- `PedidoComida`
- `PedidoEncomienda`
- `PedidoExpress`

Cada tipo de pedido posee su propia lógica para:

- Asignar repartidores.
- Calcular el tiempo estimado de entrega.
- Mostrar información del pedido.

También se implementaron operaciones de:

- Despacho de pedidos.
- Cancelación de pedidos.
- Visualización del historial de entregas.

---

## Clase abstracta

Se utilizó la clase abstracta:

```java
Pedido
```

Esta clase contiene atributos y comportamientos comunes para todos los pedidos, además del método abstracto:

```java
calcularTiempoEntrega()
```

Cada subclase implementa este método según sus propias reglas de negocio.

---

## Polimorfismo

Se aplicó polimorfismo mediante sobrescritura de métodos como:

```java
asignarRepartidor()
calcularTiempoEntrega()
mostrarResumen()
```

También se implementó sobrecarga mediante:

```java
asignarRepartidor()
```

y:

```java
asignarRepartidor(String nombre)
```

Esto permite realizar una asignación automática o manual del repartidor.

---

## Interfaces

Se implementaron las siguientes interfaces:

```text
Despachable
Cancelable
Rastreable
```

Cada una define una responsabilidad específica:

```java
despachar()
cancelar()
verHistorial()
```

---

## Estructura del proyecto

```text
src/
├── interfaces/
│   ├── Cancelable.java
│   ├── Despachable.java
│   └── Rastreable.java
│
├── model/
│   ├── Pedido.java
│   ├── PedidoComida.java
│   ├── PedidoEncomienda.java
│   └── PedidoExpress.java
│
└── ui/
    └── Main.java
```

---

## Ejecución

La clase `Main` simula distintos casos de pedidos, mostrando:

- Asignación automática de repartidores.
- Asignación manual de repartidor.
- Cálculo del tiempo estimado.
- Despacho de pedidos.
- Cancelación de pedidos.
- Historial de entregas.

---

## Tecnologías utilizadas

- Java
- IntelliJ IDEA
- Git
- GitHub

---

## Autor

Daniel González
