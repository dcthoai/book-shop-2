<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Dashboard | Admin</title>
</head>

<body>
	<h4 class="p-3 ps-lg-0 fs-4 text-light fw-semibold">Quản lý sản phẩm</h4>

    <div class="w-100 d-flex flex-wrap justify-content-between my-4">
        <div class="d-flex form-search-book mb-4">
            <input type="text" class="flex-1 h-100 form-control form-search-input" id="search-book-input" placeholder="Nhập tên sách">
            <button type="button" class="ms-2 btn btn-primary py-0 fw-medium" id="search-book-button">Tìm kiếm</button>
        </div>

        <a href="${BASE_URL}/admin/product/add" class="text-decoration-none">
            <button type="button" class="btn btn-primary py-0 fw-medium add-new">Thêm sách</button>
        </a>
    </div>

    <div class="card mb-4 w-100">
        <div class="card-header d-flex flex-wrap justify-content-between align-items-center py-3">
            <div class="mb-3 fs-5 fw-medium opacity-75"><i class="fas fa-table me-3"></i>Danh sách sản phẩm</div>
        </div>
        
        <div class="card-body">
            <table id="table-list-books" class="w-100 table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="" style="width: max-content; max-width: 20rem; overflow-x:hidden; text-wrap: nowrap;">Tên sản phẩm</th>
                        <th class="col">Tác giả</th>
                        <th class="col text-center">Giá bán</th>
                        <th class="col text-center">Giá gốc</th>
                        <th class="col text-center">Giảm giá</th>
                        <th class="col text-center">Tồn kho</th>
                        <th class="col text-center">Ngày thêm</th>
                        <th class="col text-center">Chỉnh sửa</th>
                    </tr>
                </thead>
                
                <tbody id="search-book-container">
                	<c:set var="bookService" value="${ bookService }"></c:set>
                    <c:forEach var="book" items="${ books }">
                    	<tr>
	                        <td class="table-title" style="width: max-content; max-width: 20rem; overflow-x:hidden; text-wrap: nowrap;">
	                        	${ book.title }</td>
	                        <td class="table-author">${ book.author }</td>
	                        <td class="table-price text-center ">${ book.getSellPrice() }</td>
	                        <td class="table-cost text-center ">${ book.price }</td>
	                        <td class="table-discount text-center ">${ book.discount }%</td>
	                        <td class="table-stock text-center ">${ book.stock }</td>
	                        <td class="table-date text-center ">${ book.createdDate }</td>
	                        <td>
	                            <div class="w-100 h-100 d-flex justify-content-evenly">
	                                <a href="${BASE_URL}/admin/product/edit?id=${ book.id }" class="flex-fill edit-book"><i class="fa-regular fa-pen-to-square"></i></a>
	                                <a data-id="${ book.id }" class="flex-fill delete-book"><i class="fa-regular fa-trash-can"></i></a>
	                            </div>
	                        </td>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
    <script src="${BASE_URL}/static/admin/js/book.js"></script>
</body>