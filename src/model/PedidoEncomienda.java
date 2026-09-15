package model;

public class PedidoEncomienda extends Pedido {
    public PedidoEncomienda(int id, String direccionEntrega, double distanciaKm) {
        super(id, direccionEntrega, distanciaKm);
    }

    @Override
    public void asignarRepartidor() {
        setRepartidor("Daniela Tapia");
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.round(20 + (1.5 * getDistanciaKm()));
    }

    @Override
    public void mostrarResumen() {
        System.out.println("[Pedido Encomienda]");
        super.mostrarResumen();
    }
}
