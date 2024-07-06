<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Sản phẩm - Book Store</title>
	<link rel="stylesheet" href="${BASE_URL}/static/user/css/product.css">
</head>

<body>
    <!-- Sublink -->
    <div class="sublink py-4">
        <div class="container">
            <div class="fs-6">
                <h4 class="text-dark fw-bold">Shop</h4>

                <div>
                    <a class="text-decoration-none text-dark" href="${ BASE_URL }/">Home</a>
                    <span><i class="fa-solid fa-angle-right px-2"></i>Shop</span>
                    <span><i class="fa-solid fa-angle-right px-2"></i>${ book.title }</span>
                </div>
            </div>
        </div>
    </div>

    <!-- Product -->
    <c:set var="media" value="${mediaService.getMediaById(book.thumbnailId)}"/>
    
    <div class="container product mt-5">
        <div class="row">
            <div class="col-12 col-lg-6 col-xl-5 ps-lg-4">
                <div class="row">
                    <div class="col-4 col-sm-3">
                        <div class="product__imgs">
                            <div class="img-wrapper mb-2 p-1"><img src="${BASE_URL}/${ media.path }" alt="product image"></div>
                            <div class="img-wrapper mb-2 p-1"><img src="${BASE_URL}/${ media.path }" alt="product image"></div>
                            <div class="img-wrapper mb-2 p-1"><img src="${BASE_URL}/${ media.path }" alt="product image"></div>
                            <div class="img-wrapper mb-2 p-1"><img src="${BASE_URL}/${ media.path }" alt="product image"></div>
                            <a href="" class="img-wrapper p-1">+5</a>
                        </div>
                    </div>

                    <div class="col-8">
                        <div class="product__img pt-2">
                            <img src="${BASE_URL}/${ media.path }" alt="product image">
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-12 col-lg-6 col-xl-7 mt-5 mt-lg-0 ps-md-5 ps-lg-0">
                <h4 class="mb-4 fs-4 fw-medium" style="color: #333;">${ book.title }</h4>
                
                <p class="mb-1 fs-6 fw-medium">Nhà xuất bản: <span class="fw-semibold">${ book.publisher }</span></p>
                <p class="mb-1 fs-6 fw-medium">Tác giả: <span class="fw-semibold">${ book.author }</span></p>
                <p class="mb-1 fs-6 fw-medium">Ngôn ngữ: <span class="fw-semibold">${ language }</span></p>
                <p class="mb-1 fs-6 fw-medium">Thể loại: <span class="fw-semibold">${ category }</span></p>

                <div class="my-4 opacity-75">
                    <i class="fa-regular fa-star"></i>
                    <i class="fa-regular fa-star"></i>
                    <i class="fa-regular fa-star"></i>
                    <i class="fa-regular fa-star"></i>
                    <i class="fa-regular fa-star"></i>
                    (<span>${ book.rate }</span> đánh giá)
                </div>
                
                <div>Đã bán: <span>${ book.purchases }</span></div>

                <p class="fs-2 fw-bold price">${ book.getSellPrice() } đ
                    <del class="fs-5 fw-semibold opacity-75">${ book.price } đ</del>
                    <span class="fs-5">-${ book.discount }%</span>
                </p>

                <div class="estimate-arival fw-medium">
                    <p>Thời gian giao hàng:</p> 
                    <span>Giao hàng đến</span>
                    <a href="">Thay đổi</a>
                </div>

                <div class="policy fw-medium">
                    <p>Chính sách đổi trả:</p> 
                    <span>Đổi trả trong vòng 30 ngày</span>
                    <a href="">Xem chi tiết</a>
                </div>

                <div class="product__quantity my-4">
                    <label class="d-inline me-4" for="quantity">Số lượng:</label>

                    <div class="quantity d-inline-block">
                        <button type="button" class="quantity__sub"><i class="fa-solid fa-minus"></i></button>
                        <input type="number" class="quantity__input" min="1" value="1" disabled>
                        <button type="button" class="quantity__add"><i class="fa-solid fa-plus"></i></button>
                    </div>
                </div>

                <div class="product__button pt-4">
                    <button class="buy-now me-2" type="button" id="buy-now" data-product="${ book.id }">Mua ngay</button>
                    <button class="add-to-cart" type="button" id="add-to-cart" data-product="${ book.id }">
                    	<i class="fa-solid fa-cart-shopping pe-2"></i>
                    	Thêm vào giỏ hàng
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- Voucher -->
    <div class="section-voucher container">
        <h4 class="ps-2 pt-2 fs-4"><img class="pb-1" src="${BASE_URL}/static/user/images/ico_coupon_red.svg" alt=""> Ưu đãi</h4>

        <div class="list-voucher">
            <div class="voucher-wrapper">
                <svg xmlns="http://www.w3.org/2000/svg" width="350" height="104" viewBox="0 0 524 145.001" class="svg-bg">
                    <path id="Frame_voucher_Web" d="M110,144H12A12,12,0,0,1,0,132V12A12,12,0,0,1,12,0h98a12.02,12.02,0,0,0,12,11.971A12.02,12.02,0,0,0,134,0H511a12,12,0,0,1,12,12V132a12,12,0,0,1-12,12H134v-.03a12,12,0,0,0-24,0V144Z" transform="translate(0.5 0.5)" fill="#fff" stroke="rgba(0,0,0,0)" stroke-miterlimit="10" stroke-width="1"></path>
                </svg>

                <div class="voucher">
                    <div class="voucher__icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="104.554" height="125.395" viewBox="0 0 104.554 125.395" class="cart2-svg-icon">
                            <path id="Frame_icon_web" d="M95.424,124.4H47.593l-33.592,0a12,12,0,0,1-12-12V12A12,12,0,0,1,14,0H80.785l.255,0H95.424a10.364,10.364,0,0,0,10.129,10.165l-.005,4.374a2.907,2.907,0,1,0,0,5.813v2.324a2.907,2.907,0,1,0,0,5.814v2.324a2.907,2.907,0,0,0-2.06.852,2.874,2.874,0,0,0-.855,2.05,2.917,2.917,0,0,0,2.915,2.912v2.324a2.907,2.907,0,0,0-2.06.852,2.874,2.874,0,0,0-.855,2.05,2.917,2.917,0,0,0,2.915,2.911v2.324a2.906,2.906,0,0,0-2.06.852,2.876,2.876,0,0,0-.855,2.051,2.912,2.912,0,0,0,2.915,2.9V55.22a2.907,2.907,0,1,0,0,5.813v2.324a2.907,2.907,0,1,0,0,5.813V71.5a2.907,2.907,0,0,0-2.06.852,2.874,2.874,0,0,0-.855,2.05,2.917,2.917,0,0,0,2.915,2.912v2.324a2.906,2.906,0,0,0-2.06.852,2.876,2.876,0,0,0-.855,2.051,2.912,2.912,0,0,0,2.915,2.9v2.324a2.907,2.907,0,1,0,0,5.814V95.9a2.907,2.907,0,1,0,0,5.814v2.324a2.906,2.906,0,0,0-2.06.852,2.876,2.876,0,0,0-.855,2.051,2.916,2.916,0,0,0,2.915,2.911l0,3.987A10.328,10.328,0,0,0,95.423,124.2c0,.065,0,.131,0,.2h0Z" transform="translate(-1.501 0.499)" fill="#FFB323" stroke="rgba(0,0,0,0)" stroke-miterlimit="10" stroke-width="1"></path>
                        </svg>
                        <img src="${BASE_URL}/static/user/images/ico_promotion.svg" alt="">
                    </div>

                    <div class="voucher__info">
                        <div class="voucher__info-title">
                            <h5 class="fs-6 fw-bold mb-0">Mã giảm 25k - Đơn hàng từ 100k</h5>
                            <a href="#">Chi tiết</a>
                        </div>
                        <p class="mb-0">Không áp dụng cho sách giáo khoa.  Không áp dụng cho sách giáo khoa.</p>
                    </div>
                </div>
            </div>

            <div class="voucher-wrapper">
                <svg xmlns="http://www.w3.org/2000/svg" width="350" height="104" viewBox="0 0 524 145.001" class="svg-bg">
                    <path id="Frame_voucher_Web" d="M110,144H12A12,12,0,0,1,0,132V12A12,12,0,0,1,12,0h98a12.02,12.02,0,0,0,12,11.971A12.02,12.02,0,0,0,134,0H511a12,12,0,0,1,12,12V132a12,12,0,0,1-12,12H134v-.03a12,12,0,0,0-24,0V144Z" transform="translate(0.5 0.5)" fill="#fff" stroke="rgba(0,0,0,0)" stroke-miterlimit="10" stroke-width="1"></path>
                </svg>

                <div class="voucher">
                    <div class="voucher__icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="104.554" height="125.395" viewBox="0 0 104.554 125.395" class="cart2-svg-icon">
                            <path id="Frame_icon_web" d="M95.424,124.4H47.593l-33.592,0a12,12,0,0,1-12-12V12A12,12,0,0,1,14,0H80.785l.255,0H95.424a10.364,10.364,0,0,0,10.129,10.165l-.005,4.374a2.907,2.907,0,1,0,0,5.813v2.324a2.907,2.907,0,1,0,0,5.814v2.324a2.907,2.907,0,0,0-2.06.852,2.874,2.874,0,0,0-.855,2.05,2.917,2.917,0,0,0,2.915,2.912v2.324a2.907,2.907,0,0,0-2.06.852,2.874,2.874,0,0,0-.855,2.05,2.917,2.917,0,0,0,2.915,2.911v2.324a2.906,2.906,0,0,0-2.06.852,2.876,2.876,0,0,0-.855,2.051,2.912,2.912,0,0,0,2.915,2.9V55.22a2.907,2.907,0,1,0,0,5.813v2.324a2.907,2.907,0,1,0,0,5.813V71.5a2.907,2.907,0,0,0-2.06.852,2.874,2.874,0,0,0-.855,2.05,2.917,2.917,0,0,0,2.915,2.912v2.324a2.906,2.906,0,0,0-2.06.852,2.876,2.876,0,0,0-.855,2.051,2.912,2.912,0,0,0,2.915,2.9v2.324a2.907,2.907,0,1,0,0,5.814V95.9a2.907,2.907,0,1,0,0,5.814v2.324a2.906,2.906,0,0,0-2.06.852,2.876,2.876,0,0,0-.855,2.051,2.916,2.916,0,0,0,2.915,2.911l0,3.987A10.328,10.328,0,0,0,95.423,124.2c0,.065,0,.131,0,.2h0Z" transform="translate(-1.501 0.499)" fill="#FFB323" stroke="rgba(0,0,0,0)" stroke-miterlimit="10" stroke-width="1"></path>
                        </svg>
                        <img src="${BASE_URL}/static/user/images/ico_promotion.svg" alt="">
                    </div>
                    
                    <div class="voucher__info">
                        <div class="voucher__info-title">
                            <h5 class="fs-6 fw-bold mb-0">Mã giảm 25k - Đơn hàng từ 100k</h5>
                            <a href="#">Chi tiết</a>
                        </div>
                        <p class="mb-0">Không áp dụng cho sách giáo khoa.  Không áp dụng cho sách giáo khoa.</p>
                    </div>
                </div>
            </div>

            <div class="voucher-wrapper">
                <svg xmlns="http://www.w3.org/2000/svg" width="350" height="104" viewBox="0 0 524 145.001" class="svg-bg">
                    <path id="Frame_voucher_Web" d="M110,144H12A12,12,0,0,1,0,132V12A12,12,0,0,1,12,0h98a12.02,12.02,0,0,0,12,11.971A12.02,12.02,0,0,0,134,0H511a12,12,0,0,1,12,12V132a12,12,0,0,1-12,12H134v-.03a12,12,0,0,0-24,0V144Z" transform="translate(0.5 0.5)" fill="#fff" stroke="rgba(0,0,0,0)" stroke-miterlimit="10" stroke-width="1"></path>
                </svg>

                <div class="voucher">
                    <div class="voucher__icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="104.554" height="125.395" viewBox="0 0 104.554 125.395" class="cart2-svg-icon">
                            <path id="Frame_icon_web" d="M95.424,124.4H47.593l-33.592,0a12,12,0,0,1-12-12V12A12,12,0,0,1,14,0H80.785l.255,0H95.424a10.364,10.364,0,0,0,10.129,10.165l-.005,4.374a2.907,2.907,0,1,0,0,5.813v2.324a2.907,2.907,0,1,0,0,5.814v2.324a2.907,2.907,0,0,0-2.06.852,2.874,2.874,0,0,0-.855,2.05,2.917,2.917,0,0,0,2.915,2.912v2.324a2.907,2.907,0,0,0-2.06.852,2.874,2.874,0,0,0-.855,2.05,2.917,2.917,0,0,0,2.915,2.911v2.324a2.906,2.906,0,0,0-2.06.852,2.876,2.876,0,0,0-.855,2.051,2.912,2.912,0,0,0,2.915,2.9V55.22a2.907,2.907,0,1,0,0,5.813v2.324a2.907,2.907,0,1,0,0,5.813V71.5a2.907,2.907,0,0,0-2.06.852,2.874,2.874,0,0,0-.855,2.05,2.917,2.917,0,0,0,2.915,2.912v2.324a2.906,2.906,0,0,0-2.06.852,2.876,2.876,0,0,0-.855,2.051,2.912,2.912,0,0,0,2.915,2.9v2.324a2.907,2.907,0,1,0,0,5.814V95.9a2.907,2.907,0,1,0,0,5.814v2.324a2.906,2.906,0,0,0-2.06.852,2.876,2.876,0,0,0-.855,2.051,2.916,2.916,0,0,0,2.915,2.911l0,3.987A10.328,10.328,0,0,0,95.423,124.2c0,.065,0,.131,0,.2h0Z" transform="translate(-1.501 0.499)" fill="#FFB323" stroke="rgba(0,0,0,0)" stroke-miterlimit="10" stroke-width="1"></path>
                        </svg>
                        <img src="${BASE_URL}/static/user/images/ico_promotion.svg" alt="">
                    </div>
                    
                    <div class="voucher__info">
                        <div class="voucher__info-title">
                            <h5 class="fs-6 fw-bold mb-0">Mã giảm 25k - Đơn hàng từ 100k</h5>
                            <a href="#">Chi tiết</a>
                        </div>
                        <p class="mb-0">Không áp dụng cho sách giáo khoa.  Không áp dụng cho sách giáo khoa.</p>
                    </div>
                </div>
            </div>
            
            <button type="button" class="show-more-voucher ms-4">
                <span class="fa-solid fa-angle-right"></span>
            </button>
        </div>
    </div>

    <!-- List product suggestion -->
    <div class="suggestion container p-4">
        <h4 class="fs-4 fw-medium mt-2 mb-4 ms-3">Đề xuất cho bạn</h4>

        <div class="row mb-3">
        
        	<c:forEach var="book" items="${ bookSuggesstion }">
        		<div class="col-6 col-sm-4 col-md-3 col-xl-2">
	                <div class="product">
	                    <a href="${BASE_URL}/product?id=${ book.id }" class="product__link">
	                        <div class="product__img">
	                            <img src="${BASE_URL}/${ mediaService.getMediaById(book.thumbnailId).getPath() }" alt="product image">
	                        </div>
	
	                        <div class="product__info pt-2">
	                            <h6 class="name mb-2">${ book.title }</h6>
	
	                            <div class="rating mb-2">
	                                <i class="fa-regular fa-star"></i>
	                                <i class="fa-regular fa-star"></i>
	                                <i class="fa-regular fa-star"></i>
	                                <i class="fa-regular fa-star"></i>
	                                <i class="fa-regular fa-star"></i>
	                            </div>
	
	                            <div class="price">
	                                ${ book.getSellPrice() }đ
	                                <span class="discount">-${ book.discount }%</span>
	                            </div>
	
	                            <del class="cost">${ book.price }đ</del>
	                        </div>
	                    </a>
	                </div>
	            </div>
        	</c:forEach>
            
        </div>
    </div>

    <!-- Product details info -->
    <div class="descriptions container">
        <h4>Thông tin sản phẩm</h4>

        <div class="row p-1 mt-4">
            <div class="col-6 col-lg-4 tag">
                <p class="m-0 py-1">Tác giả</p>
                <p class="m-0 py-1">Nhà xuất bản</p>
                <p class="m-0 py-1">Năm xuất bản</p>
                <p class="m-0 py-1">Ngôn ngữ</p>
                <p class="m-0 py-1">Thể loại</p>
                <p class="m-0 py-1">Trọng lượng (gram)</p>
                <p class="m-0 py-1">Kích thước (d x r x c)</p>
                <p class="m-0 py-1">Số trang</p>
            </div>
            <div class="col-6 col-lg-4 info">
                <p class="m-0 py-1">${ book.author }</p>
                <p class="m-0 py-1">${ book.publisher }</p>
                <p class="m-0 py-1"><fmt:formatDate value="${ book.releaseDate }" pattern="dd/MM/yyyy" /></p>
                <p class="m-0 py-1">${ language }</p>
                <p class="m-0 py-1">${ category }</p>
                <p class="m-0 py-1">${ book.weight }</p>
                <p class="m-0 py-1">${ book.size }</p>
                <p class="m-0 py-1">${ book.pages }</p>
            </div>
        </div>

        <p class="des-policy py-4">
            Giá sản phẩm đã bao gồm thuế theo luật hiện hành. 
            Bên cạnh đó, tuỳ vào loại sản phẩm, hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như 
            Phụ phí đóng gói, phí vận chuyển, phụ phí hàng cồng kềnh,...
        </p>

        <div class="description-text py-2">
            <p>${ book.description }</p>

            <div class="d-flex justify-content-center my-4">
                <button type="button">Xem thêm</button>
            </div>
        </div>
    </div>

    <!-- Rating -->
    <%-- <div class="rating container">
        <h4>Đánh giá sản phẩm</h4>

        <div class="d-flex">
            <div class="d-flex align-items-center">
                <div class="review__quantity">
                    <p class="fs-3 fw-bold"><span class="fs-1">${ book.purchases }</span>/5</p>
                    <div class="rate">
                        <i class="fa-regular fa-star"></i>
                        <i class="fa-regular fa-star"></i>
                        <i class="fa-regular fa-star"></i>
                        <i class="fa-regular fa-star"></i>
                        <i class="fa-regular fa-star"></i>
                    </div>
                    <span>(0 đánh giá)</span>
                </div>

                <div class="review__chart">
                    <div class="d-flex align-items-center">
                        5 <i class="fa-regular fa-star"></i>
                        <div class="rating__progress"><span></span></div>
                        <div class="rating__percent">0%</div>
                    </div>
                    <div class="d-flex align-items-center">
                        5 <i class="fa-regular fa-star"></i>
                        <div class="rating__progress"><span></span></div>
                        <div class="rating__percent">0%</div>
                    </div>
                    <div class="d-flex align-items-center">
                        5 <i class="fa-regular fa-star"></i>
                        <div class="rating__progress"><span></span></div>
                        <div class="rating__percent">0%</div>
                    </div>
                    <div class="d-flex align-items-center">
                        5 <i class="fa-regular fa-star"></i>
                        <div class="rating__progress"><span></span></div>
                        <div class="rating__percent">0%</div>
                    </div>
                    <div class="d-flex align-items-center">
                        5 <i class="fa-regular fa-star"></i>
                        <div class="rating__progress"><span></span></div>
                        <div class="rating__percent">0%</div>
                    </div>
                </div>
            </div>

            <div class="flex-grow-1 ps-5 text-center d-flex align-items-center">
                <p class="review-status">
                    Chỉ có thành viên mới có thể viết nhận xét.
                    Vui lòng <a href="">đăng nhập</a> hoặc <a href="">đăng ký</a>.
                </p>
            </div>
        </div>
    </div> --%>
    
    <script src="${BASE_URL}/static/user/js/product.js"></script>
</body>