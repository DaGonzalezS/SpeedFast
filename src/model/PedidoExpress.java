package model;

public class PedidoExpress extends Pedido {

    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        if (getDistanciaKm() > 5) {
            return 15;
        }

        return 10;
    }

    @Override
    public void mostrarResumen() {
        System.out.println("PedidoExpress #" + getIdPedido());
        System.out.println("Dirección: " + getDireccionEntrega());
        System.out.println("Distancia: " + getDistanciaKm() + " km");
    }
}