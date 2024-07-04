
const adminSearchBookBtn = document.getElementById("search-book-button");
const adminSearchBookInput = document.getElementById('search-book-input');
const resultsBookadmin = document.getElementById('search-book-container');

adminSearchBookBtn.addEventListener('click', () => {
	let key = adminSearchBookInput.value.trim();
	fetch(BASE_URL + `/admin/product/search?name=${ key }`)
	.then(response => response.json())
	.then(data => {
		let resultsHtml1 = 'Không tìm thấy sản phẩm nào.';
		
		if (data) {
            if (Array.isArray(data)) {
                if (data.length > 0) {
                    resultsHtml1 = '';

                    data.forEach(book => {
                        resultsHtml1 += `
	                        <tr>
		                        <td class="table-title" style="width: max-content; max-width: 20rem; overflow-x:hidden; text-wrap: nowrap;">
		                        	${ book.title }</td>
		                        <td class="table-author">${ book.author }</td>
		                        <td class="table-price text-center ">${ book.sellPrice }</td>
		                        <td class="table-cost text-center ">${ book.cost }</td>
		                        <td class="table-discount text-center ">${ book.discount }%</td>
		                        <td class="table-stock text-center ">${ book.stock }</td>
		                        <td class="table-date text-center ">${ book.createdDate }</td>
		                        <td>
		                            <div class="w-100 h-100 d-flex justify-content-evenly">
		                                <a href="${BASE_URL}/admin/product/edit?id=${ book.id }" class="flex-fill edit-book">
											<i class="fa-regular fa-pen-to-square"></i>
										</a>
		                                <a data-id="${ book.id }" class="flex-fill delete-book">
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
        
        resultsBookadmin.innerHTML = resultsHtml1;
        deleteBooksListener();
	})
	.catch(error => {
		openPopupNotify('Thất bại', 'Lỗi bất định', 'error');
		console.error(error);
	})
})

function deleteBooksListener() {
	var deleteBookBtns = document.querySelectorAll('#table-list-books .delete-book');

	deleteBookBtns.forEach(button => {
		button.addEventListener('click', () => {
			openPopupConfirm('Bạn có chắc chắn muốn xóa sản phẩm này không?', '', 'warning', function(isSuccess){
				if (isSuccess){
					closePopupNotify();
					
					fetch(BASE_URL + `/admin/product/delete?id=${button.dataset.id}`, {
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

deleteBooksListener();