# SpeedFast - Semana 5

Proyecto correspondiente a la actividad de la **Semana 5** de la asignatura Desarrollo Orientado a Objetos II.

El programa simula el funcionamiento de una zona de carga compartida, donde varios repartidores retiran y entregan pedidos de manera concurrente.

## Funcionamiento

El sistema permite:

- Agregar pedidos a una zona de carga.
- Ejecutar tres repartidores en paralelo.
- Retirar los pedidos de manera sincronizada.
- Evitar que un pedido sea retirado por más de un repartidor.
- Actualizar el estado del pedido durante la entrega.
- Mostrar el proceso mediante mensajes por consola.

## Estados del pedido

Los pedidos pueden tener los siguientes estados:

- `PENDIENTE`
- `EN_REPARTO`
- `ENTREGADO`

## Clases principales

- `Pedido`
- `PedidoComida`
- `PedidoEncomienda`
- `PedidoExpress`
- `EstadoPedido`
- `ZonaDeCarga`
- `Repartidor`
- `Main`

La clase `ZonaDeCarga` utiliza métodos `synchronized` para controlar el acceso de los repartidores a la lista compartida de pedidos.

## Herramientas utilizadas

- Java
- IntelliJ IDEA
- ExecutorService
- Runnable
- Git y GitHub

---

## Autor

Daniel González