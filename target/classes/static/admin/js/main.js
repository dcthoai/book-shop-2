const logoutAdminButton = document.getElementById('logout-admin-btn');

logoutAdminButton.onclick = () => {
	logout();
}

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
				window.location.href = BASE_URL + '/login';
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