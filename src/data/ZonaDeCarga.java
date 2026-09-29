package data;

import model.EstadoPedido;
import model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCarga {

    private final List<Pedido> pedidos;

    public ZonaDeCarga() {
        pedidos = new ArrayList<>();
    }

    public synchronized void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public synchronized List<Pedido> getPedidos() {
        return new ArrayList<>(pedidos);
    }

    public synchronized Pedido retirarPedido() {
        for (Pedido pedido : pedidos) {
            if (pedido.getEstado() == EstadoPedido.PENDIENTE) {
                pedido.setEstado(EstadoPedido.EN_REPARTO);
                return pedido;
            }
        }

        return null;
    }

    public synchronized boolean eliminarPedido(int idPedido) {
        return pedidos.removeIf(pedido -> pedido.getIdPedido() == idPedido);
    }

    public synchronized void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en la zona de carga.");
            return;
        }

        for (Pedido pedido : pedidos) {
            pedido.mostrarResumen();
            System.out.println();
        }
    }
}