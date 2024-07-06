<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Thêm thông tin sản phẩm | Admin</title>
	<link rel="stylesheet" href="${BASE_URL}/static/admin/css/book.css" />
</head>

<body>
	<h4 class="p-3 ps-lg-0 fs-4 text-light fw-semibold">Quản lý sản phẩm</h4>

    <div class="form-add-book mt-4 px-3 px-lg-4 pb-5 rounded">
        <h5 class="mb-4 pt-4 text-center text-md-start">Thêm danh mục</h5>

        <div class="row mt-4">
            <label for="category" class="form-label fs-6 fw-medium opacity-75">Thể loại</label>
            
            <div class="col-12 col-sm-6 col-md-7 col-lg-6 col-xl-5 col-xxl-4 pe-2 pe-sm-0">
                <input type="text" class="form-control mb-3 mb-sm-0 me-sm-2 py-1" id="category" name="category">
            </div>

            <div class="col-auto">
                <button id="add-category-btn" type="button" class="btn btn-primary py-1 fw-medium">Thêm mới</button>
            </div>
        </div>

        <div class="row mt-4">
            <label for="language" class="form-label fs-6 fw-medium opacity-75">Ngôn ngữ</label>
            
            <div class="col-12 col-sm-6 col-md-7 col-lg-6 col-xl-5 col-xxl-4 pe-2 pe-sm-0">
                <input type="text" class="form-control flex-sm-1 mb-3 mb-sm-0 me-sm-2 py-1" id="language" name="language">
            </div>

            <div class="col-auto">
                <button id="add-language-btn" type="button" class="btn btn-primary py-1 fw-medium">Thêm mới</button>
            </div>
        </div>
        
        <div class="card mt-5 mb-4 w-100">
	        <div class="card-header d-flex flex-wrap justify-content-between align-items-center py-3">
	            <div class="mb-3 fs-5 fw-medium opacity-75"><i class="fas fa-table me-3"></i>Danh sách thể loại sách</div>
	        </div>
	        
	        <div class="card-body">
	            <table class="w-100 table table-striped table-bordered table-hover">
	                <thead>
	                    <tr>
	                        <th class="col" style="width: max-content; text-wrap: nowrap;">Tên</th>
	                        <th class="col text-center">Người thêm</th>
	                        <th class="col text-center">Ngày thêm</th>
	                        <th class="col text-center">Chỉnh sửa</th>
	                    </tr>
	                </thead>
	                
	                <tbody>
	                    <c:forEach var="category" items="${ categories }">
	                    	<tr>
		                        <td class="" style="width: max-content; text-wrap: nowrap;">${ category.name }</td>
		                        <td class="text-center ">${ category.createdBy }</td>
		                        <td class="text-center ">${ category.modifiedDate }</td>
		                        <td>
		                            <div class="w-100 h-100 d-flex justify-content-evenly">
		                                <a href="${BASE_URL}/admin/category/update?id=${ category.id }" class="flex-fill edit-book"><i class="fa-regular fa-pen-to-square"></i></a>
		                                <a data-id="${ category.id }" class="flex-fill delete-book delete-category-btn"><i class="fa-regular fa-trash-can"></i></a>
		                            </div>
		                        </td>
		                    </tr>
	                    </c:forEach>
	                </tbody>
	            </table>
	        </div>
	    </div>
    
	    <div class="card mb-4 w-100">
	        <div class="card-header d-flex flex-wrap justify-content-between align-items-center py-3">
	            <div class="mb-3 fs-5 fw-medium opacity-75"><i class="fas fa-table me-3"></i>Danh sách ngôn ngữ</div>
	        </div>
	        
	        <div class="card-body">
	            <table class="w-100 table table-striped table-bordered table-hover">
	                <thead>
	                    <tr>
	                        <th class="col" style="width: max-content; text-wrap: nowrap;">Tên</th>
	                        <th class="col text-center">Người thêm</th>
	                        <th class="col text-center">Ngày thêm</th>
	                        <th class="col text-center">Chỉnh sửa</th>
	                    </tr>
	                </thead>
	                
	                <tbody>
	                    <c:forEach var="language" items="${ languages }">
	                    	<tr>
		                        <td class="" style="width: max-content; text-wrap: nowrap;">${ language.name }</td>
		                        <td class="text-center ">${ language.createdBy }</td>
		                        <td class="text-center ">${ language.modifiedDate }</td>
		                        <td>
		                            <div class="w-100 h-100 d-flex justify-content-evenly">
		                                <a href="${BASE_URL}/admin/language/update?id=${ language.id }" class="flex-fill edit-book"><i class="fa-regular fa-pen-to-square"></i></a>
		                                <a data-id="${ language.id }" class="flex-fill delete-book delete-language-btn"><i class="fa-regular fa-trash-can"></i></a>
		                            </div>
		                        </td>
		                    </tr>
	                    </c:forEach>
	                </tbody>
	            </table>
	        </div>
	    </div>
    </div>

	<script type="text/javascript" src="${BASE_URL}/static/admin/js/category-language.js"></script>
</body>