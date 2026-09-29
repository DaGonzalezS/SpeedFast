package vista;

import dao.RepartidorDAO;
import model.Repartidor;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroRepartidor extends JFrame {

    private final JTextField txtNombre = new JTextField();

    public VentanaRegistroRepartidor() {
        setTitle("Registrar repartidor");
        setSize(380, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");

        panel.add(btnGuardar);
        panel.add(btnLimpiar);

        btnGuardar.addActionListener(e -> guardar());
        btnLimpiar.addActionListener(e -> txtNombre.setText(""));

        add(panel);
        setVisible(true);
    }

    private void guardar() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre.");
            return;
        }

        boolean guardado =
                new RepartidorDAO().guardar(new Repartidor(nombre));

        if (guardado) {
            JOptionPane.showMessageDialog(
                    this, "Repartidor guardado correctamente.");
            txtNombre.setText("");
        } else {
            JOptionPane.showMessageDialog(
                    this, "No se pudo guardar el repartidor.");
        }
    }
}