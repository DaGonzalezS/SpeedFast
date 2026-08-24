package ui;

import model.*;

public class Main {

    public static void main(String[] args) {

        Pedido pedido1 = new PedidoComida(
                1,
                "Av. Italia 456",
                4
        );

        Pedido pedido2 = new PedidoEncomienda(
                2,
                "Av. Independencia 123",
                6
        );

        Pedido pedido3 = new PedidoExpress(
                3,
                "Av. Apoquindo 1500",
                7
        );

        pedido1.mostrarResumen();
        System.out.println("Tiempo estimado de entrega: "
                + pedido1.calcularTiempoEntrega() + " minutos");

        System.out.println();

        pedido2.mostrarResumen();
        System.out.println("Tiempo estimado de entrega: "
                + pedido2.calcularTiempoEntrega() + " minutos");

        System.out.println();

        pedido3.mostrarResumen();
        System.out.println("Tiempo estimado de entrega: "
                + pedido3.calcularTiempoEntrega() + " minutos");
    }
}