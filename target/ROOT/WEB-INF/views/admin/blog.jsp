<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Blogs | Admin</title>
</head>

<body>
	<h4 class="p-3 ps-lg-0 fs-4 text-light fw-semibold">Quản lý các bài viết</h4>
	
	<div class="w-100 d-flex flex-wrap justify-content-between my-4">
        <div class="d-flex form-search-book mb-4">
            <input type="text" class="flex-1 h-100 form-control form-search-input" id="search-blog-input" placeholder="Nhập tên blog">
            <button type="button" class="ms-2 btn btn-primary py-0 fw-medium" id="search-blog-button">Tìm kiếm</button>
        </div>

        <a href="${BASE_URL}/admin/blog/add" class="text-decoration-none">
            <button type="button" class="btn btn-primary py-0 fw-medium add-new">Thêm bài viết</button>
        </a>
    </div>

    <div class="card mb-4 w-100">
        <div class="card-header d-flex flex-wrap justify-content-between align-items-center py-3">
            <div class="mb-3 fs-5 fw-medium opacity-75"><i class="fas fa-table me-3"></i>Danh sách bài viết</div>
        </div>

        <div class="card-body">
            <table id="table-list-blogs" class="w-100 table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th class="text-center">Tiêu đề</th>
                        <th class="text-center">Tác giả</th>
                        <th class="text-center">Ngày tạo</th>
                        <th class="text-center">Ngày cập nhật</th>
                        <th class="text-center">Chỉnh sửa</th>
                    </tr>
                </thead>
                
                <tbody id="search-blog-container" class="pt-4">
                	<c:forEach var="blog" items="${ blogs }">
                		<tr>
	                        <td class="d-none"><input type="checkbox" name="table-select"></td>
	                        <td class="">${ blog.title }</td>
	                        <td class="">${ userService.getUserById(blog.authorId).getUsername() }</td>
	                        <td class="text-center ">${ blog.createdDate }</td>
	                        <td class="text-center ">${ blog.modifiedDate }</td>
	                        <td>
	                            <div class="w-100 h-100 d-flex justify-content-evenly">
	                                <a href="${BASE_URL}/admin/blog/edit?id=${ blog.id }" class="flex-fill edit-blog">
	                                	<i class="fa-regular fa-pen-to-square"></i>
                                	</a>
	                                <a data-id="${ blog.id }" class="flex-fill delete-blog">
	                                	<i class="fa-regular fa-trash-can"></i>
                                	</a>
	                            </div>
	                        </td>
	                    </tr>
                	</c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
    <script type="text/javascript" src="${BASE_URL}/static/admin/js/blog.js"></script>
</body>