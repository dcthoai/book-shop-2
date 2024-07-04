const deleteSlideBtns = document.querySelectorAll(".delete-slide-btn");
    	
deleteSlideBtns.forEach(button => {
	button.addEventListener('click', () => {
		openPopupConfirm('Bạn có chắc chắn muốn xóa banner này không?', '', 'warning', function(isSuccess){
			if (isSuccess){
				closePopupNotify();
				
				fetch(BASE_URL + `/admin/slide/delete?id=${button.dataset.id}`, {
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
})