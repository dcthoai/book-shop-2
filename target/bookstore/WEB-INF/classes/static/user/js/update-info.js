const fullnameUpdateInput = document.getElementById('fullname');
const phoneUpdateInput = document.getElementById('phone');
const addressUpdateInput = document.getElementById('address');
const emailUpdateInput = document.getElementById('email');
const oldPasswordInput = document.getElementById('old-pass');
const newPasswordInput = document.getElementById('new-pass');

const fullnameUpdateButton = document.getElementById('fullname-submit');
const addressUpdateButton = document.getElementById('address-submit');
const emailUpdateButton = document.getElementById('email-submit');
const phoneUpdateButton = document.getElementById('phone-submit');
const passwordUpdateButton = document.getElementById('password-submit');

function updateUserInfo(key, value) {
	fetch(BASE_URL + `/user/update`, {
		method: "POST",
		headers: {
			'Content-type': 'application/json'
		},
		body: JSON.stringify({
			key: key,
			value: value
		})
	})
	.then(response => response.json())
	.then(status => {
		if (status.success) {
			openPopupNotify('Cập nhật thành công', '', 'success');
			
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

fullnameUpdateButton.addEventListener('click', () => {
	updateUserInfo('fullname', fullnameUpdateInput.value.trim());
});

addressUpdateButton.addEventListener('click', () => {
	updateUserInfo('address', addressUpdateInput.value.trim());
});

emailUpdateButton.addEventListener('click', () => {
	updateUserInfo('email', emailUpdateInput.value.trim());
});

phoneUpdateButton.addEventListener('click', () => {
	updateUserInfo('phone', phoneUpdateInput.value.trim());
});

passwordUpdateButton.addEventListener('click', () => {
	fetch(BASE_URL + `/user/password/change`, {
		method: "POST",
		headers: {
			'Content-type': 'application/json'
		},
		body: JSON.stringify({
			oldPassword: oldPasswordInput.value.trim(),
			newPassword: newPasswordInput.value.trim()
		})
	})
	.then(response => response.json())
	.then(status => {
		if (status.success) {
			openPopupNotify('Đổi mật khẩu thành công', '', 'success');
			
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
})