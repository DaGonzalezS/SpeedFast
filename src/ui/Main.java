package ui;

import model.*;

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

        // ASIGNACIÓN AUTOMÁTICA
        pedido1.asignarRepartidor();
        pedido2.asignarRepartidor();

        // ASIGNACIÓN MANUAL
        pedido3.asignarRepartidor("Felipe Morales");

        System.out.println("=== PEDIDO 1 ===");
        pedido1.mostrarResumen();
        System.out.println("Tiempo estimado: "
                + pedido1.calcularTiempoEntrega()
                + " minutos");

        pedido1.despachar();

        System.out.println();

        System.out.println("=== PEDIDO 2 ===");
        pedido2.mostrarResumen();
        System.out.println("Tiempo estimado: "
                + pedido2.calcularTiempoEntrega()
                + " minutos");

        pedido2.despachar();

        System.out.println();

        System.out.println("=== PEDIDO 3 ===");
        pedido3.mostrarResumen();
        System.out.println("Tiempo estimado: "
                + pedido3.calcularTiempoEntrega()
                + " minutos");

        pedido3.cancelar();

        System.out.println();

        pedido1.verHistorial();
    }
}