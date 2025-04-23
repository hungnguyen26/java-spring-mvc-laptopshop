<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Laptopshop</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script
      src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
      crossorigin="anonymous"
    ></script>
  </head>
  <body class="sb-nav-fixed">
    <jsp:include page="../layout/header.jsp" />
    <div id="layoutSidenav">
      <jsp:include page="../layout/sidebar.jsp" />
      <div id="layoutSidenav_content">
        <main>
          <div class="container-fluid px-4">
            <h1 class="mt-4">Detail Orders</h1>
            <ol class="breadcrumb mb-4">
              <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
              <li class="breadcrumb-item"><a href="/order">Order</a></li>
              <li class="breadcrumb-item active">View Detail</li>
            </ol>
            <div class="container mt-5">
              <div class="row">
                <div class="col-12 mx-auto">
                  <div class="d-flex justify-content-between">
                    <h3>Order detail with id = ${id}</h3>
                  </div>
        
                  <hr />
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th scope="col">Sản phẩm</th>
                        <th scope="col">Tên</th>
                        <th scope="col">Giá cả</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Thành tiền</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach items="${orderDetails}" var="orderDetail">
                        <tr>
                          <th>
                            <img src="/images/product/${orderDetail.getProduct().getImage()}" alt="" style="width: 50px; height: 50px;">
                          </th>
                          <td>
                            <a href="/admin/product/${orderDetail.getProduct().getId()}">${orderDetail.getProduct().getName()}</a>
                          </td>
                          <td>
                            <fmt:formatNumber type="number" value="${orderDetail.getProduct().getPrice()}"/> đ
                          </td>
                          <td>
                            ${orderDetail.quantity}
                          </td>
                          <td>
                            <fmt:formatNumber type="number" value="${orderDetail.getProduct().getPrice() * orderDetail.quantity}"/> đ
                          </td>
                        </tr>
                      </c:forEach>
                    </tbody>
                  </table>
                  <a href="/admin/product" class="btn btn-success mt-3">Back</a>
                  
                </div>
              </div>
            </div>
          </div>
        </main>
        <jsp:include page="../layout/footer.jsp" />
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      crossorigin="anonymous"
    ></script>
    <script src="/js/scripts.js"></script>
  </body>
</html>
