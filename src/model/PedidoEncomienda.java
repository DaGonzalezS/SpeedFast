package model;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido, String direccionEntrega) {
        super(idPedido, direccionEntrega, "Encomienda");
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("[Pedido Encomienda] Asignando repartidor...");
        System.out.println("→ Validando peso y embalaje... OK");
        System.out.println("→ Buscando vehículo de carga adecuado.");
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("[Pedido Encomienda] Asignando repartidor...");
        System.out.println("→ Validando peso y embalaje... OK");
        System.out.println("→ Pedido asignado a " + nombreRepartidor);
    }
}
