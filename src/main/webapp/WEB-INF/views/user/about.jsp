<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<head>
	<title>Về chúng tôi - Book Store</title>
	<link rel="stylesheet" href="${BASE_URL}/static/user/css/about.css">
</head>

<body>
    <!-- Why choose us -->
    <div class="why-choose-us">
        <div class="container">
            <div class="row justify-content-between">
                <div class="col-lg-6">
                    <h2 class="text-dark">Tại sao chọn chúng tôi?</h2>
                    <p>
                        Hãy lựa chọn chúng tôi vì chúng tôi cam kết cung cấp 
                        giải pháp chất lượng và dịch vụ tận tâm, đáp ứng mọi nhu cầu của bạn.
                    </p>
                    
                    <div class="row my-5">
                        <div class="col-6 col-md-6">
                            <div class="icon mb-3">
                                <img src="${BASE_URL}/static/user/images/bag.svg" alt="Image">
                            </div>
    
                            <h3 class="fs-5 text-dark">Mua hàng nhanh chóng</h3>
                            <p class="text-justify">
                                Tận hưởng trải nghiệm mua sắm không giới hạn với 
                                dịch vụ mua hàng nhanh chóng của chúng tôi - 
                                mọi thứ đều chỉ cách bạn một cú nhấp chuột!
                            </p>
                        </div>
    
                        <div class="col-6 col-md-6">
                            <div class="icon mb-3">
                                <img src="${BASE_URL}/static/user/images/return.svg" alt="Image">
                            </div>
          
                            <h3 class="fs-5 text-dark">Hoàn trả miễn phí</h3>
                            
                            <p class="text-justify">
                                Bạn có thể mua sắm thoải mái mà không cần lo lắng về việc chọn sai! Chính sách hoàn trả 
                                miễn phí và nhanh chóng của chúng tôi sẽ khiến bạn hài lòng. 
                            </p>
                        </div>
    
                        <div class="col-6 col-md-6">
                            <div class="icon mb-3">
                                <img src="${BASE_URL}/static/user/images/truck.svg" alt="Image">
                            </div>
                            
                            <h3 class="fs-5 text-dark">Nhanh &amp; Tiết kiệm</h3>
                            
                            <p class="text-justify">
                                Đơn hàng sẽ được giao tới bạn nhanh nhất với chi phí vô cùng tiết kiệm.
                            </p>
                        </div>
    
                        <div class="col-6 col-md-6">
                            <div class="icon mb-3">
                                <img src="${BASE_URL}/static/user/images/support.svg" alt="Image">
                            </div>
    
                            <h3 class="fs-5 text-dark">Hỗ trợ 24/7</h3>
                            <p class="text-justify">Đội ngũ của chúng tôi luôn sẵn lòng để giúp bạn mọi lúc, mọi nơi.</p>
                        </div>                    
                    </div>
                </div>
    
                <div class="col-lg-5">
                    <div class="mt-5 mt-lg-0 pb-5 img-wrapper">
                        <img class="w-100 h-auto rounded-4" src="${BASE_URL}/static/user/images/why-choose-us-img.jpg" alt="Image">
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>