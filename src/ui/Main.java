package ui;
import model.*;

public class Main {
    public static void main(String[] args) {
        Pedido pedido1 = new PedidoComida(101, "Av. Pajaritos 1234");
        Pedido pedido2 = new PedidoEncomienda(102, "Av. Italia 567");
        Pedido pedido3 = new PedidoExpress(103, "Plaza de Armas 890");

        System.out.println("=== PRUEBA DE MÉTODOS SOBRESCRITOS (SIN PARÁMETROS) ===");
        pedido1.asignarRepartidor();
        System.out.println();
        pedido2.asignarRepartidor();
        System.out.println();
        pedido3.asignarRepartidor();
        System.out.println();

        System.out.println("=== PRUEBA DE MÉTODOS SOBRECARGADOS (CON NOMBRE) ===");
        pedido1.asignarRepartidor("Juan Pérez");
        System.out.println();
        pedido2.asignarRepartidor("Camila Soto");
        System.out.println();
        pedido3.asignarRepartidor("Luis Díaz");
    }
}