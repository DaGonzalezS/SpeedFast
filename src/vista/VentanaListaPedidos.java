package vista;

import data.ZonaDeCarga;
import model.Pedido;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaListaPedidos extends JFrame {
    private final ZonaDeCarga zonaDeCarga;
    private final DefaultTableModel modeloTabla;

    public VentanaListaPedidos(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;

        setTitle("Listado de pedidos");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblTitulo = new JLabel("LISTADO DE PEDIDOS");
        lblTitulo.setBounds(325, 15, 180, 25);
        add(lblTitulo);

        String[] columnas = {
                "ID", "Tipo", "Direccion", "Distancia", "Repartidor", "Estado"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tablaPedidos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        scrollPane.setBounds(30, 55, 725, 235);
        add(scrollPane);

        JButton btnActualizar = new JButton("Actualizar tabla");
        btnActualizar.setBounds(235, 310, 150, 30);
        btnActualizar.addActionListener(e -> cargarPedidos());
        add(btnActualizar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(410, 310, 120, 30);
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar);

        cargarPedidos();
        setVisible(true);
    }

    private void cargarPedidos() {
        modeloTabla.setRowCount(0);

        for (Pedido pedido : zonaDeCarga.getPedidos()) {
            Object[] fila = {
                    pedido.getId(),
                    pedido.getTipo(),
                    pedido.getDireccionEntrega(),
                    pedido.getDistanciaKm() + " km",
                    pedido.getRepartidor(),
                    pedido.getEstado()
            };

            modeloTabla.addRow(fila);
        }
    }
}