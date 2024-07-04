<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Đơn hàng - Book Store</title>
    <link rel="stylesheet" href="${BASE_URL}/static/user/css/responsive.css">
    <link rel="stylesheet" href="${BASE_URL}/static/user/css/orderdetail.css">
</head>

<body>
	    
    <div class="order-details">
        <h2 class="order-details__heading">Thông tin đơn hàng</h2>

        <div class="customer px-3 px-md-5">
            <h4>Người nhận: <span>${ order.customerName }</span></h4>
            <h4>Số điện thoại: <span>${ order.customerPhone }</span></h4>
            <h4>Địa chỉ nhận hàng: <span>${ order.address }</span></h4>
            <h4>Ngày đặt hàng: <span class="d-inline-block ms-0 ms-md-2"><fmt:formatDate value="${order.createdDate}" pattern="dd/MM/yyyy HH:mm:ss" /></span></h4>
            <h4>Ngày giao hàng dự kiến: <span class="ms-0 ms-md-2"><fmt:formatDate value="${ order.estimatedArrival }" pattern="dd/MM/yyyy" /></span></h4>
        </div>

        <div class="list-product px-3 px-md-5">
            <h4 class="list-product__heading">Danh sách sản phẩm:</h4>
            
           	<c:forEach var="orderProduct" items="${ orderProducts }">
           		<a class="product" href="${BASE_URL}/product?id=${ orderProduct.first.id }">
                    <img class="product__img" src="${BASE_URL}/${ mediaService.getMediaById(orderProduct.first.thumbnailId).getPath() }">
                    
                    <div class="product__info">
                        <h4 class="info__heading">${ orderProduct.first.title }</h4>
                        
                        <div class="info__body">
                            <div class="info__body-price">
                                <del>${ orderProduct.first.getSellPrice() }đ</del>
                                ${ orderProduct.first.price }đ
                            </div>
                            
                            <div class="total-price">
                                <div class="total-price__quantity">x${ orderProduct.second }</div>
                                <div class="total-price__total">${ orderProduct.first.getSellPrice() * orderProduct.second }đ</div>
                            </div>
                        </div>
                    </div>
                </a>
           	</c:forEach>
        </div>

        <div class="order-details__controls align-items-center justify-content-between">
            <div class="order-details__bill px-0 px-sm-3 px-md-5">
                Tổng thanh toán: <span>${ order.totalPayment }đ</span>
            </div>
            
            <div class="order-details__status pe-0 pe-sm-3 pe-md-5">
                <label style="color: #ff0000">Chưa thanh toán</label>
            </div>
        </div>
    </div>

</body>