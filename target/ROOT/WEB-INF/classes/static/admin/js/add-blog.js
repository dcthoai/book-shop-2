
const submitBlogBtn = document.getElementById('upload-blog');
const thumbnailBlogInput = document.getElementById('thumbnail-demo');

document.getElementById('thumbnail').addEventListener('change', function(e) {
    thumbnailBlogInput.src = URL.createObjectURL(e.target.files[0]);
});

submitBlogBtn.addEventListener('click', () => {
	let formData = new FormData();

    // Fetch các giá trị từ form và thêm vào FormData
    formData.append('title', document.getElementById('title').value);
    formData.append('content', document.getElementById('content').value);
    formData.append('thumbnail', document.getElementById('thumbnail').files[0]);
    
    fetch(BASE_URL + '/admin/blog/add', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(status => {
        if (status.success) {
			openPopupNotify('Thành công', '', 'success');
			
			setTimeout(() => {
				window.location.href = BASE_URL + '/admin/blog';
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


document.getElementById('cancel-blog').addEventListener('click', () => {
	window.location.href = BASE_URL + '/admin/blog';
})