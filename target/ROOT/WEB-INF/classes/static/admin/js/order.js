
const searchOrderBtn = document.getElementById('search-order-button');
const resultOrders = document.getElementById('order-container');
const searchOrderInput = document.getElementById('search-order-input');

searchOrderBtn.addEventListener('click', () => {
	let key = searchOrderInput.value.trim();
	
	fetch(BASE_URL + `/admin/dashboard/order/search?customer=${ key }`)
	.then(response => response.json())
	.then(data => {
		let resultsHtml2 = 'Không tìm thấy đơn hàng nào.';
		
		if (data) {
            if (Array.isArray(data)) {
                if (data.length > 0) {
                    resultsHtml2 = '';

                    data.forEach(order => {
                        resultsHtml2 += `
	                        <tr>
		                        <td class="" style="min-width: 10rem">${ order.username }</td>
		                        <td class="">${ order.address }</td>
		                        <td class="text-center">${ order.orderStatus }</td>
		                        <td class="text-center">${ order.shippingCost }</td>
		                        <td class="text-center">${ order.discount }</td>
		                        <td class="text-center">${ order.totalPayment }</td>
		                        <td class="text-center ">${ order.paymentMethod }</td>
		                        <td class="text-center ">${ order.shipperPhone }</td>
		                        <td class="text-center ">${ order.orderDate }</td>
		                        <td class="text-center ">${ order.estimatedArrival }</td>
		                        <td class="text-center ">${ order.deliveredAt }</td>
		                        <td>
		                            <div class="w-100 h-100 d-flex justify-content-evenly">
		                                <a href="/bookstore/admin/dashboard/order/update?id=${ order.id }" class="flex-fill edit-book"><i class="fa-regular fa-pen-to-square"></i></a>
		                            </div>
		                        </td>
		                    </tr>
						`;
                    });
                }
            }
        }
        
        resultOrders.innerHTML = resultsHtml2;
	})
	.catch(error => {
		openPopupNotify('Thất bại', 'Rất tiếc khi có lỗi, vui lòng thử lại sau.', 'error');
		console.error(error);
	})
})