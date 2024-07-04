<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Trang chủ - Book Store</title>
	<link rel="stylesheet" href="${BASE_URL}/static/user/css/home.css">
</head>

<body>
	<!-- Slider -->
    <div class="slider">
        <div class="container">
            <div id="carouselExample" class="carousel slide touch" data-bs-ride="carousel" data-bs-interval="5000" data-bs-touch="true">
                <div class="carousel-inner">
					<!-- Load list slides -->
	                <c:forEach var="slide" items="${ slides }" varStatus="index">
	                	<div class="carousel-item <c:if test="${ index.first }">active</c:if>">
	                	
	                        <div class="row justify-content-between align-items-center">
	                            <div class="col-12 col-lg-5 ps-md-4">
	                                <h1 class="mb-4 fw-bold fs-2 text-light">${ slide.caption }</h1>
	                                <p class="mb-4 slide-caption opacity-50 fs-6 text-light">
	                                    ${ slide.content }
	                                </p>
	                                <a href="${BASE_URL}/${ slide.link }" class="btn rounded-5 px-4 py-2 fs-5 fw-medium text-light explore">Xem ngay</a>
	                            </div>
	
	                            <div class="col-sm-12 col-lg-7 pt-5 pt-lg-0 slide-img">
	                            	<c:set var="media" value="${mediaService.getMediaById(slide.thumbnailId)}"/>
	                                <img src="${BASE_URL}/${ media.path }" alt="slide image">
	                            </div>
	                        </div>
	                    </div>
	                </c:forEach>
	                
                </div>
        
                <button class="d-none d-md-block carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                    <i class="fa-solid fa-angle-left"></i>
                </button>
                
                <button class="d-none d-md-block carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                    <i class="fa-solid fa-angle-right"></i>
                </button>
            </div>
        </div>
    </div>

    <!-- Season trending -->
    <div class="season-trending">
        <div class="container">
            <div class="row justify-content-between">
                <div class="col-lg-7 mb-5 mb-lg-0">
                    <div class="row align-items-start img-wrapper">
                        <div class="col-8"><img class="img-1 rounded-4" src="${BASE_URL}/static/user/images/trend1.jpg" alt="trending-img"></div>
                        <div class="col-4 position-relative">
                            <img class="img-2 rounded-4" src="${BASE_URL}/static/user/images/trend2.png" alt="trending-img">
                            <img class="img-3 rounded-4" src="${BASE_URL}/static/user/images/trend3.jpg" alt="trending-img">
                        </div>
                    </div>
                </div>

                <div class="col-lg-5 pt-5 pt-lg-0 ps-lg-5 mt-5 mt-lg-0">
                    <h2 class="mb-4 fs-2 text-dark">Xu hướng sách mùa này</h2>
                    <p class="fs-6">
	                    Cập nhật các xu hướng mới nhất cho bạn đọc.
                    </p>

                    <ul class="my-4">
                        <li class="mb-3" style="list-style: inside;">
                        	Marketing và Design: Sự sáng tạo không giới hạn
                        </li>
                        <li class="mb-3" style="list-style: inside;">
                        	Sức khỏe tinh thần và não bộ: 
                        	Xu hướng sách tập trung vào phát triển cá nhân và kiến thức về tâm lý học.
	                    </li>
                        <li class="mb-3" style="list-style: inside;">
                        	Tương lai và xu hướng toàn cầu: 
                        	Cái nhìn sâu sắc về các xu hướng lớn sẽ ảnh hưởng đến thế giới trong thập kỷ tới.
                        </li>
                    </ul>

                    <a href="${BASE_URL}/shop" class="btn btn-dark mt-4 px-4 py-2 fs-5 fw-medium rounded-5">Xem ngay</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Recent blog -->
    <div class="blog">
        <div class="container">
            <div class="row mb-5">
                <div class="col-md-6">
                    <h2 class="fs-2 text-dark">Blog mới</h2>
                </div>
                <div class="col-md-6 pt-4 text-start text-md-end">
                    <a href="${BASE_URL}/blog" class="fs-6 text-decoration-none text-dark">Xem tất cả</a>
                </div>
            </div>

            <div class="row">
            	<!-- Loop listTopBlogs -->
            	<c:forEach var="blog" items="${ newBlogs }">
            		<div class="col-12 col-sm-6 col-md-4 mb-4 mb-md-0">
	                    <div class="post-entry">
	                    	<c:set var="media" value="${mediaService.getMediaById(blog.thumbnailId)}"/>
	                        <a href="#" class="post-thumbnail"><img src="${BASE_URL}/${ media.path }" alt="Image" class="img-fluid rounded-4"></a>
	                        <div class="mt-4">
	                            <h3><a class="text-decoration-none text-dark fs-4" href="#">${ blog.title }</a></h3>
	                            <div class="meta">
	                            	<!-- <c:set var="user" value=""/> -->
	                                <span>by <a href="#">${ userService.getUserById(blog.authorId).getFullname() }</a></span>
	                                <span>on <a href="#"><fmt:formatDate value="${ blog.modifiedDate }" pattern="dd/MM/yyyy HH:mm:ss"/></a></span>
	                            </div>
	                        </div>
	                    </div>
	                </div>
            	</c:forEach>
            </div>
        </div>
    </div>
</body>