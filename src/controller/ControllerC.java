/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.mysql.conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerC {

    private final Conexion conexion;

    public ControllerC() throws SQLException {
        this.conexion = new Conexion();
    }

    public void createCondominio(int id, String nombre, String direccion, int numero_casas, int numero_celadores) throws SQLException {
        String createSQL = "INSERT INTO Condominio(id, nombre, direccion, numero_casas, numero_celadores) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = conexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(createSQL)) {
            statement.setInt(1, id);
            statement.setString(2, nombre);
            statement.setString(3, direccion);
            statement.setInt(4, numero_casas);
            statement.setInt(5, numero_celadores);
            
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar los datos");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al realizar la inserción en la base de datos");
            e.printStackTrace();
        }
    }

    public void readCondominio() throws SQLException {
        String readSQL = "SELECT * FROM Condominio";
        try (Connection conn = conexion.getConnection();
             PreparedStatement statement = conn.prepareStatement(readSQL)) {
             ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + ", Dirección: " + rs.getString("direccion") + ", numero de casas: " + rs.getInt("numero_casas") + ", numero de celadores: " + rs.getInt("numero_celadores"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

}