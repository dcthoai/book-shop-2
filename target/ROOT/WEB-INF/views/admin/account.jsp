<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Quản lý tài khoản | Admin</title>
</head>

<body>

	<h4 class="p-3 ps-lg-0 fs-4 text-light fw-semibold">Quản lý tài khoản người dùng</h4>

    <div class="w-100 d-flex flex-wrap justify-content-between my-4">
        <div class="d-flex form-search-book mb-4">
            <input type="text" class="flex-1 h-100 form-control form-search-input" id="search-user-input" placeholder="Nhập username">
            <button type="button" class="ms-2 btn btn-primary py-0 fw-medium" id="search-user-button">Tìm kiếm</button>
        </div>
    </div>

    <div class="card mb-4 w-100">
        <div class="card-header d-flex flex-wrap justify-content-between align-items-center py-3">
            <div class="mb-3 fs-5 fw-medium opacity-75"><i class="fas fa-table me-3"></i>Danh sách tài khoản</div>
        </div>

        <div class="card-body">
            <table id="table-list-slides" class="w-100 table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="text-center" style="min-width: 8rem;">Tên tài khoản</th>
                        <th class="text-center">Họ và tên</th>
                        <th class="text-center">Email</th>
                        <th class="text-center">Số điện thoại</th>
                        <th class="text-center">Địa chỉ</th>
                        <th class="text-center">Người tạo</th>
                        <th class="text-center">Ngày tạo</th>
                        <th class="text-center">Người cập nhật</th>
                        <th class="text-center">Ngày cập nhật</th>
                    </tr>
                </thead>
                
                <tbody>
                	<c:forEach var="user" items="${ users }">
                		<tr>
	                        <td class="">${ user.username }</td>
	                        <td class="">${ user.fullname }</td>
	                        <td class="">${ user.email }</td>
	                        <td class="text-center">${ user.phone }</td>
	                        <td class="">${ user.address }</td>
	                        <td class="">${ user.createdBy }</td>
	                        <td class="text-center">${ user.createdDate }</td>
	                        <td class="">${ user.modifiedBy }</td>
	                        <td class="text-center">${ user.modifiedDate }</td>
	                    </tr>
                	</c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>