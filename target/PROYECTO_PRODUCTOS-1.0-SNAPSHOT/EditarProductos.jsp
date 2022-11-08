<%-- 
    Document   : EditarProductos
    Created on : 16/11/2021, 12:34:48 PM
    Author     : Waldo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar</title>
        <link rel="stylesheet" href="css/estilos1.css">
        <link rel="shortcut icon" href="css/img/icono-web.png">
    </head>
    <body>
    <center>
        <h1 class="h1-titulo">Gestion de Productos</h1>
        <h2 class="h2-subtitulo">
            <a href="ControladorServlet?action=listar">Mostrar todos los productos</a>
            &nbsp;&nbsp;&nbsp;
            <a href="ControladorServlet?action=nuevo">Crear un nuevo producto</a>
        </h2>
    </center>
    
    <div class="form-register">
        <c:if test="${productodelafila != null}">
            <form action="ControladorServlet?action=actualizar&id=<c:out value='${productodelafila.id}'></c:out>" method="post">
        </c:if>
                    
        <c:if test="${productodelafila == null}">
                <form action="ControladorServlet?action=insertar" method="post">
        </c:if>
     
                    <caption>
                        <h4>
                            <c:if test="${productodelafila != null}">Editar</c:if>
                            <c:if test="${productodelafila == null}">Crear</c:if>
                        </h4>
                    </caption> 
                        <c:if test="${productodelafila != null}">
                            <input type="hidden" name="id" value="<c:out value='${productodelafila.id}' ></c:out>"/>
                        </c:if>
                            
                            <input class="controls" type="text" name="codigo" placeholder="Código producto" size="45" value="<c:out value='${productodelafila.codigo}'></c:out>" required=""/>

                            <input class="controls" type="text" name="nombre" placeholder="Nombre producto" size="45" value="<c:out value='${productodelafila.nombre}'></c:out>" required=""/>

                            <input class="controls" type="text" name="marca" placeholder="Marca producto" size="45" value="<c:out value='${productodelafila.marca}'></c:out>" required=""/>

                            <input class="controls" type="text" name="categoria" placeholder="Categoría producto" size="45" value="<c:out value='${productodelafila.categoria}'></c:out>" required=""/>

                            <input class="controls" type="text" name="cantidadDisponible" placeholder="Cantidad producto" size="45" value="<c:out value='${productodelafila.cantidadDisponible}'></c:out>" required=""/>




                            <input class="controls" type="text" name="precio" placeholder="Precio producto" size="45" value="<c:out value='${productodelafila.precio}'></c:out>" required=""/>

                        <input class="botons" type="submit" value="Guardar" />
                </form>
        </div>
    </body>
</html>