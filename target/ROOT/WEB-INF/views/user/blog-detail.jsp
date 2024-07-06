<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Blog - Book Store</title>
 	<link rel="stylesheet" href="${BASE_URL}/static/user/css/blog-detail.css">
</head>

<body>
	<div class="blog-detail container" style="padding-top: 5rem;">
        <div class="row">
            <div class="col-12 col-lg-9 content">
                <img class="img-fluid" src="${BASE_URL}/${ mediaService.getMediaById(blog.thumbnailId).getPath() }" alt="blog thumbnail">
                
                <h5 class="opacity-75">
                	Đã đăng: <span><fmt:formatDate value="${ blog.createdDate }" pattern="dd/MM/yyyy HH:mm:ss"/></span>
               	</h5>
               	
                <h1 class="title mt-5 fs-1 fw-bold">${ blog.title }</h1>
                <p class="mt-3" style="font-size: 16px; color: #1a1a1a;">${ blog.content }</p>
            </div>

            <div class="col-12 col-lg-3 author">
                <div class="author__name">${ userService.getUserById(blog.authorId).getFullname() }</div>

                <div class="author__connect d-flex flex-column align-items-center">
                    <h3 class="text-uppercase">Connect & Follow</h3>
                    
                    <div class="pt-4">
                        <a href="" class=""><i class="fa-brands fa-facebook fs-2 text-dark mx-2"></i></a>
                        <a href="" class=""><i class="fa-brands fa-square-instagram fs-2 text-dark mx-2"></i></a>
                        <a href="" class=""><i class="fa-brands fa-linkedin fs-2 text-dark mx-2"></i></a>
                        <a href="" class=""><i class="fa-brands fa-twitter fs-2 text-dark mx-2"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>