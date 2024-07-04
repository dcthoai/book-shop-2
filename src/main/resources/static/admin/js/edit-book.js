
const submitBookBtn = document.getElementById('submit-book');
const thumbnailInput = document.getElementById('thumbnail-demo');

document.getElementById('thumbnail').addEventListener('change', function(e) {
    thumbnailInput.src = URL.createObjectURL(e.target.files[0]);
});

submitBookBtn.addEventListener('click', () => {
	let formData = new FormData();

    // Fetch các giá trị từ form và thêm vào FormData
    formData.append('title', document.getElementById('title').value);
    formData.append('description', document.getElementById('description').value);
    formData.append('author', document.getElementById('author').value);
    formData.append('publisher', document.getElementById('publisher').value);
    formData.append('price', document.getElementById('price').value);
    formData.append('discount', document.getElementById('discount').value);
    formData.append('releaseDate', document.getElementById('releaseDate').value);
    formData.append('languageId', document.getElementById('language').value);
    formData.append('categoryId', document.getElementById('category').value);
    formData.append('pages', document.getElementById('pages').value);
    formData.append('weight', document.getElementById('weight').value);
    formData.append('stock', document.getElementById('stock').value);
    formData.append('size', document.getElementById('size').value);
    
    let thumbnailFile = document.getElementById('thumbnail').files[0];
    
	if (thumbnailFile)
	    formData.append('thumbnail', thumbnailFile);
    
    formData.append('id', submitBookBtn.dataset.id);
    
    fetch(BASE_URL + '/admin/product/update', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(status => {
        if (status.success) {
			openPopupNotify('Thành công', '', 'success');
			
			setTimeout(() => {
				location.reload();
			}, 1000);
		} else {
			openPopupNotify('Thất bại', status.message, 'error');
		}
    })
    .catch(error => {
		openPopupNotify('Thất bại', 'Lỗi bất định', 'error');
        console.error('Error:', error);
    });
});

document.getElementById('cancel-edit').addEventListener('click', () => {
	console.log('akjfao')
	window.location.href = BASE_URL + '/admin';
})
