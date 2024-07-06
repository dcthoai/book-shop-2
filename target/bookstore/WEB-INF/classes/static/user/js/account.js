
const termsOfUseHTML = `
    <p>Chào mừng quý khách đến mua sắm tại BookStore. Sau khi truy cập vào website BookStore 
    để tham khảo hoặc mua sắm, quý khách đã đồng ý tuân thủ và ràng buộc với những quy định của BookStore. 
    Vui lòng xem kỹ các quy định và hợp tác với chúng tôi để xây dựng 1 website BookStore 
    ngày càng thân thiện và phục vụ tốt những yêu cầu của chính quý khách. Ngoài ra, nếu có 
    bất cứ câu hỏi nào về những thỏa thuận trên đây, vui lòng email cho chúng tôi qua địa chỉ
    <a href="">support@bookStore.com</a>
    </p>
    <h4>Tài khoản của khách hàng</h4>
    <p>Khi sử dụng dịch vụ ABCBook, quý khách sẽ cung cấp cho chúng tôi thông tin về địa chỉ email, 
    mật khẩu và họ tên để có được 1 tài khoản tại đây. Việc sử dụng và bảo mật thông tin tài khoản 
    là trách nhiệm và quyền lợi của quý khách khi sử dụng BookStore. Ngoài ra, những thông tin khác 
    trong tài khoản như tên tuổi, địa chỉ.... là những thông tin sẽ giúp ABCBook phục vụ quý khách tốt nhất. 
    Trong trường hợp thông tin do quý khách cung cấp không đầy đủ hoặc sai dẫn đến việc không 
    thể giao hàng cho quý khách, chúng tôi có quyền đình chỉ hoặc từ chối phục vụ, giao hàng mà 
    không phải chịu bất cứ trách nhiệm nào đối với quý khách. Khi có những thay đổi thông tin của quý khách, 
    vui lòng cập nhật lại thông tin trong tài khoản trong phần thông tin tài khoản. 
    Quý khách phải giữ kín mật khẩu và tài khoản, hoàn toàn chịu trách nhiệm đối với tất cả 
    các hoạt động diễn ra thông qua việc sử dụng mật khẩu hoặc tài khoản của mình. Quý khách nên 
    đảm bảo thoát khỏi tài khoản tại BookStore sau mỗi lần sử dụng để bảo mật thông tin của mình.
    </p>
    <h4>Quyền lợi bảo mật thông tin của khách hàng</h4>
    <p>Khi sử dụng dịch vụ tại website BookStore, quý khách được đảm bảo rằng những thông tin cung cấp cho 
    chúng tôi sẽ chỉ được dùng để nâng cao chất lượng dịch vụ dành cho khách hàng của BookStore và 
    sẽ không được chuyển giao cho 1 bên thứ ba nào khác vì mục đích thương mại. Thông tin của quý khách 
    tại BookStore sẽ được chúng tôi bảo mật và chỉ trong trường hợp pháp luật yêu cầu, chúng tôi sẽ buộc 
    phải cung cấp những thông tin này cho các cơ quan pháp luật.
    </p>
    <h4>Trách nhiệm của khách hàng khi sử dụng dịch vụ của BookStore</h4>
    <p>Quý khách tuyệt đối không được sử dụng bất kỳ công cụ, phương pháp nào để can thiệp, xâm nhập 
    bất hợp pháp vào hệ thống hay làm thay đổi cấu trúc dữ liệu tại website BookStore. 
    Quý khách không được có những hành động (thực hiện, cổ vũ) việc can thiệp, xâm nhập dữ liệu 
    của BookStore cũng như hệ thống máy chủ của chúng tôi. Ngoài ra, xin vui lòng thông báo cho 
    quản trị web của BookStore ngay khi quý khách phát hiện ra lỗi hệ thống theo số điện thoại 
    <span>0123456789</span> hoặc email <a href="">support@bookStore.com</a>
    </p>
    <p>Quý khách không được đưa ra những nhận xét, đánh giá có ý xúc phạm, quấy rối, làm phiền hoặc 
    có bất cứ hành vi nào thiếu văn hóa đối với người khác. Không nêu ra những nhận xét có tính 
    chính trị (tuyên truyền, chống phá, xuyên tạc chính quyền), kỳ thị tôn giáo, giới tính, sắc tộc....
    Tuyệt đối cấm mọi hành vi mạo nhận, cố ý tạo sự nhầm lẫn mình là một khách hàng khác hoặc 
    là thành viên Ban Quản Trị BookStore.
    </p>
    <h4>Trách nhiệm và quyền lợi của BookStore</h4>
    <p>Trong trường hợp có những phát sinh ngoài ý muốn hoặc trách nhiệm của mình, BookStore sẽ không 
    chịu trách nhiệm về mọi tổn thất phát sinh. Ngoài ra, chúng tôi không cho phép các tổ chức, 
    cá nhân khác quảng bá sản phẩm tại website BookStore mà chưa có sự đồng ý bằng văn bản từ đội ngũ BookStore. 
    Các thỏa thuận và quy định trong Điều khoản sử dụng có thể thay đổi vào bất cứ lúc nào 
    nhưng sẽ được chúng tôi thông báo cụ thể trên website BookStore.
    </p>
`;

