package vista;

import javax.swing.JOptionPane;
import java.awt.Component;
import java.sql.SQLException;

public class Mensajes {

    private Mensajes() {}
    public static void error(Component padre, SQLException error) {
        String texto;
        if (error.getErrorCode() == 1451) {
            texto = "Este pedido o repartidor tiene entregas asociadas. Elimine o reasigne esas entregas primero.";
        } else if (error.getErrorCode() == 1452) {
            texto = "El pedido o repartidor ya no existe. Actualice los datos y vuelva a seleccionarlo.";
        } else if (error.getErrorCode() == 1045) {
            texto = "MySQL rechazo el usuario o la contrasena. Revise db.properties.";
        } else if (error.getErrorCode() == 1049 || error.getErrorCode() == 1146) {
            texto = "No se encontro la base o alguna tabla. Ejecute sql/speedfast_db.sql y revise db.properties.";
        } else if (error.getSQLState() != null && error.getSQLState().startsWith("08")) {
            texto = "No se pudo conectar con MySQL. Revise que el servidor este encendido y la URL sea correcta.";
        } else {
            texto = "No se pudo completar la operacion: " + error.getMessage();
        }
        JOptionPane.showMessageDialog(padre, texto, "Error de base de datos", JOptionPane.ERROR_MESSAGE);
    }
}