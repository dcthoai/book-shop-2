<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Mua sắm - Book Store</title>
	<link rel="stylesheet" href="${BASE_URL}/static/user/css/shop.css">
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
                </div>
            </div>
        </div>
    </div>

    <!-- Shop -->
    <div class="shop">
        <div class="container pt-5">
            <div class="row">
                <div class="col-lg-3 pe-5">
                    <div class="row">
                        <div class="form-search">
                            <input type="text" id="search-input" placeholder="Tìm tên sách hoặc tác giả">
                            <button type="button" id="search-button"><i class="fa-solid fa-magnifying-glass"></i></button>
                        </div>
                    </div>

                    <div class="nav-filter py-4">
                        <div class="filter mt-2">
                            <div class="py-2 d-flex justify-content-between align-items-center">
                                <h4 class="mb-0 fs-5">Thể loại</h4>

                                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#nav-category">
                                    <span class="fa-solid fa-angle-down"></span>
                                </button>
                            </div>

                            <div class="filter__body collapse navbar-collapse" id="nav-category">
                                <ul class="mt-2 mb-4 lh-lg">
                                    <li><div class="filter-category-item" data-id="-1">Tất cả</div></li>
                                    <c:forEach var="category" items="${ categories }">
                                    	<li><div class="filter-category-item" data-id="${ category.id }">${ category.name }</div></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>

                        <div class="filter mt-2">
                            <div class="py-2 d-flex justify-content-between align-items-center">
                                <h4 class="mb-0 fs-5">Ngôn ngữ</h4>

                                <button class="" type="button" data-bs-toggle="collapse" data-bs-target="#nav-language" aria-controls="nav-language" aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="fa-solid fa-angle-down"></span>
                                </button>
                            </div>

                            <div class="filter__body collapse navbar-collapse" id="nav-language">
                                <ul class="mt-2 mb-4 lh-lg">
                                    <li><div class="filter-language-item" data-id="-1">Tất cả</div></li>
                                    <c:forEach var="language" items="${ languages }">
                                    	<li><div class="filter-language-item" data-id="${ language.id }">${ language.name }</div></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>

                        <div class="filter mt-2">
                            <div class="py-2 d-flex justify-content-between align-items-center">
                                <h4 class="mb-0 fs-5">Khoảng giá</h4>

                                <button class="" type="button" data-bs-toggle="collapse" data-bs-target="#nav-price" aria-controls="nav-price" aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="fa-solid fa-angle-down"></span>
                                </button>
                            </div>

                            <div class="filter__body collapse navbar-collapse" id="nav-price">
                                <div class="d-flex justify-content-between align-items-center mt-4">
                                    <input id="min-price" class="me-2 h-75" type="number" placeholder="0đ">
                                    <span>-</span>
                                    <input id="max-price" class="ms-2 h-75" type="number" placeholder="100.000đ">
                                </div>
                                
                                <button id="filter-btn-price" class="btn btn-filter-price mt-3 mb-4">Lọc</button>
                            </div>
                        </div>

                        <!-- <div class="filter mt-2">
                            <div class="py-2 d-flex justify-content-between align-items-center">
                                <h4 class="mb-0 fs-5">Đánh giá</h4>

                                <button class="" type="button" data-bs-toggle="collapse" data-bs-target="#nav-rating" aria-controls="nav-rating" aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="fa-solid fa-angle-down"></span>
                                </button>
                            </div>

                            <div class="filter__body collapse navbar-collapse" id="nav-rating">
                                <ul class="mt-2 mb-4 lh-lg">
                                    <li><div data-star="-1" class="filter-stars-item">Tất cả</div></li>
                                    <li><div data-star="5" class="filter-stars-item">5 sao</div></li>
                                    <li><div data-star="4" class="filter-stars-item">4 sao</div></li>
                                    <li><div data-star="3" class="filter-stars-item">3 sao</div></li>
                                </ul>
                            </div>
                        </div> -->
                    </div>
                </div>

                <div class="col-lg-9">
                    <div class="row">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <p class="mb-0">
                            	Đang xem
                            	<span id="total-results" class="d-inline">${ newestBooks.size() }</span>
                            	kết quả
                            </p>
                        </div>

                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="d-flex justify-content-end">
                                <p class="mb-0 me-2">Sắp xếp theo:</p>

                                <select id="sort-by-options" class="sortby">
                                    <option value="newest">Mới nhất</option>
                                    <option value="discount">Giảm giá nhiều nhất</option>
                                    <option value="purchases">Mua nhiều nhất</option>
                                    <option value="low-price">Giá tăng dần</option>
                                    <option value="high-price">Giá giảm dần</option>
                                    <option value="name">Tên a-z</option>
                                    <option value="release-date">Phát hành mới nhất</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-5" id="book-results">
                        <c:forEach var="book" items="${ newestBooks }">
                        
                        	<div class="col-lg-3 col-md-4 col-6 mb-4">
	                            <div class="product">
	                                <a href="${BASE_URL}/product?id=${ book.id }" class="product__link">
	                                	<c:set var="media" value="${mediaService.getMediaById(book.thumbnailId)}"/>
	                                    <div class="product__img"><img src="${BASE_URL}/${ media.path }" alt="product image"></div>
	
	                                    <div class="product__info p-3 pt-2">
	                                        <h6 class="name mb-2">${ book.title }</h6>
	
	                                        <div class="rating mb-2">
	                                            <i class="fa-regular fa-star"></i>
	                                            <i class="fa-regular fa-star"></i>
	                                            <i class="fa-regular fa-star"></i>
	                                            <i class="fa-regular fa-star"></i>
	                                            <i class="fa-regular fa-star"></i>
	                                        </div>
	                                        
	                                        <div>Đã bán: <span>${ book.purchases }</span></div>
	
	                                        <div class="price">
	                                            ${ book.getSellPrice() } đ
	                                            <span class="discount">-${ book.discount }%</span>
	                                        </div>
	
	                                        <del class="cost">${ book.price }đ</del>
	                                    </div>
	                                </a>
	                            </div>
	                        </div>
	                        
                        </c:forEach>
                    </div>

                    <!-- <div class="row mt-5 text-center">
                        <div class="col-lg-12">
                            <div class="product__pagination">
                                <a class="mx-2 active" href="#">1</a>
                                <a class="mx-2" href="#">2</a>
                                <a class="mx-2" href="#">3</a>
                                <span>...</span>
                                <a class="mx-2" href="#">21</a>
                            </div>
                        </div>
                    </div> -->
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript" src="${BASE_URL}/static/user/js/shop.js"></script>
</body>