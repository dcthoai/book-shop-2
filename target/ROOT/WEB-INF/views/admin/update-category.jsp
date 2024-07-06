<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Thêm thông tin sản phẩm | Admin</title>
	<link rel="stylesheet" href="${BASE_URL}/static/admin/css/book.css" />
</head>

<body>
	<h4 class="p-3 ps-lg-0 fs-4 text-light fw-semibold">Quản lý sản phẩm</h4>

    <div class="form-add-book mt-4 px-3 px-lg-4 pb-5 rounded">
        <h5 class="mb-4 pt-4 text-center text-md-start">Sửa danh mục</h5>

        <div class="row mt-4">
            <label for="category" class="form-label fs-6 fw-medium opacity-75">Thể loại</label>
            
            <div class="col-12 col-sm-6 col-md-7 col-lg-6 col-xl-5 col-xxl-4 pe-2 pe-sm-0">
                <input value="${ category.name }" type="text" class="form-control mb-3 mb-sm-0 me-sm-2 py-1" id="category" name="category">
            </div>

            <div class="col-auto">
                <button id="update-category-btn" data-id="${ category.id }" type="button" class="btn btn-primary py-1 fw-medium">Xác nhận</button>
            </div>
        </div>
    </div>

	<script type="text/javascript" src="${BASE_URL}/static/admin/js/update-category.js"></script>
</body>