package model;

public class PedidoExpress extends Pedido {
    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public void asignarRepartidor() {
        setRepartidor("Camila Soto");
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.round(10 + (1.2 * getDistanciaKm()));
    }

    @Override
    public void mostrarResumen() {
        System.out.println("[Pedido Express]");
        super.mostrarResumen();
    }
}

