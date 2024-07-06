
const addCategoryBtn = document.getElementById('add-category-btn');
const addLanguageBtn = document.getElementById('add-language-btn');
const categoryInput = document.getElementById('category');
const languageInput = document.getElementById('language');

const deleteCategoryBtns = document.querySelectorAll('.delete-category-btn');
const deleteLanguageBtns = document.querySelectorAll('.delete-language-btn');

addCategoryBtn.addEventListener('click', () => {
	fetch(BASE_URL + '/admin/category/add', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			"category": categoryInput.value.trim()
		})
	})
	.then(response => response.json())
	.then(status => {
		if (status.success){
			openPopupNotify('Thêm thành công', '', 'success');
			
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

addLanguageBtn.addEventListener('click', () => {
	fetch(BASE_URL + '/admin/language/add', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			"language": languageInput.value.trim()
		})
	})
	.then(response => response.json())
	.then(status => {
		if (status.success){
			openPopupNotify('Thêm thành công', '', 'success');
			
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

deleteCategoryBtns.forEach(button => {
	button.addEventListener('click', () => {
		openPopupConfirm('Bạn có chắc chắn muốn xóa không?', '', 'warning', function(isSuccess){
			if (isSuccess){
				closePopupNotify();
				
				fetch(BASE_URL + `/admin/category/delete?id=${button.dataset.id}`, {
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

deleteLanguageBtns.forEach(button => {
	button.addEventListener('click', () => {
		openPopupConfirm('Bạn có chắc chắn muốn xóa không?', '', 'warning', function(isSuccess){
			if (isSuccess){
				closePopupNotify();
				
				fetch(BASE_URL + `/admin/language/delete?id=${button.dataset.id}`, {
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


