<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User Detail ${id}</title>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <!-- <link rel="stylesheet" href="/css/demo.css"> -->
  </head>
  <body>
    <div class="container mt-5">
      <div class="row">
        <div class="col-12 mx-auto">
          <div class="d-flex justify-content-between">
            <h3>User detail with id = ${id}</h3>
            <a href="/admin/user/create" class="btn btn-primary">Create User</a>
          </div>

          <hr />
          <div class="card" style="width: 60%;">
            <div class="card-header">
              User Infomation
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">ID: ${id}</li>
              <li class="list-group-item">Eamil: </li>
              <li class="list-group-item">Full Name:</li>
              <li class="list-group-item">Address :</li>
              
            </ul>
          </div>
          
        </div>
      </div>
    </div>
  </body>
</html>
