/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mysql.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Prueba {
   
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.getConnection()) {
            // Verificamos si la conexión fue exitosa
            if (conn != null) {
                // Ejemplo de INSERT
                String insertSQL = "INSERT INTO Condominio (nombre, direccion, numero_casas, numero_celadores) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, "Ejemplo Condominio");
                    pstmt.setString(2, "123 Calle Principal");
                    pstmt.setInt(3, 20); // Por ejemplo, 20 casas
                    pstmt.setInt(4, 2); // Por ejemplo, 2 celadores
                    pstmt.executeUpdate();
                    System.out.println("Inserción exitosa");
                }

                // Ejemplo de UPDATE
                String updateSQL = "UPDATE Condominio SET nombre = ? WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                    pstmt.setString(1, "Nuevo Nombre Condominio");
                    pstmt.setInt(2, 1); // Actualizar el registro con ID 1
                    pstmt.executeUpdate();
                    System.out.println("Actualización exitosa");
                }

                // Ejemplo de DELETE
                String deleteSQL = "DELETE FROM Condominio WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                    pstmt.setInt(1, 1); // Eliminar el registro con ID 1
                    pstmt.executeUpdate();
                    System.out.println("Eliminación exitosa");
                }
            } else {
                System.out.println("No se pudo establecer la conexión con la base de datos");
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al realizar las operaciones en la base de datos");
            e.printStackTrace();
        }
    }
}
