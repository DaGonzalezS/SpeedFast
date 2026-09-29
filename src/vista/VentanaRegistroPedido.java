package vista;

import dao.PedidoDAO;
import model.*;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private final JTextField txtId = new JTextField();
    private final JTextField txtDireccion = new JTextField();
    private final JTextField txtDistancia = new JTextField();
    private final JComboBox<String> cbTipo =
            new JComboBox<>(new String[]{"COMIDA", "ENCOMIENDA", "EXPRESS"});

    public VentanaRegistroPedido() {
        setTitle("Registrar pedido");
        setSize(420, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("ID:"));
        panel.add(txtId);
        panel.add(new JLabel("Direccion:"));
        panel.add(txtDireccion);
        panel.add(new JLabel("Distancia (km):"));
        panel.add(txtDistancia);
        panel.add(new JLabel("Tipo:"));
        panel.add(cbTipo);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");
        panel.add(btnGuardar);
        panel.add(btnLimpiar);

        btnGuardar.addActionListener(e -> guardar());
        btnLimpiar.addActionListener(e -> limpiar());

        add(panel);
        setVisible(true);
    }

    private void guardar() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String direccion = txtDireccion.getText().trim();
            double distancia = Double.parseDouble(txtDistancia.getText().trim());
            String tipo = (String) cbTipo.getSelectedItem();

            if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una direccion.");
                return;
            }

            Pedido pedido;

            switch (tipo) {
                case "COMIDA" ->
                        pedido = new PedidoComida(id, direccion, distancia);
                case "ENCOMIENDA" ->
                        pedido = new PedidoEncomienda(id, direccion, distancia);
                default ->
                        pedido = new PedidoExpress(id, direccion, distancia);
            }

            boolean guardado = new PedidoDAO().guardar(pedido);

            if (guardado) {
                JOptionPane.showMessageDialog(
                        this, "Pedido guardado correctamente.");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(
                        this, "No se pudo guardar el pedido.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this, "ID y distancia deben ser numericos.");
        }
    }

    private void limpiar() {
        txtId.setText("");
        txtDireccion.setText("");
        txtDistancia.setText("");
        cbTipo.setSelectedIndex(0);
    }
}