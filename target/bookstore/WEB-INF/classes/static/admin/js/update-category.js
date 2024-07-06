
const updateCategoryBtn = document.getElementById('update-category-btn');
const categoryInput = document.getElementById('category');

updateCategoryBtn.addEventListener('click', () => {
	fetch(BASE_URL + '/admin/category/update', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			"category": categoryInput.value.trim(),
			"id": updateCategoryBtn.dataset.id
		})
	})
	.then(response => response.json())
	.then(status => {
		if (status.success){
			openPopupNotify('Cập nhật thành công', '', 'success');
			
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
});