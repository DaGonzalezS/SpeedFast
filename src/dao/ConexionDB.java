package dao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConexionDB {

    private ConexionDB() {}

    public static Connection conectar() throws SQLException {

        Properties config = new Properties();

        Path archivo = Path.of("db.properties");

        try (InputStream entrada = Files.newInputStream(archivo)) {
            config.load(entrada);
        } catch (IOException e) {
            throw new SQLException("No se pudo leer db.properties. Copie db.properties.example "
                    + "como db.properties en la raiz del proyecto y configure su conexion.", e);
        }

        String url = config.getProperty("db.url", "").trim();

        String usuario = config.getProperty("db.user", "").trim();

        if (!url.startsWith("jdbc:mysql:") || usuario.isEmpty()) {
            throw new SQLException("Configure una URL MySQL y un usuario en db.properties.");
        }

        return DriverManager.getConnection(url, usuario, config.getProperty("db.password", ""));
    }
}

