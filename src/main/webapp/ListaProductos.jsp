<%-- 
    Document   : ListaProductos
    Created on : 15/11/2021, 04:38:25 PM
    Author     : Waldo
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>ADMINISTRADOR</title>
        <link rel="shortcut icon" href="css/img/icono-web.png">
        <style>
      body{
      background: url("css/img/fondo.jpg");
      background-repeat: no-repeat;
      background-size: 100%;
    }
    .img-fluid{
        border: 1px solid red;
        border-radius: 50%;
        background: black;
    }
    p{
      font-family: Courier New;
      font-weight: 900;
      font-size: 30px;
      margin-left: 40%;
      color: red;
    }
    .container-fluid{
      width: 90%;
      margin: auto;
      margin-top: 30px;
      margin-bottom: 30px;
    }
      #tabla-prueba{
        width: 90%;
        margin: auto;
        margin-top: 30px;
        margin-bottom: 30px;
      }
      footer{
        color: red;
      }
    </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#"><img class="img-fluid" width="100px" height="100px" src="css/img/admin.png"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Inicio <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Notificaciones</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Ajustes
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Cambiar fondo</a>
          <a class="dropdown-item" href="#">Cambiar presentación</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Cambiar datos</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="formulario.html">Cerrar sesión</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Buscar" aria-label="Search">
      <button id="boton" class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
    </form>
  </div>
</nav>


        <div class="container-fluid">
<div class="row mt-5">
<div class="col-lg colmd col">
<div class="card">
<div class="card-header bg-dark bg-gradient">
                            <p><u>TABLA PRODUCTOS</u></p>
                        </div>
                            <table id="tabla-prueba" class="table table-hover table-dark">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">CÓDIGO</th>
                                        <th scope="col">NOMBRE</th>
                                        <th scope="col">MARCA</th>
                                        <th scope="col">CATEGORÍA</th>
                                        <th scope="col">CANTIDAD</th>
                                        <th scope="col">PRECIO</th>
                                        <th scope="col">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach  var="producto" items="${listaProductos}">
                                        <tr class="table-primary">
                                            <th class="text-dark" scope="row"><c:out value="${producto.id}"/></th>
                                            <td><c:out value="${producto.codigo}"/></td>
                                            <td><c:out value="${producto.nombre}"/></td>
                                            <td><c:out value="${producto.marca}"/></td>
                                            <td><c:out value="${producto.categoria}"/></td>
                                            <td><c:out value="${producto.cantidadDisponible}"/></td>
                                            <td>S/<c:out value="${producto.precio}"/></td>
                                            <td>
                                                <a href="ControladorServlet?action=editar&id=<c:out value='${producto.id}'/>">Editar</a>
                                                &nbsp;&nbsp;&nbsp;&nbsp;
                                                <a href="ControladorServlet?action=eliminar&id=<c:out value ='${producto.id}'/>">Eliminar</a>
                                            </td>
                                        </tr>  
                                    </c:forEach>
                                </tbody>

                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Footer-->
  <footer id="sticky-footer" class="flex-shrink-0 py-4 bg-dark text-white-50">
      <div class="container text-center">
        <small>Copyright &copy; EMPRESA L.J.</small>
      </div>
    </footer>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>