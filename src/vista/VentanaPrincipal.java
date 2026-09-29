package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("SpeedFast - Semana 7");
        setSize(500, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel titulo = new JLabel("SPEEDFAST", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JButton btnPedido = new JButton("Registrar pedido");
        JButton btnRepartidor = new JButton("Registrar repartidor");
        JButton btnLista = new JButton("Listar pedidos");
        JButton btnRepartidores = new JButton("Listar repartidores");

        btnPedido.addActionListener(e -> new VentanaRegistroPedido());
        btnRepartidor.addActionListener(e -> new VentanaRegistroRepartidor());
        btnLista.addActionListener(e -> new VentanaListaPedidos());
        btnRepartidores.addActionListener(e -> new VentanaListaRepartidores());

        panel.add(titulo);
        panel.add(btnPedido);
        panel.add(btnRepartidor);
        panel.add(btnLista);
        panel.add(btnRepartidores);

        add(panel);
        setVisible(true);
    }
}
