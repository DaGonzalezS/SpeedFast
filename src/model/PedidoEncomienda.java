package model;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int id, String direccion, EstadoPedido estado) { super(id, direccion, estado); }

    @Override public TipoPedido getTipo() { return TipoPedido.ENCOMIENDA; }
}
