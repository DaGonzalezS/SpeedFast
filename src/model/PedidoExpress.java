package model;

public class PedidoExpress extends Pedido {
    public PedidoExpress(int id, String direccionEntrega, double distanciaKm) {
        super(id, direccionEntrega, distanciaKm);
    }

    @Override
    public void asignarRepartidor() {
        setRepartidor("Carlos Soto");
    }

    @Override
    public int calcularTiempoEntrega() {
        return getDistanciaKm() > 5 ? 15 : 10;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("[Pedido Express]");
        super.mostrarResumen();
    }
}

