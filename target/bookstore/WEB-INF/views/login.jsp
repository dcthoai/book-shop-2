<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${BASE_URL}/static/user/images/favi.png" type="image/x-icon">
    <title>Đăng nhập | Bookstore</title>

    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap">
    <!-- CSS icon from Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <!-- CSS Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- CSS custom -->
    <link rel="stylesheet" href="${BASE_URL}/static/user/css/notify.css">
    <link rel="stylesheet" href="${BASE_URL}/static/user/css/login.css">
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

    <div class="container">
        <div id="form-signin" class="form">
	        <h2 class="heading">Đăng nhập</h2>
	
	        <form id="form-login" action="#" class="mt-4">
	            <div class="input-box">
	                <span class="label-error"></span>
	                <input type="text" placeholder="Nhập username" id="username" name="username">
	            </div>
	            <div class="input-box">
	                <span class="label-error"></span>
	                <input type="password" placeholder="Nhập mật khẩu" id="password" name="password">
	            </div>
	            <div class="input-box button">
	                <button type="submit">Đăng nhập</button>
	            </div>
	        </form>
	
	        <h3 class="change-link m-0 pt-3 w-100 text-center">
	        	Bạn chưa có tài khoản? <a href="${ BASE_URL }/register" class="link ms-1">Đăng ký ngay</a>
	        </h3>
	    </div>
    </div>

    <script>
    	var BASE_URL = "${BASE_URL}";
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
    	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
    	crossorigin="anonymous"></script>
    
    <!-- Custom JS -->
    <script src="${BASE_URL}/static/user/js/notify.js"></script>
    <script src="${BASE_URL}/static/user/js/validator.js"></script>
    <script src="${BASE_URL}/static/user/js/login.js"></script>
</body>
</html>