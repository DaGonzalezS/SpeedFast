# SpeedFast - Sistema de Gestión de Pedidos

Proyecto desarrollado para la asignatura **Desarrollo Orientado a Objetos II** de Duoc UC.

## Descripción

SpeedFast es un sistema de gestión de pedidos que trabaja con distintos tipos de entregas.

En esta etapa se agregó **programación concurrente en Java**, permitiendo que varios repartidores realicen entregas al mismo tiempo.

---

## Clases principales

- `Pedido`
- `PedidoComida`
- `PedidoEncomienda`
- `PedidoExpress`
- `Repartidor`
- `Main`

También se utilizan las interfaces:

- `Despachable`
- `Cancelable`
- `Rastreable`

---

## Concurrencia

La clase `Repartidor` implementa `Runnable` y contiene una lista de pedidos asignados.

Cada repartidor ejecuta sus entregas mediante el método:

```java
run()
```

Para simular el tiempo de entrega se utiliza:

```java
Thread.sleep()
```

Los repartidores se ejecutan en paralelo utilizando:

```java
ExecutorService
```

---

## Funcionamiento

El programa:

- Crea diferentes tipos de pedidos.
- Crea tres repartidores.
- Asigna dos pedidos a cada repartidor.
- Ejecuta las entregas de forma concurrente.
- Muestra el avance de cada repartidor por consola.

---

## Conceptos utilizados

- Abstracción
- Herencia
- Polimorfismo
- Interfaces
- `Runnable`
- `Thread.sleep()`
- `ExecutorService`
- Manejo de excepciones

---

## Tecnologías

- Java
- IntelliJ IDEA
- Git
- GitHub

---

## Autor

Daniel González