<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Thanh toán - Book Store</title>
    <link rel="stylesheet" href="${BASE_URL}/static/user/css/payment.css">
    <link rel="stylesheet" href="${BASE_URL}/static/user/css/responsive.css">
</head>

<body>
	<div class="payment">
        <div class="payment__header">
            <span>Đơn hàng của bạn</span>
        </div>

        <div class="payment__info">
            <div class="wrapper">
                <span>Họ tên</span>
                <input name="payment-name" type="text" class="info__input" value="${ user.fullname }" placeholder="Nhập họ tên người nhận">
            </div>
            <div class="wrapper">
                <span>Số điện thoại</span>
                <input name="payment-phone" type="text" class="info__input" value="${ user.phone }" placeholder="Nhập số điện thoại người nhận">
            </div>
            <div class="wrapper">
                <span>Địa chỉ</span>
                <input name="payment-address" type="text" class="info__input" value="${ user.address }" placeholder="Nhập địa chỉ người nhận">
            </div>
        </div>

        <div class="space"></div>

        <div class="payment__method">
            <div class="payment__method-heading">
                Chọn phương thức thanh toán của bạn:
            </div>

            <div>
                <div class="payment__method-choice">
                    <input type="radio" name="payment-method" disabled value="zalo-pay">
                    <div class="des">
                        <div class="des__icon">
                            <img src="${BASE_URL}/static/user/images/zalopay.jpg" alt="ZaloPay">
                        </div>
                        <span class="des__heading">Ví ZaloPay</span>
                    </div>
                </div>

                <div class="payment__method-choice">
                    <input type="radio" name="payment-method" disabled value="momo-pay">
                    <div class="des">
                        <div class="des__icon">
                            <img src="${BASE_URL}/static/user/images/momopay.jpg" alt="MomoPay">                    
                        </div>
                        <span class="des__heading">Ví Momo</span>
                    </div>
                </div>

                <div class="payment__method-choice">
                    <input type="radio" name="payment-method" disabled value="banking-pay">
                    <div class="des">
                        <div class="des__icon">
                            <img src="${BASE_URL}/static/user/images/atmpay.jpg" alt="ATMPay">
                        </div>
                        <span class="des__heading">ATM/Internet Banking</span>
                    </div>
                </div>

                <div class="payment__method-choice">
                    <input type="radio" name="payment-method" checked value="cash-pay">
                    <div class="des">
                        <div class="des__icon">
                            <img src="${BASE_URL}/static/user/images/cashpay.jpg" alt="CashPay">
                        </div>
                        <span class="des__heading">Thanh toán bằng tiền mặt khi nhận hàng</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="space"></div>

        <div class="payment__content">
            <h4>Xem lại sản phẩm của bạn:</h4>
            <div class="list__product">
            
            	<c:forEach var="orderProduct" items="${ orderProducts }">
            		<c:set var="book" value="${ bookService.getBookById(orderProduct.bookId) }"/>
            		
            		<div class="product">
	                    <div class="product__img">
	                        <a href="${BASE_URL}/product?id=${ book.id }"><img src="${BASE_URL}/${ mediaService.getMediaById(book.thumbnailId).getPath() }" alt="image-product"></a>
	                    </div>
	
	                    <div class="product__info">
	                        <div class="description">
	                            <div class="description__heading"><a href="${BASE_URL}/product?id=${ book.id }">${ book.title }</a></div>
	                            <div class="description__price">
	                                ${ book.getSellPrice() }đ 
	                                <del>${ book.price }đ</del>
	                            </div>
	                        </div>
	                        
	                        <div class="price">
	                            <div class="price__quantity">x${ orderProduct.quantity }</div>
	                            <div class="price__total">${ orderProduct.quantity * book.getSellPrice() }đ</div>
	                        </div>
	                    </div>
	                </div>
            	</c:forEach>
                
            </div>
        </div>
        
        <div class="payment__controls">
            <div style="max-width: 960px; margin: 0 auto;" class="d-flex flex-column flex-sm-row align-items-center justify-content-center d-md-block px-5 px-lg-0">
                <div class="ps-0 pe-4 wrapper">
                    <div class="payment__controls-back d-none d-md-block">
                        <a href="${BASE_URL}/cart">
                            <i class="fa-solid fa-angle-left"></i>
                            <p>Quay lại giỏ hàng</p>
                        </a>
                    </div>

                    <div class="payment__controls-price">
                        Tổng cộng:
                        <span id="total-payment">0đ</span>
                    </div>
                </div>

                <div class="payment__controls-confirm mx-auto me-md-4">
                    <button type="button" id="payment-confirm">Thanh toán</button>
                </div>
            </div>
        </div>
    </div>

	<script src="${BASE_URL}/static/user/js/payment.js"></script>
</body>