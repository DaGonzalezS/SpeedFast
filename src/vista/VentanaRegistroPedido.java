package vista;

import data.ZonaDeCarga;
import model.Pedido;
import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaRegistroPedido extends JFrame {
    private final ZonaDeCarga zonaDeCarga;
    private final JTextField txtId;
    private final JTextField txtDireccion;
    private final JTextField txtDistancia;
    private final JComboBox<String> cmbTipo;

    public VentanaRegistroPedido(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;

        setTitle("Registrar pedido");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblTitulo = new JLabel("REGISTRO DE PEDIDOS");
        lblTitulo.setBounds(145, 20, 200, 25);
        add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(70, 70, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(180, 70, 180, 25);
        add(txtId);

        JLabel lblDireccion = new JLabel("Direccion:");
        lblDireccion.setBounds(70, 110, 100, 25);
        add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(180, 110, 180, 25);
        add(txtDireccion);

        JLabel lblDistancia = new JLabel("Distancia (km):");
        lblDistancia.setBounds(70, 150, 110, 25);
        add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(180, 150, 180, 25);
        add(txtDistancia);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(70, 190, 100, 25);
        add(lblTipo);

        cmbTipo = new JComboBox<>(new String[]{"Comida", "Encomienda", "Express"});
        cmbTipo.setBounds(180, 190, 180, 25);
        add(cmbTipo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(95, 245, 110, 30);
        btnGuardar.addActionListener(e -> guardarPedido());
        add(btnGuardar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(230, 245, 110, 30);
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar);

        setVisible(true);
    }

    private void guardarPedido() {
        String textoId = txtId.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String textoDistancia = txtDistancia.getText().trim();

        if (textoId.isEmpty() || direccion.isEmpty() || textoDistancia.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe completar todos los campos.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(textoId);
            double distancia = Double.parseDouble(textoDistancia.replace(',', '.'));

            if (id <= 0) {
                JOptionPane.showMessageDialog(this,
                        "El ID debe ser mayor que cero.",
                        "ID no valido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (distancia <= 0) {
                JOptionPane.showMessageDialog(this,
                        "La distancia debe ser mayor que cero.",
                        "Distancia no valida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (zonaDeCarga.existePedido(id)) {
                JOptionPane.showMessageDialog(this,
                        "Ya existe un pedido con ese ID.",
                        "ID repetido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String tipo = (String) cmbTipo.getSelectedItem();
            Pedido pedido;

            if ("Comida".equals(tipo)) {
                pedido = new PedidoComida(id, direccion, distancia);
            } else if ("Encomienda".equals(tipo)) {
                pedido = new PedidoEncomienda(id, direccion, distancia);
            } else {
                pedido = new PedidoExpress(id, direccion, distancia);
            }

            zonaDeCarga.agregarPedido(pedido);

            JOptionPane.showMessageDialog(this,
                    "Pedido registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "El ID y la distancia deben ser valores numericos.",
                    "Datos no validos",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtDireccion.setText("");
        txtDistancia.setText("");
        cmbTipo.setSelectedIndex(0);
        txtId.requestFocus();
    }
}
