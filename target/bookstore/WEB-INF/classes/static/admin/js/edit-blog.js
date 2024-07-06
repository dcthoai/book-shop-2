
const submitBlogBtn = document.getElementById('edit-blog');
const thumbnailBlogInput = document.getElementById('thumbnail-demo');

document.getElementById('thumbnail').addEventListener('change', function(e) {
    thumbnailBlogInput.src = URL.createObjectURL(e.target.files[0]);
});

submitBlogBtn.addEventListener('click', () => {
	let formData = new FormData();

    // Fetch các giá trị từ form và thêm vào FormData
    formData.append('title', document.getElementById('title').value);
    formData.append('content', document.getElementById('content').value);
    
	let thumbnailFile = document.getElementById('thumbnail').files[0];
    
	if (thumbnailFile)
	    formData.append('thumbnail', thumbnailFile);
    
    formData.append('id', submitBlogBtn.dataset.id);
    
    fetch(BASE_URL + '/admin/blog/update', {
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


document.getElementById('cancel-blog').addEventListener('click', () => {
	window.location.href = BASE_URL + '/admin/blog';
})