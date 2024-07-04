
function login(user){
    openLoadingAnimation();

    fetch(BASE_URL + '/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user),
    })
    .then(response => {
	    const authorizationHeader = response.headers.get('Authorization');
	    
	    if(authorizationHeader){
			localStorage.setItem('jwtToken', authorizationHeader);
		} else {
			console.error("Missing Authorization header in response.");
		}
	    
	    return response.json();
	})
    .then(status => {
        closeLoadingAnimation();

        if (status.success){
			openPopupNotify('Đăng nhập thanh cong!', status.message, 'success');
			
			setTimeout(() => {
				if (status.isAdmin)
					window.location.href = BASE_URL + '/admin';
				else
					window.location.href = BASE_URL + '/';
			}, 1000);
        } else {
            openPopupNotify('Đăng nhập thất bại!', status.message, 'error');
        }
    })
    .catch(error => {
        closeLoadingAnimation();
        openPopupNotify('Đăng nhập thất bại!', 'Lỗi bất định', 'error');
        console.log(error);
    })
}

function validateLogin(){

    Validator({
        form: 'form-login',
        formInput: '.input-box',
        errorMessage: '.label-error',
        rules: [
            Validator.isRequired('#username', 'Vui lòng nhập username hoặc email!'),
            Validator.isRequired('#password', 'Vui lòng nhập mật khẩu của bạn!'),
            Validator.minLength('#password', 8, 'Vui lòng nhập tối thiểu 8 kí tự!'),
        ],
        onSubmit: function(data){
            let user = {
				username: data['username'],
                password: data['password']
            }
        
			login(user);
        }
    });
}

validateLogin();