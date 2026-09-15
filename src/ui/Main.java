package ui;

import data.ZonaDeCarga;
import model.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        Pedido pedido1 = new PedidoComida(101, "Av. Italia 456", 4);
        Pedido pedido2 = new PedidoEncomienda(102, "Av. Santa Rosa 567", 7);
        Pedido pedido3 = new PedidoExpress(103, "Av. Apoquindo 1500", 6);
        Pedido pedido4 = new PedidoComida(104, "Gran Avenida 2300", 5);
        Pedido pedido5 = new PedidoEncomienda(105, "Av. Grecia 880", 3);
        Pedido pedido6 = new PedidoExpress(106, "Av. Providencia 1200", 8);

        zonaDeCarga.agregarPedido(pedido1);
        zonaDeCarga.agregarPedido(pedido2);
        zonaDeCarga.agregarPedido(pedido3);
        zonaDeCarga.agregarPedido(pedido4);
        zonaDeCarga.agregarPedido(pedido5);
        zonaDeCarga.agregarPedido(pedido6);

        Repartidor camila = new Repartidor("Camila", zonaDeCarga);
        Repartidor luis = new Repartidor("Luis", zonaDeCarga);
        Repartidor pedro = new Repartidor("Pedro", zonaDeCarga);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("\n=== INICIO DE ENTREGAS ===\n");

        executor.execute(camila);
        executor.execute(luis);
        executor.execute(pedro);
        executor.shutdown();

        try {
            boolean finalizaron = executor.awaitTermination(1, TimeUnit.MINUTES);

            if (!finalizaron) {
                System.out.println("La simulacion excedio el tiempo maximo.");
                executor.shutdownNow();
                return;
            }
        } catch (InterruptedException e) {
            System.out.println("La ejecucion principal fue interrumpida.");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            return;
        }

        if (zonaDeCarga.estaVacia()) {
            System.out.println("\nTodos los pedidos han sido entregados correctamente");
        }

        System.out.println();
        pedido1.verHistorial();
    }
}
