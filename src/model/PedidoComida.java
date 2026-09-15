package model;

public class PedidoComida extends Pedido {
    public PedidoComida(int id, String direccionEntrega, double distanciaKm) {
        super(id, direccionEntrega, distanciaKm);
    }

    @Override
    public void asignarRepartidor() {
        setRepartidor("Luis Diaz");
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (15 + (2 * getDistanciaKm()));
    }

    @Override
    public void mostrarResumen() {
        System.out.println("[Pedido Comida]");
        super.mostrarResumen();
    }
}
