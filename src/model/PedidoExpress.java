package model;

public class PedidoExpress extends Pedido {

    public PedidoExpress(int id, String direccion, EstadoPedido estado) { super(id, direccion, estado); }

    @Override public TipoPedido getTipo() { return TipoPedido.EXPRESS; }
}

