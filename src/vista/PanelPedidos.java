package vista;

import dao.PedidoDAO;
import model.*;
import javax.swing.*;

public class PanelPedidos extends PanelCRUD<Pedido> {

    private final JTextField direccion = new JTextField();
    private final JComboBox<TipoPedido> tipo = new JComboBox<>(TipoPedido.values());
    private final JComboBox<EstadoPedido> estado = new JComboBox<>(EstadoPedido.values());

    public PanelPedidos(Runnable refrescar) {
        super(new PedidoDAO(), refrescar, "ID", "Direccion", "Tipo", "Estado");
        campo("Direccion:", direccion); campo("Tipo:", tipo); campo("Estado:", estado);
    }

    @Override protected int id(Pedido p) { return p.getIdPedido(); }

    @Override protected Object[] fila(Pedido p) {
        return new Object[]{p.getIdPedido(), p.getDireccionEntrega(), p.getTipo(), p.getEstado()};
    }

    @Override protected Pedido leer(int id) {
        return Pedido.crear(id, direccion.getText(), (TipoPedido) tipo.getSelectedItem(),
                (EstadoPedido) estado.getSelectedItem());
    }

    @Override protected void mostrar(Pedido p) {
        direccion.setText(p.getDireccionEntrega()); tipo.setSelectedItem(p.getTipo()); estado.setSelectedItem(p.getEstado());
    }

    @Override protected void vaciar() { direccion.setText(""); tipo.setSelectedIndex(0); estado.setSelectedIndex(0); }
}
