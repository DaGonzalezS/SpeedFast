package vista;

import dao.PedidoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaPedidos extends JFrame {

    private final DefaultTableModel modelo;
    private final JTable tabla;

    public VentanaListaPedidos() {
        setTitle("Pedidos almacenados");
        setSize(650, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(
                new Object[]{"ID", "Direccion", "Tipo", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> cargarPedidos());

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(btnActualizar, BorderLayout.SOUTH);

        cargarPedidos();
        setVisible(true);
    }

    private void cargarPedidos() {
        modelo.setRowCount(0);

        List<PedidoDAO.PedidoRegistro> pedidos =
                new PedidoDAO().listarTodos();

        for (PedidoDAO.PedidoRegistro pedido : pedidos) {
            modelo.addRow(new Object[]{
                    pedido.getId(),
                    pedido.getDireccion(),
                    pedido.getTipo(),
                    pedido.getEstado()
            });
        }
    }
}