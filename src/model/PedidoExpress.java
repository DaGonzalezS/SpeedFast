package model;

public class PedidoExpress extends Pedido {

    public PedidoExpress(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Compra Express");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Express] Asignando repartidor...");
        System.out.println("→ Buscando repartidor más cercano con disponibilidad inmediata...");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("[Pedido Express] Asignando repartidor...");
        System.out.println("→ Repartidor más cercano con disponibilidad inmediata encontrado.");
        System.out.println("→ Pedido asignado a " + nombreRepartidor);
    }
}
