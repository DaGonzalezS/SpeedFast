package model;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.round(20 + (1.5 * getDistanciaKm()));
    }

    @Override
    public void mostrarResumen() {
        System.out.println("PedidoEncomienda #" + getIdPedido());
        System.out.println("Dirección: " + getDireccionEntrega());
        System.out.println("Distancia: " + getDistanciaKm() + " km");
    }
}