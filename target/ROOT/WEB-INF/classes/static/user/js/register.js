
function register(user){
    openLoadingAnimation();

    fetch(BASE_URL + '/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user),
    })
    .then(response => response.json())
	.then(status => {
        closeLoadingAnimation();

		if (status.success){
			openPopupNotify('Đăng ký thành công.', '', 'success');
			
			setTimeout(() => {
				window.location.href = BASE_URL + '/login';
			}, 1200);
		} else {
			openPopupNotify('Đăng ký thất bại!', status.message, 'error');
		}
	})
    .catch(error => {
        closeLoadingAnimation();
        openPopupNotify('Đăng ký thất bại!', 'Lỗi bất định', 'error');
        console.log(error);
    });
}

function validateRegister(){

    Validator({
        form: 'form-register',
        formInput: '.input-box',
        errorMessage: '.label-error',
        rules: [
            Validator.isRequired('#username', 'Vui lòng nhập tên người dùng!'),
            Validator.isRequired('#email', 'Vui lòng nhập email của bạn!'),
            Validator.isRequired('#password', 'Vui lòng nhập mật khẩu của bạn!'),
            Validator.isRequired('#repeat-password', 'Vui lòng nhập lại mật khẩu của bạn!'),
            Validator.isEmail('#email', 'Vui lòng nhập một email hợp lệ!'),
            Validator.minLength('#password', 8, 'Vui lòng nhập tối thiểu 8 kí tự!'),
            Validator.minLength('#repeat-password', 8, 'Vui lòng nhập tối thiểu 8 kí tự!'),
            Validator.isConfirmed('#repeat-password', '#password', 'Vui lòng nhập lại đúng mật khẩu!')
        ],
        onSubmit: function(data){
            const user = {
                username: data['username'],
                email: data['email'],
                password: data['password']
            }

			register(user);   // Register when validate success 
        }
    });
}

validateRegister();
