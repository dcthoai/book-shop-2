<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<head>
	<title>Giỏ hàng - Book Store</title>
	<link rel="stylesheet" href="${BASE_URL}/static/user/css/cart.css">
</head>

<body>
	<!-- Sublink -->
    <div class="sublink py-4">
        <div class="container">
            <div class="fs-6">
                <h4 class="text-dark fw-bold">Shop</h4>

                <div>
                    <a class="text-decoration-none text-dark" href="${BASE_URL}/home">Home</a>
                    <a class="text-decoration-none text-dark" href="${BASE_URL}/shop">
                    	<i class="fa-solid fa-angle-right px-2"></i>
                    	Shop
                    </a>
                    <span><i class="fa-solid fa-angle-right px-2"></i>Cart</span>
                </div>
            </div>
        </div>
    </div>

	<!-- Shopping cart -->
    <div class="container mt-5 px-2 cart">
        <div class="w-100 cart__header">
            <div class="row g-0 py-4 fs-5 align-items-center fw-normal">
                <div class="col-2 col-md-1 d-flex align-items-center justify-content-center">
                    <input type="checkbox" name="select-all-cart-product" id="select-all-cart-product">
                </div>

                <div class="col-10 col-md-11">
                    <div class="row g-0">
                        <div class="col-12 col-md-11 col-lg-6 p-0">
                            <p class="m-0 fs-5">Chọn tất cả (<span id="quantity-cart">${ quantityCart }</span> sản phẩm)</p>
                        </div>
        
                        <div class="col-lg-3 d-none d-lg-block text-center"><p class="m-0">Số lượng</p></div>
                        <div class="col-lg-2 d-none d-lg-block text-center pe-3"><p class="m-0">Thành tiền</p></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="w-100 cart__wrapper">
            <!-- A product -->
            <c:forEach var="pair" items="${cartProductPairs}">
        		<c:set var="book" value="${pair.first}"/>  
	    		<c:set var="cartProduct" value="${pair.second}"/>
	    		<c:set var="media" value="${mediaService.getMediaById(book.thumbnailId)}"/>
            
	            <div class="row py-4 g-0 align-items-center product">
	                <div class="col-2 col-md-1">
	                    <div class="d-flex align-items-center justify-content-center">
	                        <input type="checkbox" name="select" class="select-cart-product" data-id="${ pair.first.id }" data-quantity="${ cartProduct.quantity }">
	                    </div>
	                </div>
	
	                <div class="col-10 col-md-11">
	                    <div class="row g-0">
	                        <div class="col-3 col-sm-4 col-lg-2 d-flex align-items-center justify-content-start">
	                            <div class="w-100 product__img">
	                            	<a href="${BASE_URL}/product?id=${ book.id }">
	                                	<img src="${BASE_URL}/${ media.path }" alt="product image">
	                            	</a>
	                            </div>
	                        </div>
	
	                        <div class="col-9 col-sm-8 col-lg-10 ps-3">
	                            <div class="row g-0 h-100 flex-column flex-lg-row justify-content-between">
	                                <div class="col-12 col-lg-5 pb-md-3 pb-lg-0 d-flex flex-column justify-content-md-between product__info">
	                                    <div class="pe-sm-3">
	                                        <h5 class="pe-2 fs-6 text-black title">
	                                        	<a href="${BASE_URL}/product?id=${ book.id }">${ book.title }</a>
	                                        </h5>
	                                        <p class="m-0 d-none d-md-block fw-semibold">
	                                        	Tác giả: 
	                                        	<span class="fw-normal">${ book.author }</span>
	                                        </p>
	                                        <p class="m-0 pe-2 d-none fw-semibold description">
	                                        	Mô tả: 
	                                        	<span class="fw-normal">${ book.description }</span></p>
	                                    </div>
	            
	                                    <span class="pb-2 pb-lg-0 fs-6 fw-bold text-danger sell-price">
	                                    	${ book.getSellPrice() } đ
	                                    	<del class="d-block fs-6 fw-medium text-secondary">${ book.price } đ</del>
	                                    </span>
	                                </div>
	
	                                <div class="col-12 col-lg-7 d-lg-flex justify-content-center">
	                                    <div class="row w-100 align-items-center">
	                                        <div class="col-auto col-lg-5 d-lg-flex justify-content-lg-center">
	                                            <div class="quantity">
	                                                <button type="button" class="quantity__sub" data-id="${ cartProduct.id }">
	                                                	<i class="fa-solid fa-minus"></i>
	                                                </button>
	                                                
	                                                <input disabled class="quantity__input" type="text" min="1" value="${ cartProduct.quantity }">
	                                                
	                                                <button type="button" class="quantity__add" data-id="${ cartProduct.id }">
	                                                	<i class="fa-solid fa-plus"></i>
	                                                </button>
	                                            </div>
	                                        </div>
	                
	                                        <div class="col-lg-5 d-none d-lg-block text-center">
	                                            <span class="fs-5 fw-bold text-danger product-total">${ cartProduct.quantity * book.getSellPrice() }đ</span>
	                                        </div>
	                
	                                        <div class="col-auto col-lg-2 flex-fill px-0 px-sm-3 px-lg-0">
	                                            <button class="delete" data-id="${ cartProduct.id }">
	                                            	<i class="fs-5 fa-solid fa-trash-can"></i>
	                                            </button>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <!-- End a product -->
            </c:forEach>
        </div>
        
        <div class="cart__payment d-flex flex-column flex-md-row align-items-center justify-content-between justify-content-lg-around mt-5 px-4 px-lg-0">
	        <div class="total__price mt-5 mt-md-0">
	            <div class="total__price-heading">Tổng thanh toán:</div>
	            <span id="total-price">0đ</span>
	        </div>
	
	        <button type="button" id="payment-btn" class="payment__link my-5 px-4 py-2 h-auto">Thanh toán</button>
	    </div>
    </div>

	<script src="${BASE_URL}/static/user/js/cart.js"></script>
</body>