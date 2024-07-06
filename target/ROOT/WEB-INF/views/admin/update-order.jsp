<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Cập nhật đơn hàng | Admin</title>
	<link rel="stylesheet" href="${BASE_URL}/static/admin/css/book.css">
</head>

<body>
	<h4 class="p-3 ps-lg-0 fs-4 text-light fw-semibold">Quản lý đơn hàng</h4>

     <div class="form-add-book mt-4 px-3 px-lg-4 pb-5 rounded">
         <h5 class="mb-4 pt-4 text-center text-md-start">Cập nhật trạng thái đơn hàng</h5>

         <div class="row mt-5">
             <div class="col-12 col-lg-8 col-xxl-6 mb-3">
                 <label for="status" class="form-label fs-6 fw-medium opacity-75">Trạng thái đơn hàng</label>
                 <input type="text" class="form-control" id="status" name="status" value="${ order.orderStatus }">
             </div>
         </div>

         <div class="row mt-3">
             <div class="col-12 col-lg-8 col-xxl-6 mb-3">
                 <label for="shipper" class="form-label fs-6 fw-medium opacity-75">Số điện thoại shipper</label>
                 <input type="text" class="form-control" id="shipper" name="shipper" value="${ order.shipperPhone }">
             </div>
         </div>

         <div class="row mt-3 mb-5">
             <div class="col-12 col-lg-8 col-xxl-6 mb-4">
                 <label for="estimatedDate" class="form-label fs-6 fw-medium opacity-75">Ngày giao hàng dự kiến</label>
                 <input type="date" class="form-control" id="estimatedDate" value="${ order.estimatedArrival }">
             </div>
         </div>
         
         <div class="row mt-3 mb-5">
             <button id="deliveried-at-btn" data-id="${ order.id }" class="btn btn-primary ms-3" style="width: fit-content;">Đã giao hàng</button>
         </div>

         <div class="row justify-content-center justify-content-md-start">
             <div class="col-auto">
                 <button data-id="${ order.id }" type="button" id="update-order-btn" class="btn btn-primary fw-medium px-4 w-auto">Xác nhận</button>
                 <button type="button" id="cancell-order" class="btn btn-danger fw-medium px-4 w-auto">Hủy</button>
             </div>    
         </div>
     </div>
     
     <script type="text/javascript" src="${BASE_URL}/static/admin/js/update-order.js"></script>
</body>