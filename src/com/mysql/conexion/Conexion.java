
package com.mysql.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOSTNAME = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "Condominio";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mesi2005";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DATABASE + "?useSSL=false";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (conn != null && !conn.isClosed()) {
                JOptionPane.showMessageDialog(null, "Conexión exitosa");
                
                Statement statement = conn.createStatement();
                String createTableSQL = "CREATE TABLE IF NOT EXISTS Condominio ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "nombre VARCHAR(20),"
                        + "direccion VARCHAR(20),"
                        + "numero_casas INT,"
                        + "numero_celadores INT"
                        + ")";
                statement.executeUpdate(createTableSQL);
                statement.close();
                JOptionPane.showMessageDialog(null, "Tabla Condominio creada exitosamente");
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el driver de MySQL: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al establecer la conexión o crear la tabla: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }
}
