package model;

public class PedidoComida extends Pedido {

    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (15 + (2 * getDistanciaKm()));
    }

    @Override
    public void mostrarResumen() {
        System.out.println("PedidoComida #" + getIdPedido());
        System.out.println("Dirección: " + getDireccionEntrega());
        System.out.println("Distancia: " + getDistanciaKm() + " km");
    }
}