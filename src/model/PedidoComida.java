package model;

public class PedidoComida extends Pedido {

    public PedidoComida(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Comida");
    }
    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Comida] Asignando repartidor...");
        System.out.println("→ Verificando mochila térmica... OK");
        System.out.println("→ Buscando repartidor disponible cercano.");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("[Pedido Comida] Asignando repartidor...");
        System.out.println("→ Verificando mochila térmica... OK");
        System.out.println("→ Pedido asignado a " + nombreRepartidor);
    }
}