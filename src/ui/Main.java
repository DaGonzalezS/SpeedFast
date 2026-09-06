package ui;

import model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Pedido pedido1 = new PedidoComida(
                101,
                "Av. Italia 456",
                4
        );

        Pedido pedido2 = new PedidoEncomienda(
                102,
                "Av. Santa Rosa 567",
                7
        );

        Pedido pedido3 = new PedidoExpress(
                103,
                "Av. Apoquindo 1500",
                6
        );

        Pedido pedido4 = new PedidoComida(
                104,
                "Gran Avenida 2300",
                5
        );

        Pedido pedido5 = new PedidoEncomienda(
                105,
                "Av. Grecia 880",
                3
        );

        Pedido pedido6 = new PedidoExpress(
                106,
                "Av. Providencia 1200",
                8
        );

        Repartidor camila = new Repartidor("Camila");
        Repartidor luis = new Repartidor("Luis");
        Repartidor pedro = new Repartidor("Pedro");

        camila.agregarPedido(pedido1);
        camila.agregarPedido(pedido4);

        luis.agregarPedido(pedido2);
        luis.agregarPedido(pedido5);

        pedro.agregarPedido(pedido3);
        pedro.agregarPedido(pedido6);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("=== INICIO DE ENTREGAS ===");

        executor.execute(camila);
        executor.execute(luis);
        executor.execute(pedro);

        executor.shutdown();

        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                System.out.println("La simulación excedió el tiempo máximo.");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("La ejecución principal fue interrumpida.");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println();
        System.out.println("=== TODAS LAS ENTREGAS FINALIZARON ===");
        pedido1.verHistorial();
    }
}
