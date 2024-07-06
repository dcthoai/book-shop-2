const updateOrderBtn = document.getElementById('update-order-btn');
const estimatedArrivalInput = document.getElementById('estimatedDate');
const shipperPhoneInput = document.getElementById('shipper');
const orderStatus = document.getElementById('status');
const deliveriedBtn = document.getElementById('deliveried-at-btn');
const cancelOrder = document.getElementById('cancell-order');

cancelOrder.addEventListener('click', () => {
	window.location.href = BASE_URL + '/admin/dashboard/order';
})

updateOrderBtn.addEventListener('click', () => {
	fetch(BASE_URL + '/admin/dashboard/order/update', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			'id': updateOrderBtn.dataset.id,
			'status': orderStatus.value.trim(),
			'shipperPhone': shipperPhoneInput.value.trim(),
			'estimatedArrival': estimatedArrivalInput.value.trim()
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
        openPopupNotify('Thất bại', 'Rất tiếc khi có lỗi, vui lòng thử lại sau.', 'error');
		console.error(error);
	})
});


deliveriedBtn.addEventListener('click', () => {
	fetch(BASE_URL + `/admin/dashboard/order/update/deliveried?id=${ deliveriedBtn.dataset.id }`, {
		method: 'POST'
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
        openPopupNotify('Thất bại', 'Rất tiếc khi có lỗi, vui lòng thử lại sau.', 'error');
		console.error(error);
	})
});

