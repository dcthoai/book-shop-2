const loginButton = document.getElementById('login-link');
const userControlButton = document.getElementById('user-control-button');
const userNav = document.getElementById('user-nav');
const logoutButton = document.getElementById('logout');

function openUserNav(){
    if (userNav.classList.contains('active')){
        userNav.classList.remove('active');
    } else {
        userNav.classList.add('active');
    }
}

window.addEventListener('DOMContentLoaded', () => {
	const token = localStorage.getItem('jwtToken');
	
	fetch(BASE_URL + `/auth`, {
		headers: {
            'Authorization': token
        }
	})
	.then(response => response.json())
	.then(status => {
		if (status.success) {
			loginButton.style.display = 'none';
			userControlButton.style.display = 'block';
		} else {
			loginButton.style.display = 'block';
			userControlButton.style.display = 'none';
		}
	})
	.catch(error => {
		loginButton.style.display = 'block';
		userControlButton.style.display = 'none';
		console.error(error);
	})
	
	userControlButton.onclick = () => {
		openUserNav();
	}
	
	logoutButton.onclick = () => {
		logout();
	}
});

function logout(){
    openLoadingAnimation();
	
    fetch(BASE_URL + `/logout`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(status => {
        closeLoadingAnimation();

        if(status.success){
			openPopupNotify('Đăng xuất thành công!', '', 'success');
			localStorage.removeItem('jwtToken');
			
            setTimeout(() => {
				window.location.reload();
			}, 1000);
        } else {
            openPopupNotify('Đăng xuất thất bại!', status.message, 'error');
        }
    })
    .catch(error => {
        closeLoadingAnimation();
        openPopupNotify('Đăng xuất thất bại!', 'Lỗi bất định', 'error');
        console.error(error);
    })
}

function getQuantityCart() {
	fetch(BASE_URL + `/user/cart/quantity-cart`)
    .then(response => response.json())
    .then(status => {
        if(status.success){
			let quantityCart = parseInt(status.message);
			
			document.getElementById('quantity-cart-header').innerText = quantityCart;
        } else {
            console.log(status.message);
        }
    })
    .catch(error => {
        console.error(error);
    })
}

getQuantityCart();
