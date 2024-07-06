
var listIncreaseQuantityButton = document.querySelectorAll('.cart .product .quantity .quantity__add');
var listDecreaseQuantityButton = document.querySelectorAll('.cart .product .quantity .quantity__sub');
var listInputQuantity = document.querySelectorAll('.cart .product .quantity .quantity__input');
var listDeleteButton = document.querySelectorAll('.cart .product .delete');

var selectAllCartProductBtn = document.getElementById('select-all-cart-product');
var selectCartProductsBtn = document.querySelectorAll('.select-cart-product');
var totalPrice = document.getElementById('total-price');
var paymentBtn = document.getElementById('payment-btn');

function updateCartProduct(cartProduct, inputIndex){
	
	fetch(BASE_URL + '/user/cart/update', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(cartProduct)
	})
	.then(response => response.json())
	.then(status => {
		if (status.success){
			listInputQuantity[inputIndex].value = cartProduct.quantity;
			getQuantityCart();
		} else {
			openPopupNotify('Thất bại', status.message, 'error');
		}
	})
	.catch(error => {
        openPopupNotify('Thất bại', 'Lỗi bất định', 'error');
		console.error(error);
	})
}

function deleteCartProduct(cartProductId){
	fetch(BASE_URL + '/user/cart/delete', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			'cartProductId': cartProductId
		})
	})
	.then(response => response.json())
	.then(status => {
		if (status.success){
			location.reload();
			openPopupNotify('Xóa sản phẩm thành công!', '', 'success');
		} else {
			openPopupNotify('Thất bại', status.message, 'error');
		}
	})
	.catch(error => {
        openPopupNotify('Thất bại', 'Lỗi bất định', 'error');
		console.error(error);
	})
}

listIncreaseQuantityButton.forEach((button, index) => {
	button.addEventListener('click', function(){
		let cartProductId = button.dataset.id;
		let quantity = parseInt(listInputQuantity[index].value);

		let cartProduct = {
			'cartProductId': cartProductId,
			'quantity': quantity + 1
		}
		
		updateCartProduct(cartProduct, index);
	});
});

listDecreaseQuantityButton.forEach((button, index) => {
	button.addEventListener('click', function(){
		let cartProductId = button.dataset.id;
		let quantity = parseInt(listInputQuantity[index].value);

		if (quantity <= 1) {
			openPopupConfirm('Bạn có chắc chắn muốn xóa sản phẩm này không?', '', 'warning', function(success){
				if (success){
					closePopupNotify();
					deleteCartProduct(cartProductId);
				}
			});
		} else {
			let cartProduct = {
				'cartProductId': cartProductId,
				'quantity': quantity - 1
			}
			
			updateCartProduct(cartProduct, index);
		}
	});
});

listDeleteButton.forEach((button) => {
	button.addEventListener('click', function(){
		let cartProductId = button.dataset.id;
		
		openPopupConfirm('Bạn có chắc chắn muốn xóa sản phẩm này không?', '', 'warning', function(success){
			if (success){
				closePopupNotify();
				deleteCartProduct(cartProductId);
			}
		});
	});
});

function getTotalPrice() {
	let total = 0;
	let inputs = document.querySelectorAll('.product-total');
	
	selectCartProductsBtn.forEach((button, index) => {
		if (button.checked) {
			let temp = inputs[index].textContent.trim();
            total += Number(temp.replace(/đ/g, ''));
		}
	});
	
	return total;
}

selectAllCartProductBtn.addEventListener('change', () => {
	if (selectAllCartProductBtn.checked) {
		selectCartProductsBtn.forEach(button => {
			button.checked = true;
		});
	} else {
		selectCartProductsBtn.forEach(button => {
			button.checked = false;
		});
	}
	
	totalPrice.innerText = getTotalPrice() + "đ";
});

selectCartProductsBtn.forEach(button => {
	button.addEventListener('change', () => {
		if (!button.checked) {
			if (selectAllCartProductBtn.checked)
				selectAllCartProductBtn.checked = false;
		}
		
		totalPrice.innerText = getTotalPrice() + "đ";
	});
});

paymentBtn.addEventListener('click', () => {
	let check = false;
	let cartProducts = [];
	
	selectCartProductsBtn.forEach(button => {
		if (button.checked){
			check = true;
			
			let cartProduct = {};
			cartProduct.bookId = button.dataset.id;
			cartProduct.quantity = button.dataset.quantity;
			
			cartProducts.push(cartProduct);
		}
	});
	
	if (check) 
		saveOrderCache(cartProducts);
	else 
		openPopupNotify('Bạn chưa chọn sản phẩm nào', '', 'notify');
});

function saveOrderCache(cartProducts) {
	
	fetch(BASE_URL + '/user/order/cache', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			'cartProducts': cartProducts
		})
	})
	.then(response => response.json())
	.then(status => {
		if (status.success){
			window.location.href = BASE_URL + `/payment`;
		} else {
			openPopupNotify('Thất bại', status.message, 'error');
		}
	})
	.catch(error => {
        openPopupNotify('Thất bại', 'Lỗi bất định', 'error');
		console.error(error);
	})
}
