<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Tài khoản - Book Store</title>
    <link rel="stylesheet" href="${BASE_URL}/static/user/css/account.css">
	<link rel="stylesheet" href="${BASE_URL}/static/user/css/responsive.css">
</head>
<body>
    
    <div class="personal">

        <!-- Form controls -->
        <div class="controls">
            <div class="info">

                <div class="info__name">
                    <div class="info__name-fullname">${ user.fullname }</div>
                    <div class="info__name-username">@${ user.username }</div>
                </div>
            </div>

            <div class="nav">
                <div class="nav__heading">Cài đặt tài khoản</div>
                <div class="nav__item active">Thông tin cá nhân</div>
                <div class="nav__item">Mật khẩu và bảo mật</div>
                <div class="nav__item">Thanh toán</div>
                <div class="nav__item">Điều khoản sử dụng</div>
            </div>
        </div>

        <div class="details">
            <!-- Personal infomations -->
            <div class="wrapper details__info active">
                <div class="wrapper__header">
                    <div class="wrapper__header-heading">
                        <div class="wrapper-back mobile"><i class="fa-solid fa-angle-left"></i></div>
                        Thông tin cá nhân
                    </div>
                    <div class="wrapper__header-des">
                        <p>Chúng tôi sử dụng thông tin này để xác minh danh tính và bảo vệ tài khoản của bạn.</p>
                        <p>Vui lòng cung cấp đầy đủ thông tin để chúng tôi dễ dàng lấy lại tài khoản giúp bạn khi gặp sự cố.</p>
                    </div>
                </div>

                <div class="wrapper__body">
                    <!-- Form fullname -->
                    <div class="form__group">
                        <div class="form__group-box" style="margin-bottom: 0px;">
                            <label class="form__group-heading">Họ và tên</label>
                            <p class="form__group-des">${ user.fullname }</p>
                        </div>

                        <i class="fa-solid fa-angle-right" style="transform: translateY(-50%);"></i>

                        <div class="form-change" style="display: none; animation: 0.3s ease 0s 1 normal forwards running close;">
                            <div class="form__heading">Thay đổi họ tên:</div>
                            <div class="form__body">
                                <input type="text" class="form__body-input" name="fullname">
                                <button type="button" class="form__body-submit">Lưu</button>
                            </div>
                        </div>
                    </div>
                    <div class="line"></div>

                    <!-- Form email -->
                    <div class="form__group">
                        <div class="form__group-box" style="margin-bottom: 0px;">
                            <label class="form__group-heading">Email</label>
                            <p class="form__group-des">${ user.email }</p>
                        </div>

                        <i class="fa-solid fa-angle-right" style="transform: translateY(-50%);"></i>

                        <div class="form-change" style="animation: 0.3s ease 0s 1 normal forwards running close; display: none;">
                            <div class="form__heading">Thay đổi Email:</div>
                            <div class="form__body">
                                <input type="text" class="form__body-input" name="email">
                                <button type="button" class="form__body-submit">Lưu</button>
                            </div>
                        </div>
                    </div>
                    <div class="line"></div>

                    <!-- Form phone number -->
                    <div class="form__group">
                        <div class="form__group-box" style="margin-bottom: 0px;">
                            <label class="form__group-heading">Số điện thoại</label>
                            <p class="form__group-des">${ user.phone }</p>
                        </div>

                        <i class="fa-solid fa-angle-right" style="transform: translateY(-50%);"></i>

                        <div class="form-change" style="animation: 0.3s ease 0s 1 normal forwards running close; display: none;">
                            <div class="form__heading">Thay đổi Số điện thoại:</div>
                            <div class="form__body">
                                <input type="text" class="form__body-input" name="phone-number">
                                <button type="button" class="form__body-submit">Lưu</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Passwords and security -->
            <div class="wrapper">
                <div class="wrapper__header">
                    <div class="wrapper__header-heading">
                        <div class="wrapper-back mobile"><i class="fa-solid fa-angle-left"></i></div>
                        Mật khẩu và Bảo mật
                    </div>
                    <div class="wrapper__header-des">
                        <p>Quản lý mật khẩu, tùy chọn đăng nhập và khôi phục tài khoản.</p>
                    </div>
                </div>

                <div class="wrapper__body">   
                    <!-- Form change password -->
                    <div class="form__group">
                        <div class="form__group-box" style="margin-bottom: 0px;">
                            <label class="form__group-heading">Thay đổi mật khẩu</label>
                            <p class="form__group-des">Tạo một mật khẩu mới bảo mật hơn.</p>
                        </div>

                        <i class="fa-solid fa-angle-right" style="transform: translateY(-50%);"></i>

                        <div class="form-change form-change-password" style="animation: 0.3s ease 0s 1 normal forwards running close; display: none;">
                            <div class="form__heading">Thay đổi mật khẩu:</div>
                            <div class="form__body">
                                <span class="error-message"></span>
                                <input type="password" autocomplete="off" class="form__body-input" name="password" placeholder="Nhập mật khẩu tài khoản">
                                <span class="error-message"></span>
                                <input type="password" autocomplete="off" class="form__body-input" name="new-password" placeholder="Nhập mật khẩu mới">
                                <button type="button" class="form__body-submit">Lưu</button>
                            </div>
                        </div>
                    </div>
                    <div class="line"></div>
                </div>
            </div>

            <!-- Payment -->
            <div class="wrapper">
                <div class="wrapper__header">
                    <div class="wrapper__header-heading">
                        <div class="wrapper-back mobile"><i class="fa-solid fa-angle-left"></i></div>
                        Thanh toán
                    </div>
                    <div class="wrapper__header-des">
                        <p>Thiết lập phương thức thanh toán và cập nhật địa chỉ giao hàng của bạn tại đây.</p>
                    </div>
                </div>

                <div class="wrapper__body">
                    <!-- Form shipping address -->
                    <div class="form__group">
                        <div class="form__group-box" style="margin-bottom: 0px;">
                            <label class="form__group-heading">Thay đổi địa chỉ giao hàng</label>
                            <p class="form__group-des">${ user.address }</p>
                        </div>

                        <i class="fa-solid fa-angle-right" style="transform: translateY(-50%);"></i>

                        <div class="form-change" style="animation: 0.3s ease 0s 1 normal forwards running close; display: none;">
                            <div class="form__heading">Thay đổi địa chỉ giao hàng:</div>
                            <div class="form__body">
                                <input type="text" class="form__body-input" name="new-address">
                                <button type="button" class="form__body-submit">Lưu</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Terms of Use -->
            <div class="wrapper terms">
                <div class="wrapper__header">
                    <div class="wrapper__header-heading">
                        <div class="wrapper-back mobile"><i class="fa-solid fa-angle-left"></i></div>
                        Điều khoản sử dụng
                    </div>
                    <div class="wrapper__header-des" id="terms-of-use"></div>
                </div>
            </div>
        </div>
    </div>

	<script src="${BASE_URL}/static/user/js/account.js"></script>
</body>