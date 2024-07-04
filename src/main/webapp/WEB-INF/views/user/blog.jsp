<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Blog - Book Store</title>
 	<link rel="stylesheet" href="${BASE_URL}/static/user/css/blog.css">
</head>

<body>
    <div class="banner">
        <img class="w-100" src="${BASE_URL}/static/user/images/blog-bgg.jpg" alt="" class="banner__img">
    </div>

    <div class="blogs">
        <div class="container">
            <div class="row">
                <c:forEach var="blog" items="${ blogs }">
                	<div class="col-6 col-md-4">
	                    <div class="blog w-100">
	                        <img class="w-100" style="aspect-ratio: 7/5;" src="${BASE_URL}/${ mediaService.getMediaById(blog.thumbnailId).getPath() }" alt="" class="blog__thumbnail">
	                        <div class="blog__info">
	                            <div class="blog__info-date">
	                                <i class="fa-regular fa-calendar-check me-2"></i>
	                                <span><fmt:formatDate value="${ blog.createdDate }" pattern="dd/MM/yyyy HH:mm:ss"/></span>
	                            </div>
	    
	                            <h4 class="blog__info-title">${ blog.title }</h4>
	                            
	                            <a href="${BASE_URL}/blog?id=${ blog.id }" class="blog__info-link">Xem thêm</a>
	                        </div>
	                    </div>
	                </div>
                </c:forEach>
            </div>
        </div>
    </div>
</body>