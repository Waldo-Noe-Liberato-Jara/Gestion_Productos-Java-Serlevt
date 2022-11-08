/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_productos.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.proyecto_productos.model.Productos;
import com.mycompany.proyecto_productos.model.ProductosDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Waldo
 */
//@WebServlet(name = "ControladorServlet", urlPatterns = {"/ControladorServlet"})
public class ControladorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        try {
            //String accion = request.getServletPath();
            String accion = request.getParameter("action");

            switch (accion) {

                case "eliminar": {
                    try {
                        eliminarProducto(request, response);
                        break;
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case "nuevo":
                    nuevoProducto(request, response);
                    break;

                case "insertar": {
                    try {
                        insertarProducto(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case "actualizar": {
                    try {
                        actualizarProducto(request, response);
                        break;
                    } catch (SQLException ex) {
                        Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                case "editar":
            
                try {
                    editarProducto(request, response);
                    break;

                } catch (SQLException ex) {
                    Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                case "listar":            
            
                try {
                    listarProductos(request, response);
                    break;
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                default:
                    break;
            }

        } catch (NamingException ex) {
            java.util.logging.Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String user = request.getParameter("usuario"), contrasena = request.getParameter("contrasena");

            if ("Waldo".equals(user) & "RD".equals(contrasena)) {
                RespuestaAdmin(response, request);
            } else if ("Diego".equals(user) & "DP".equals(contrasena)) {
                try {
                    listarProductosUsuario(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("Daniel".equals(user) & "DL".equals(contrasena)) {
                try {
                    listarProductosUsuario(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                ConsultaRealizada(response, request);
            }
        }
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void editarProducto(HttpServletRequest request, HttpServletResponse response) throws NamingException, IOException, ServletException, SQLException {

        int columnaId = Integer.parseInt(request.getParameter("id"));
        ProductosDAO productosDAO = new ProductosDAO();
        Productos filaproducto = productosDAO.editarProductoP(columnaId);

        request.setAttribute("productodelafila", filaproducto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditarProductos.jsp");
        dispatcher.forward(request, response);
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws NamingException, IOException, ServletException, SQLException {
        ProductosDAO productosDAO = new ProductosDAO();
        List<Productos> listar = productosDAO.listarProductos();

        request.setAttribute("listaProductos", listar);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListaProductos.jsp");
        dispatcher.forward(request, response);
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, NamingException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //int actualizarId = Integer.parseInt(request.getParameter("id"));
        int id = Integer.parseInt(request.getParameter("id"));
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String marca = request.getParameter("marca");
        String categoria = request.getParameter("categoria");
        int cantidadDisponible = Integer.parseInt(request.getParameter("cantidadDisponible"));
        double precio = Double.parseDouble(request.getParameter("precio"));

        ProductosDAO productosDAO = new ProductosDAO();
        Boolean bool = productosDAO.editarProductoB(id, codigo, nombre, marca, categoria, cantidadDisponible, precio);
        if (bool) {
            try {
                listarProductos(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // No se puedo editar.....?  
        }
    }

    private void nuevoProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditarProductos.jsp");
        dispatcher.forward(request, response);
    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response) throws NamingException, SQLException {
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String marca = request.getParameter("marca");
        String categoria = request.getParameter("categoria");
        int cantidadDisponible = Integer.parseInt(request.getParameter("cantidadDisponible"));
        double precio = Double.parseDouble(request.getParameter("precio"));

        ProductosDAO productosDAO = new ProductosDAO();
        Boolean bol = productosDAO.insertarProducto(codigo, nombre, marca, categoria, cantidadDisponible, precio);

        if (bol) {
            try {
                listarProductos(request, response);
            } catch (IOException ex) {
                Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServletException ex) {
                Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));

        ProductosDAO productosDAO = new ProductosDAO();

        Boolean bol = productosDAO.eliminarProducto(id);

        if (bol) {
            try {
                listarProductos(request, response);
            } catch (IOException ex) {
                Logger.getLogger(ControladorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void RespuestaAdmin(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n<html>\n<head>\n<meta charset='utf-8'>\n<meta name='viewport' content='width=device-width, initial-scale=1'>\n<title>Modificando</title>\n<link rel='stylesheet' type='text/css' href='css/modificando.css'>\n<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>\n</head>\n<body>\n<p class='p-h'>Confirmar cuenta de administrador</p>\n<a href=\"ControladorServlet?action=listar\"><input id=\"btn\" class=\"btn btn-dark\" type=\"submit\" value=\"Confirmar\"></a>\n</body>\n</html>");
    }

    private void listarProductosUsuario(HttpServletRequest request, HttpServletResponse response) throws NamingException, IOException, ServletException, SQLException {
        ProductosDAO productosDAO = new ProductosDAO();
        List<Productos> listar = productosDAO.listarProductos();

        request.setAttribute("listaProductos", listar);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListaProductosUsuario.jsp");
        dispatcher.forward(request, response);
    }
    private void ConsultaRealizada(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n<html>\n<head>\n<meta charset='utf-8'>\n<meta name='viewport' content='width=device-width, initial-scale=1'>\n<title>Modificado</title>\n<link rel='stylesheet' type='text/css' href='css/modificando.css'>\n<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css' integrity='sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm' crossorigin='anonymous'>\n</head>\n<body>\n<p class='p-h'>Cambio realizado</p>\n<a href=\"ControladorServlet?action=listar\"><input id=\"btn\" class=\"btn btn-dark\" type=\"submit\" value=\"Continuar\"></a>\n</body>\n</html>");
    }
}
