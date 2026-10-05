package vista;

import javax.swing.*;
import java.sql.SQLException;

public class VentanaPrincipal extends JFrame {

    private final PanelRepartidores repartidores;
    private final PanelPedidos pedidos;
    private final PanelEntregas entregas;

    public VentanaPrincipal() {

        setTitle("SpeedFast - Semana 8");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600); setLocationRelativeTo(null);
        repartidores = new PanelRepartidores(this::refrescar);
        pedidos = new PanelPedidos(this::refrescar);
        entregas = new PanelEntregas(this::refrescar);
        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Repartidores", repartidores); pestanas.addTab("Pedidos", pedidos); pestanas.addTab("Entregas", entregas);
        add(pestanas); setVisible(true);
        refrescar();
    }

    private void refrescar() {
        try {
            repartidores.cargar(); pedidos.cargar();
            entregas.cargarCombos(pedidos.registros, repartidores.registros);
            entregas.cargar();
        } catch (SQLException e) { Mensajes.error(this, e); }
    }
}
