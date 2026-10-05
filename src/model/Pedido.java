package model;

public abstract class Pedido {
    private final int idPedido;
    private final String direccionEntrega;
    private final EstadoPedido estado;

    protected Pedido(int idPedido, String direccionEntrega, EstadoPedido estado) {
        if (idPedido < 0 || direccionEntrega == null || direccionEntrega.trim().isEmpty()
                || direccionEntrega.trim().length() > 255 || estado == null) {
            throw new IllegalArgumentException("Ingrese una direccion de 1 a 255 caracteres y un estado valido.");
        }
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega.trim();
        this.estado = estado;
    }
    public int getIdPedido() { return idPedido; }
    public String getDireccionEntrega() { return direccionEntrega; }
    public EstadoPedido getEstado() { return estado; }
    public abstract TipoPedido getTipo();

    public static Pedido crear(int id, String direccion, TipoPedido tipo, EstadoPedido estado) {
        if (tipo == null) throw new IllegalArgumentException("Seleccione un tipo de pedido.");
        return switch (tipo) {
            case COMIDA -> new PedidoComida(id, direccion, estado);
            case ENCOMIENDA -> new PedidoEncomienda(id, direccion, estado);
            case EXPRESS -> new PedidoExpress(id, direccion, estado);
        };
    }

    @Override public String toString() { return idPedido + " - " + direccionEntrega; }
}
