
const adminSearchBlogBtn = document.getElementById("search-blog-button");
const adminSearchBlogInput = document.getElementById('search-blog-input');
const resultsBlogAdmin = document.getElementById('search-blog-container');

adminSearchBlogBtn.addEventListener('click', () => {
	let key = adminSearchBlogInput.value.trim();
	
	fetch(BASE_URL + `/admin/blog/search?name=${ key }`)
	.then(response => response.json())
	.then(data => {
		let resultsHtml1 = 'Không tìm thấy bài viết nào.';
		
		if (data) {
            if (Array.isArray(data)) {
                if (data.length > 0) {
                    resultsHtml1 = '';

                    data.forEach(blog => {
                        resultsHtml1 += `
	                        <tr>
		                        <td class="">${ blog.title }</td>
		                        <td class="">${ blog.author }</td>
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
						`;
                    });
                }
            }
        }
        
        resultsBlogAdmin.innerHTML = resultsHtml1;
        deleteBlogsListener();
	})
	.catch(error => {
		openPopupNotify('Thất bại', 'Lỗi bất định', 'error');
		console.error(error);
	})
})

function deleteBlogsListener() {
	var deleteBlogBtns = document.querySelectorAll('#table-list-blogs .delete-blog');

	deleteBlogBtns.forEach(button => {
		button.addEventListener('click', () => {
			openPopupConfirm('Bạn có chắc chắn muốn xóa bài viết này không?', '', 'warning', function(isSuccess){
				if (isSuccess){
					closePopupNotify();
					
					fetch(BASE_URL + `/admin/blog/delete?id=${button.dataset.id}`, {
						method: 'DELETE'
					})
					.then(response => response.json())
					.then(status => {
						if (status.success){
							openPopupNotify('Xóa thành công', '', 'success');
							
							setTimeout(() => {
								location.reload();
							}, 1000);
						} else {
							openPopupNotify('Thất bại', status.message, 'error');
						}
					})
					.catch(error => {
						openPopupNotify('Thất bại', 'Lỗi bất định', 'error');
						console.error(error);
					})
				}
			});
		})
	});
}

deleteBlogsListener();