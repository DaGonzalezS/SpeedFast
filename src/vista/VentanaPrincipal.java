package vista;

import data.ZonaDeCarga;
import model.Repartidor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VentanaPrincipal extends JFrame {
    private final ZonaDeCarga zonaDeCarga;
    private final JButton btnIniciarEntregas;

    public VentanaPrincipal() {
        zonaDeCarga = new ZonaDeCarga();

        setTitle("SpeedFast");
        setSize(420, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblTitulo = new JLabel("SISTEMA SPEEDFAST");
        lblTitulo.setBounds(140, 25, 180, 25);
        add(lblTitulo);

        JButton btnRegistrar = new JButton("Registrar pedido");
        btnRegistrar.setBounds(105, 80, 200, 40);
        btnRegistrar.addActionListener(e ->
                new VentanaRegistroPedido(zonaDeCarga));
        add(btnRegistrar);

        JButton btnListar = new JButton("Listar pedidos");
        btnListar.setBounds(105, 135, 200, 40);
        btnListar.addActionListener(e ->
                new VentanaListaPedidos(zonaDeCarga));
        add(btnListar);

        btnIniciarEntregas = new JButton("Iniciar entregas");
        btnIniciarEntregas.setBounds(105, 190, 200, 40);
        btnIniciarEntregas.addActionListener(e -> iniciarEntregas());
        add(btnIniciarEntregas);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(105, 245, 200, 40);
        btnSalir.addActionListener(e -> System.exit(0));
        add(btnSalir);

        setVisible(true);
    }

    private void iniciarEntregas() {
        if (zonaDeCarga.estaVacia()) {
            JOptionPane.showMessageDialog(this,
                    "No existen pedidos registrados.",
                    "Sin pedidos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!zonaDeCarga.hayPedidosPendientes()) {
            JOptionPane.showMessageDialog(this,
                    "No quedan pedidos pendientes.",
                    "Entregas finalizadas",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        btnIniciarEntregas.setEnabled(false);

        Repartidor camila = new Repartidor("Camila", zonaDeCarga);
        Repartidor luis = new Repartidor("Luis", zonaDeCarga);
        Repartidor pedro = new Repartidor("Pedro", zonaDeCarga);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(camila);
        executor.execute(luis);
        executor.execute(pedro);
        executor.shutdown();

        JOptionPane.showMessageDialog(this,
                "Los repartidores comenzaron las entregas.",
                "Entregas iniciadas",
                JOptionPane.INFORMATION_MESSAGE);

        Thread espera = new Thread(() -> esperarFinalizacion(executor));
        espera.start();
    }

    private void esperarFinalizacion(ExecutorService executor) {
        try {
            boolean finalizaron = executor.awaitTermination(1, TimeUnit.MINUTES);

            SwingUtilities.invokeLater(() -> {
                btnIniciarEntregas.setEnabled(true);

                if (finalizaron) {
                    JOptionPane.showMessageDialog(this,
                            "Todos los pedidos fueron entregados.",
                            "Proceso finalizado",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    executor.shutdownNow();
                    JOptionPane.showMessageDialog(this,
                            "Las entregas excedieron el tiempo maximo.",
                            "Tiempo agotado",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();

            SwingUtilities.invokeLater(() -> {
                btnIniciarEntregas.setEnabled(true);
                JOptionPane.showMessageDialog(this,
                        "El proceso de entregas fue interrumpido.",
                        "Proceso interrumpido",
                        JOptionPane.ERROR_MESSAGE);
            });
        }
    }
}