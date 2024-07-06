<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Đơn hàng | Admin</title>
</head>

<body>
	<h4 class="p-3 ps-lg-0 fs-4 text-light fw-semibold">Quản lý đơn hàng của người dùng</h4>
	
	<div class="w-100 d-flex flex-wrap justify-content-between my-4">
        <div class="d-flex form-search-book mb-4">
            <input type="text" class="flex-1 h-100 form-control form-search-input" id="search-order-input" placeholder="Nhập tên khách hàng">
            <button type="button" class="ms-2 btn btn-primary py-0 fw-medium" id="search-order-button">Tìm kiếm</button>
        </div>
    </div>

    <div class="card mb-4 w-100">
        <div class="card-header d-flex flex-wrap justify-content-between align-items-center py-3">
            <div class="mb-3 fs-5 fw-medium opacity-75"><i class="fas fa-table me-3"></i>Danh sách đơn hàng</div>
        </div>

        <div class="card-body">
            <table id="table-list-slides" class="w-100 table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="text-center">Tên khách hàng</th>
                        <th class="text-center">Địa chỉ</th>
                        <th class="text-center">Trạng thái đơn hàng</th>
                        <th class="text-center">Phí vận chuyển</th>
                        <th class="text-center">Giảm giá</th>
                        <th class="text-center">Tổng thanh toán</th>
                        <th class="text-center">Phương thức thanh toán</th>
                        <th class="text-center">Số điện thoại shipper</th>
                        <th class="text-center">Ngày đặt hàng</th>
                        <th class="text-center">Ước tính ngày giao</th>
                        <th class="text-center">Ngày giao hàng</th>
                        <th class="text-center">Chỉnh sửa</th>
                    </tr>
                </thead>
                
                <tbody id="order-container" style="text-wrap: nowrap;">
                	<c:forEach var="order" items="${ orders }">
                		<tr>
	                        <td class="" style="min-width: 10rem">${ userService.getUserById(order.userId).getFullname() }</td>
	                        <td class="">${ order.address }</td>
	                        <td class="text-center">${ order.orderStatus }</td>
	                        <td class="text-center">${ order.shippingCost }</td>
	                        <td class="text-center">${ order.discount }</td>
	                        <td class="text-center">${ order.totalPayment }</td>
	                        <td class="text-center ">${ order.paymentMethod }</td>
	                        <td class="text-center ">${ order.shipperPhone }</td>
	                        <td class="text-center ">${ order.orderDate }</td>
	                        <td class="text-center ">${ order.estimatedArrival }</td>
	                        <td class="text-center ">${ order.deliveredAt }</td>
	                        <td>
	                            <div class="w-100 h-100 d-flex justify-content-evenly">
	                                <a href="${BASE_URL}/admin/order/update?id=${ order.id }" class="flex-fill edit-book"><i class="fa-regular fa-pen-to-square"></i></a>
	                            </div>
	                        </td>
	                    </tr>
                	</c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
    <script type="text/javascript" src="${BASE_URL}/static/admin/js/order.js"></script>
</body>