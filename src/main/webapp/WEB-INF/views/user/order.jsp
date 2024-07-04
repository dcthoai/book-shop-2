<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<head>
    <title>Đơn hàng - Book Store</title>
    <link rel="stylesheet" href="${BASE_URL}/static/user/css/order.css">
</head>
<body>

    <div class="your-order">
        <h2 class="your-order__heading">Đơn hàng của bạn</h2>
            
        <div class="your-order__content">
            <div class="list-order">
            	<c:forEach var="order" items="${ orders }">
            		<c:set var="book" value="${ orderService.getFirstProduct(order.id) }"/>
            		<div class="order">
	                    <a class="order-demo" href="${BASE_URL}/user/order?id=${ order.id }">
	                        <div class="order-demo__img">
	                            <img src="${BASE_URL}/${ mediaService.getMediaById(book.thumbnailId).getPath() }" alt="image-product">
	                        </div>
	
	                        <div class="order-demo__info">
	                            <h4 class="info__heading">${ book.title }</h4>
	                            
	                            <div class="info__body">
	                                <div class="info__body-price">
	                                    <del>${ book.getSellPrice() }</del>
	                                    ${ book.price }đ
	                                </div>
	                                
	                                <div class="info__body-total">Nhấn để xem chi tiết</div>
	                            </div>
	                        </div>
	                    </a>
	
	                    <div class="order-demo__status d-none d-md-flex">
	                        <label style="color: #ff7600">${ order.orderStatus }</label>
	                    </div>
	                </div>
            	</c:forEach>
            </div>
        </div>
    </div>

</body>