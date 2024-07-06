const addToCart = document.getElementById('add-to-cart');
const buyNow = document.getElementById('buy-now');

const increaseNumber = document.querySelector('.product .product__quantity .quantity .quantity__add');
const decreaseNumber = document.querySelector('.product .product__quantity .quantity .quantity__sub');
const productQuantity = document.querySelector('.product .product__quantity .quantity .quantity__input');

increaseNumber.addEventListener('click', function(){
    let quantity = parseInt(productQuantity.value);

    productQuantity.value = quantity + 1;
});

decreaseNumber.addEventListener('click', function(){
    let quantity = parseInt(productQuantity.value);

    if (quantity > 1){
        productQuantity.value = quantity - 1;
    } else {
        productQuantity.value = 1;
    }
})

addToCart.addEventListener('click', function(){
	let bookId = addToCart.dataset.product;
	
	fetch(BASE_URL + '/user/cart/add', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			'bookId': bookId,
			'quantity': productQuantity.value
		})
	})
	.then(response => response.json())
	.then(status => {
		if (status.success){
			openPopupNotify('Thêm vào giỏ hàng thành công', '', 'success');
			getQuantityCart();
		} else {
			openPopupNotify('Thất bại', status.message, 'error');
		}
	})
	.catch(error => {
        openPopupNotify('Thất bại', 'Lỗi bất định', 'error');
		console.error(error);
	})
});

buyNow.addEventListener('click', function(){
    let bookId = addToCart.dataset.product;
    let cartProducts = [];
    cartProducts.push({
		"bookId": bookId,
		"quantity": productQuantity.value
	})
    
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
})