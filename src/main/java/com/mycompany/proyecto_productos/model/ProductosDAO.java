/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_productos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Waldo
 */
public class ProductosDAO {

    ResultSet rs;
    Statement stmt;
    PreparedStatement pstmt;
    private Connection con;
    boolean actualizacionOk = false;
    boolean eliminarOk = false;
    boolean insertarOk = false;

    protected void poolConexiones() throws NamingException {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/dbjavamysql");

            con = ds.getConnection();
            //stmt = con.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Productos> listarProductos() throws NamingException, SQLException {
        List<Productos> lista = new ArrayList<>();
        String sql = "select *from productos";

        poolConexiones();
        stmt = con.createStatement();

        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id_producto");
                String codigo = rs.getString("codigo_producto");
                String nombre = rs.getString("nombre_producto");
                String marca = rs.getString("marca_producto");
                String categoria = rs.getString("categoria_producto");
                int cantidadDisponible = rs.getInt("cantidad_producto");
                Double precio = rs.getDouble("precio_producto");

                Productos producto = llenarDatosProducto(id, codigo, nombre, marca, categoria, cantidadDisponible, precio);
                lista.add(producto);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }

    private Productos llenarDatosProducto(int id, String codigo, String nombre, String marca, String categoria, int cantidadDisponible, double precio) {

        Productos producto = new Productos();
        producto.setId(id);
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setMarca(marca);
        producto.setCategoria(categoria);
        producto.setCantidadDisponible(cantidadDisponible);
        producto.setPrecio(precio);

        return producto;
    }
    
    public Productos editarProductoP(int columnaId) throws NamingException, SQLException {
        Productos producto2 = null;
        String sql = "select *from productos where id_producto = ?";
        poolConexiones();

        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, columnaId);

        rs = stm.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id_producto");
            String codigo = rs.getString("codigo_producto");
            String nombre = rs.getString("nombre_producto");
            String marca = rs.getString("marca_producto");
            String categoria = rs.getString("categoria_producto");
            int cantidadDisponible = rs.getInt("cantidad_producto");
            double precio = rs.getDouble("precio_producto");

            producto2 = llenarDatosProducto(id, codigo, nombre, marca, categoria, cantidadDisponible, precio);
        }
        return producto2;
    }
    
    public Boolean editarProductoB(int id, String codigo, String nombre, String marca, String categoria, int cantidadDisponible, double precio) throws NamingException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "update productos set codigo_producto=?, nombre_producto=?, marca_producto=?, categoria_producto=?, cantidad_producto=?, precio_producto=? where id_producto = ?";
        try {
            poolConexiones();

            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, codigo);
            pstmt.setString(2, nombre);
            pstmt.setString(3, marca);
            pstmt.setString(4, categoria);
            pstmt.setInt(5, cantidadDisponible);
            pstmt.setDouble(6, precio);
            pstmt.setInt(7, id);

            actualizacionOk = pstmt.executeUpdate() > 0;
            //Confirmar en las tablas los cambios realizados
            con.commit();

        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        return actualizacionOk;
    }
    
    public Boolean insertarProducto(String codigo, String nombre, String marca, String categoria, int cantidadDisponible, double precio) throws NamingException, SQLException{
        
        String sql = "INSERT INTO productos (codigo_producto, nombre_producto, marca_producto, categoria_producto, cantidad_producto, precio_producto) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            poolConexiones();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, codigo);
            pstmt.setString(2, nombre);
            pstmt.setString(3, marca);
            pstmt.setString(4, categoria);
            pstmt.setInt(5, cantidadDisponible);
            pstmt.setDouble(6, precio);

            insertarOk = pstmt.executeUpdate() > 0;
            con.commit();
        } catch (SQLException ex) {
            con.rollback();
        }
        
        return insertarOk;
    }
    
    public Boolean eliminarProducto(int id)throws NamingException, SQLException {
        String sql = "delete from productos where id_producto= ?";
        
        try{
            poolConexiones();

            con.setAutoCommit(false);
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            eliminarOk = pstmt.executeUpdate() > 0;
            con.commit();
        }catch(SQLException ex){
            con.rollback();
        }
        
        return eliminarOk;
    }
}
