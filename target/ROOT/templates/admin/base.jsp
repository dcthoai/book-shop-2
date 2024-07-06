<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><decorator:title></decorator:title></title>
    <link rel="shortcut icon" href="${BASE_URL}/static/user/images/favi.png" type="image/x-icon">

    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap">
    <!-- CSS icon from Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <!-- CSS Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
    	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- CSS custom -->
    <link rel="stylesheet" href="${BASE_URL}/static/user/css/notify.css">
    <link rel="stylesheet" href="${BASE_URL}/static/admin/css/styles.css">
    <decorator:head></decorator:head>
</head>

<body>
    <!-- Pop up to show notifications -->
    <div id="notify">
        <div class="d-flex align-items-center">
            <div class="notify-status d-flex justify-content-center align-items-center px-3 px-md-4 fs-1">
                <i class="fa-solid fa-circle-check"></i>
                <i class="fa-solid fa-bug"></i>
                <i class="fa-solid fa-triangle-exclamation"></i>
                <i class="fa-regular fa-comment-dots"></i>
            </div>
    
            <div class="notify-content">
                <div id="notify-title">This is title</div>
                <div id="notify-message">This is a example message.</div>
            </div>
             
            <div id="notify-close-button" class="justify-content-center align-items-center px-2 px-md-3 fs-3">
                <i class="w-75 p-3 text-secondary fa-solid fa-xmark"></i>
            </div>
        </div>

        <div class="w-100 py-3 pe-4 justify-content-end notify-response" id="notify-response">
            <button class="ok me-2 fw-bold" id="ok">Ok</button>
            <button class="cancel fw-bold" id="cancel">Hủy</button>
        </div>
    </div>
    
    <!-- Loading animation -->
    <div id="popup-loader">
    	<div class="circle"></div>
    </div>

    <!-- Body -->
    <nav class="navbar navbar-expand fixed-top navbar-dark bg-dark">
        <a class="navbar-brand ps-3" href="${BASE_URL}/admin">Quản trị viên</a>

        <button class="btn btn-dark btn-sm order-1 order-lg-0 ms-md-5 me-3 me-lg-0" type="button" data-bs-toggle="collapse" data-bs-target="#sideBarNav">
            <i class="fas fa-bars"></i>
        </button>
        
        <ul class="navbar-nav ms-auto me-3 me-lg-4">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="">Cài đặt</a></li>
                    <li><a class="dropdown-item" href="">Tài khoản</a></li>
                    <li><hr class="dropdown-divider" /></li>
                    <li><button class="dropdown-item" id="logout-admin-btn">Đăng xuất</button></li>
                </ul>
            </li>
        </ul>
    </nav>

    <div class="content d-flex vw-100">
        <div class="collapse collapse-horizontal show vh-100" id="sideBarNav">
            <div class="h-100 navbar-left">
                <!-- Sidebar controls -->
                <div class="accordion pt-3" id="accordionNav">
                    <div class="accordion-item border-0">
                        <h2 class="accordion-header">
                            <button class="accordion-button px-4 py-3" type="button" 
                                                                    data-bs-toggle="collapse" 
                                                                    data-bs-target="#collapse-general" 
                                                                    aria-expanded="true" 
                                                                    aria-controls="collapse-general">
                                <h4 class="m-0 text-light fs-6 fw-medium opacity-50">Chung</h4>
                            </button>
                        </h2>
                        <div id="collapse-general" class="accordion-collapse collapse show" data-bs-parent="#accordionNav">
                            <div class="accordion-body px-0 pt-0">
                                <div class="">
                                    <a href="${BASE_URL}/admin" class="text-decoration-none">
                                    	<div class="navbtn">Sản phẩm</div>
                                    </a>
                                    <a href="${BASE_URL}/admin/category" class="text-decoration-none">
                                    	<div class="navbtn">Danh mục sản phẩm</div>
                                    </a>
                                    <a href="${BASE_URL}/admin/blog" class="text-decoration-none">
                                    	<div class="navbtn">Blogs</div>
                                    </a>
                                    <a href="${BASE_URL}/admin/slide" class="text-decoration-none">
                                    	<div class="navbtn">Quảng cáo</div>
                                    </a>
                                    <a href="${BASE_URL}/admin/order" class="text-decoration-none">
                                    	<div class="navbtn">Đơn hàng</div>
                                    </a>
                                    <a href="${BASE_URL}/admin/account" class="text-decoration-none">
                                    	<div class="navbtn">Người dùng</div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item border-0">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed px-4 py-3" type="button" 
                                                                            data-bs-toggle="collapse" 
                                                                            data-bs-target="#collapse-discount" 
                                                                            aria-expanded="false" 
                                                                            aria-controls="collapse-discount">
                                <h4 class="m-0 text-light fs-6 fw-medium opacity-50">Khuyến mãi</h4>
                            </button>
                        </h2>
                        <div id="collapse-discount" class="accordion-collapse collapse" data-bs-parent="#accordionNav">
                            <div class="accordion-body px-0 pt-0">
                                <div class="text-light fs-6 fw-normal">
                                    <a href="" class="text-decoration-none"><div class="navbtn">Giảm giá</div></a>
                                    <a href="" class="text-decoration-none"><div class="navbtn">Khác</div></a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item border-0">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed px-4 py-3" type="button" 
                                                                            data-bs-toggle="collapse" 
                                                                            data-bs-target="#collapse-chart" 
                                                                            aria-expanded="false" 
                                                                            aria-controls="collapse-chart">
                                <h4 class="m-0 text-light fs-6 fw-medium opacity-50">Thống kê</h4>
                            </button>
                        </h2>
                        <div id="collapse-chart" class="accordion-collapse collapse" data-bs-parent="#accordionNav">
                            <div class="accordion-body px-0 pt-0">
                                <div class="text-light fs-6 fw-normal">
                                    <a href="" class="text-decoration-none"><div class="navbtn">Doanh số</div></a>
                                    <a href="" class="text-decoration-none"><div class="navbtn">Biểu đồ</div></a>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="accordion-item border-0">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed px-4 py-3" type="button" 
                                                                            data-bs-toggle="collapse" 
                                                                            data-bs-target="#collapse-system" 
                                                                            aria-expanded="false" 
                                                                            aria-controls="collapse-system">
                                <h4 class="m-0 text-light fs-6 fw-medium opacity-50">Hệ thống</h4>
                            </button>
                        </h2>
                        <div id="collapse-system" class="accordion-collapse collapse" data-bs-parent="#accordionNav">
                            <div class="accordion-body px-0 pt-0">
                                <div class="text-light fs-6 fw-normal">
                                    <a href="" class="text-decoration-none"><div class="navbtn">Thanh toán</div></a>
                                    <a href="" class="text-decoration-none"><div class="navbtn">Hỗ trợ</div></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	    
        <div class="vh-100 content-wrapper px-2 px-lg-4">
        	<decorator:body></decorator:body>
        </div>
    </div>
    <!-- End Body -->

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
	    		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
	    		crossorigin="anonymous"></script>
	    
    <!-- Custom JS -->
    <script>
    	var BASE_URL = "${BASE_URL}";
    </script>
    
   	<script src="${BASE_URL}/static/user/js/notify.js"></script>
    <script src="${BASE_URL}/static/admin/js/main.js"></script>
    <script>
        // Kiểm tra kích thước màn hình và đóng phần tử collapse nếu trên thiết bị di động
        if (window.innerWidth < 768) {
            var sideBarNav = document.getElementById('sideBarNav');
            sideBarNav.classList.remove('show'); // Xóa lớp 'show' để đóng phần tử
        }
    </script>
</body>
</html>