document.getElementById('terms-of-use').innerHTML = termsOfUseHTML;
var buttonControls = document.querySelectorAll('.personal .controls .nav__item');
var items = document.querySelectorAll('.personal .details .wrapper');

// Add event click to open for forms-group
addEventFormsGroup();

buttonControls.forEach(function(buttonControl, index) {
    buttonControl.addEventListener('click', function() {
        buttonControls.forEach(function(button, i){
            button.classList.remove('active');
            items[i].classList.remove('active');
        });

        this.classList.add('active');
        items[index].classList.add('active');
        closeAllFormChange(items[index].querySelectorAll('.form__group'), -1);
        addEventFormsGroup();
    });
});

// Open form change personal infomations
function openFormChange(item){
    var formChange = item.querySelector('.form-change');
    var formGroupBox = item.querySelector('.form__group-box');

    item.querySelector('i').style.transform = 'translateY(-50%) rotate(90deg)';
    formChange.style.display = 'block';
    formChange.style.animation = 'open 0.3s ease forwards';
    formGroupBox.style.marginBottom = formChange.offsetHeight + 'px';
}

// Close form change personal infomations
function closeFormChange(item){
    var formChange = item.querySelector('.form-change');
    var formGroupBox = item.querySelector('.form__group-box');

    item.querySelector('i').style.transform = 'translateY(-50%)';
    formChange.style.animation = 'close 0.3s ease forwards';
    formGroupBox.style.marginBottom = '0px';
    setTimeout(function(){
        formChange.querySelectorAll('input').forEach(function(item){
            item.value = '';
        })
        formChange.style.display = 'none';
    }, 300);
}

// Close all form change
function closeAllFormChange(formsGroup, index){
    formsGroup.forEach(function(item, i){
        if (i != index){
            closeFormChange(item);
            item.querySelector('.form-change').style.display = 'none';
        }
    })
}

// Add animation when open/close form-group
function addEventFormsGroup(){
    var formsGroup = document.querySelectorAll('.personal .wrapper.active .form__group');
    var formsGroupBox = document.querySelectorAll('.personal .wrapper.active .form__group-box');

    formsGroup.forEach(function(item, index){
        formsGroupBox[index].addEventListener('click', function(){
            if(formsGroupBox[index].disabled == true)
                return;

            formsGroupBox[index].disabled = true;
            setTimeout(function(){
                formsGroupBox[index].disabled = false;
            }, 320);

            let isOpen = item.querySelector('.form__group-box').style.marginBottom !== '0px';   // default = false

            if(isOpen){
                isOpen = false;
                closeFormChange(item);
            }else{
                isOpen = true;
                closeAllFormChange(formsGroup, index);
                openFormChange(item);
            }
        })
    });
}


// Change interface account settings when on mobile screen
window.addEventListener('DOMContentLoaded', function(){
    var clientWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;

    if(clientWidth < 740){
        var navItems = document.querySelectorAll('.personal .controls .nav__item');
        var detailItems = document.querySelectorAll('.personal .details .wrapper');
        var detailsForm = document.querySelector('.personal .details');
        var detailsBackBtn = document.querySelectorAll('.personal .details .wrapper-back');
        
        navItems.forEach(function(navItem, index){
            navItem.classList.remove('active');
            detailItems[index].classList.remove('active');

            navItem.addEventListener('click', function(){
                navItems.forEach(function(item, i){
                    item.classList.remove('active');
                    detailItems[i].classList.remove('active');
                });

                navItem.classList.add('active');
                detailItems[index].classList.add('active');
                detailsForm.style.left = '0';
                
            });
        });

        detailsBackBtn.forEach(function(button, index){
            button.addEventListener('click', function(){
                navItems.forEach(function(item, i){
                    item.classList.remove('active');
                });
                
                detailsForm.style.left = '100%';

                setTimeout(function(){
                    detailItems[index].classList.remove('active');
                }, 400);
            })
        })
    }
});