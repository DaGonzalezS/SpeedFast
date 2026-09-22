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

    public synchronized boolean existePedido(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return true;
            }
        }

        return false;
    }

    public synchronized List<Pedido> getPedidos() {
        return new ArrayList<>(pedidos);
    }

    public synchronized Pedido retirarPedido() {
        for (Pedido pedido : pedidos) {
            if (pedido.estaDisponible()) {
                pedido.setEstado(EstadoPedido.EN_REPARTO);
                return pedido;
            }
        }

        return null;
    }

    public synchronized boolean hayPedidosPendientes() {
        for (Pedido pedido : pedidos) {
            if (pedido.estaDisponible()) {
                return true;
            }
        }

        return false;
    }

    public synchronized boolean estaVacia() {
        return pedidos.isEmpty();
    }
}